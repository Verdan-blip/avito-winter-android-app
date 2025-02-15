package ru.verdan.feature.trackplayer.presentation

import android.os.Bundle
import android.view.View
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

class TrackPlayerFragment : BaseFragment<FragmentTrackPlayerBinding, TrackPlayerComponentHolder>(
    id = R.layout.fragment_track_player,
    componentHolder = TrackPlayerComponentHolder
) {
    override val viewBinding by viewBinding(FragmentTrackPlayerBinding::bind)

    val trackPlayerArgs

    private val viewModel by viewModels<TrackPlayerViewModel> {
        TrackPlayerComponentHolder
            .create(requireContext())
            .factoryOfViewFactory
            .create(listOf())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            ivCover.setImageResource(ru.verdan.core.theme.R.drawable.image_placeholder)
            ibPlayPause.setOnClickListener { viewModel.onPlayPause() }
            ibPrev.setOnClickListener { viewModel.onPlayPrevious() }
            ibNext.setOnClickListener { viewModel.onPlayNext() }
            sbProgress.setOnSeekBarChangeListener(
                onStartTrackingTouch = { progress -> viewModel.onProgressChange(progress) },
                onStopTrackingTouch = { viewModel.onProgressChanged() }
            )
            collectViewModelStates()
        }
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
            }
        }
    }

    private fun onCollectCanPlayNext(canPlayNext: Boolean) {
        viewBinding.ibNext.isVisible = canPlayNext
    }

    private fun onCollectCanPlayPrevious(canPlayNext: Boolean) {
        viewBinding.ibPrev.isVisible = canPlayNext
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
    }
}
