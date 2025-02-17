package ru.verdan.feature.trackplayer.presentation

import android.app.DownloadManager
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import coil3.load
import coil3.request.crossfade
import coil3.request.error
import dev.androidbroadcast.vbpd.viewBinding
import ru.verdan.common.base.BaseFragment
import ru.verdan.common.util.collectWithLifecycle
import ru.verdan.common.util.setOnSeekBarChangeListener
import ru.verdan.feature.loaded.R
import ru.verdan.feature.loaded.databinding.FragmentTrackPlayerBinding
import ru.verdan.feature.trackplayer.di.TrackPlayerComponentHolder
import ru.verdan.feature.trackplayer.presentation.entity.TrackModel
import ru.verdan.feature.trackplayer.presentation.receiver.DownloadCompletionReceiver

class TrackPlayerFragment : BaseFragment<FragmentTrackPlayerBinding, TrackPlayerViewModel>(
    id = R.layout.fragment_track_player
) {
    override val viewBinding by viewBinding(FragmentTrackPlayerBinding::bind)

    private var queueTrackIds: List<Long> = listOf()
    private var selectedTrackId: Long = -1L

    override val viewModel by viewModels<TrackPlayerViewModel> {
        TrackPlayerComponentHolder
            .create(requireContext())
            .factoryOfViewFactory
            .create(queueTrackIds, selectedTrackId)
    }

    private val receiver = DownloadCompletionReceiver { id, uri ->
        viewModel.onDownloadFinished(id, uri.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ContextCompat.registerReceiver(
            requireContext(),
            receiver,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
            ContextCompat.RECEIVER_EXPORTED
        )
        extractArgs()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onLaunch()
        viewBinding.apply {
            initViews()
            ivCover.setImageResource(ru.verdan.core.theme.R.drawable.image_placeholder)
            ibPlayPause.setOnClickListener { viewModel.onPlayPause() }
            ibPrev.setOnClickListener { viewModel.onPlayPrevious() }
            ibNext.setOnClickListener { viewModel.onPlayNext() }
            ibDownload.setOnClickListener { viewModel.onDownloadTrack() }
            sbProgress.setOnSeekBarChangeListener(
                onStartTrackingTouch = { viewModel.onProgressStartTrackingTouch() },
                onProgressChanged = { progress -> viewModel.onProgressChange(progress) },
                onStopTrackingTouch = { viewModel.onProgressStopTrackingTouch() }
            )
            collectBaseEvents()
            collectViewModelStates()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        requireContext().unregisterReceiver(receiver)
    }

    private fun extractArgs() {
        arguments?.apply {
            getLongArray(KEY_QUEUE_TRACK_IDS)?.also { ids ->
                queueTrackIds = ids.toList()
            }
            selectedTrackId = getLong(KEY_SELECTED_TRACK)
        }
    }

    private fun FragmentTrackPlayerBinding.initViews() {
        val noDataMessage = getString(R.string.no_data)
        tvAlbum.text = noDataMessage
        tvTitle.text = noDataMessage
        tvArtist.text = noDataMessage
    }

    private fun onCollectCurrentTrack(track: TrackModel?) {
        viewBinding.apply {
            track?.apply {
                ivCover.load(coverUrl) {
                    crossfade(enable = true)
                    error(ru.verdan.core.theme.R.drawable.image_placeholder)
                }
                tvTitle.text = title
                tvArtist.text = artist
                tvAlbum.text = getString(R.string.album_name_format, albumTitle)
                ibDownload.isVisible = !isSaved
            }
        }
    }

    private fun onCollectIsPlaying(isPlaying: Boolean) {
        viewBinding.ibPlayPause.setImageResource(
            if (isPlaying)
                R.drawable.ic_pause_24
            else
                R.drawable.ic_play_24
        )
    }

    private fun onCollectCanPlayNext(canPlayNext: Boolean) {
        viewBinding.ibNext.isVisible = canPlayNext
    }

    private fun onCollectCanPlayPrevious(canPlayNext: Boolean) {
        viewBinding.ibPrev.isVisible = canPlayNext
    }

    private fun onCollectCurrentTimeDuration(timeProgress: String) {
        viewBinding.tvDuration.text = timeProgress
    }

    private fun onCollectCurrentTimeProgress(timeProgress: String) {
        viewBinding.tvProgress.text = timeProgress
    }

    private fun onCollectCurrentProgress(timeProgress: Int) {
        viewBinding.sbProgress.progress = timeProgress
    }

    private fun collectViewModelStates() {
        viewModel.currentTrack.collectWithLifecycle(
            viewLifecycleOwner, ::onCollectCurrentTrack
        )
        viewModel.isPlaying.collectWithLifecycle(
            viewLifecycleOwner, ::onCollectIsPlaying
        )
        viewModel.canPlayPrevious.collectWithLifecycle(
            viewLifecycleOwner, ::onCollectCanPlayNext
        )
        viewModel.canPlayNext.collectWithLifecycle(
            viewLifecycleOwner, ::onCollectCanPlayPrevious
        )
        viewModel.currentTimeProgress.collectWithLifecycle(
            viewLifecycleOwner, ::onCollectCurrentTimeProgress
        )
        viewModel.currentProgress.collectWithLifecycle(
            viewLifecycleOwner, ::onCollectCurrentProgress
        )
        viewModel.currentTimeDuration.collectWithLifecycle(
            viewLifecycleOwner, ::onCollectCurrentTimeDuration
        )
    }

    companion object {

        const val KEY_QUEUE_TRACK_IDS = "queue_track_ids"
        const val KEY_SELECTED_TRACK = "selected_track"
    }
}
