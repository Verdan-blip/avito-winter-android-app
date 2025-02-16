package ru.verdan.feature.loaded.presention

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dev.androidbroadcast.vbpd.viewBinding
import kotlinx.coroutines.launch
import ru.verdan.common.base.BaseFragment
import ru.verdan.common.util.collectWithLifecycle
import ru.verdan.core.theme.entity.TrackModel
import ru.verdan.core.theme.view.adapter.TrackAdapter
import ru.verdan.core.theme.view.decoration.DefaultItemDecoration
import ru.verdan.core.theme.view.text.onTextChange
import ru.verdan.feature.loaded.R
import ru.verdan.feature.loaded.databinding.FragmentLoadedTracksBinding
import ru.verdan.feature.loaded.di.LoadedTracksComponentHolder

class LoadedTracksFragment : BaseFragment<FragmentLoadedTracksBinding, LoadedTracksViewModel>(
    id = R.layout.fragment_loaded_tracks
) {
    override val viewBinding by viewBinding(FragmentLoadedTracksBinding::bind)
    
    override val viewModel by viewModels<LoadedTracksViewModel> {
        LoadedTracksComponentHolder
            .get(requireContext())
            .viewModelFactory
    }

    private val chartTracksAdapter by lazy {
        TrackAdapter(
            context = requireContext(),
            onTrackClick = { viewModel.onTrackClick(it) }
        )
    }

    private val foundTracksAdapter by lazy {
        TrackAdapter(
            context = requireContext(),
            onTrackClick = { viewModel.onFoundTrackClick(it) }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onLaunch()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            setupChartTracksRecyclerView()
            setupFoundTracksRecyclerView()
            setupSearchView()
            viewModel.tracks.collectWithLifecycle(
                viewLifecycleOwner, ::onCollectTracks
            )
            viewModel.foundTracks.collectWithLifecycle(
                viewLifecycleOwner, ::onCollectFoundTracks
            )
            viewModel.showTracksProgressBar.collectWithLifecycle(
                viewLifecycleOwner, ::onCollectChartTracksProgressVisibility
            )
            viewModel.showFoundTracksProgressBar.collectWithLifecycle(
                viewLifecycleOwner, ::onCollectFoundTracksProgressVisibility
            )
        }
    }

    private fun FragmentLoadedTracksBinding.setupChartTracksRecyclerView() {
        rvLoadedTracks.adapter = chartTracksAdapter
        rvLoadedTracks.addItemDecoration(DefaultItemDecoration(requireContext()))
    }

    private fun FragmentLoadedTracksBinding.setupFoundTracksRecyclerView() {
        rvFoundLoadedTracks.adapter = foundTracksAdapter
        rvFoundLoadedTracks.addItemDecoration(DefaultItemDecoration(requireContext()))
    }

    private fun FragmentLoadedTracksBinding.setupSearchView() {
        svQuery.editText.setText(viewModel.query.value)
        svQuery.editText.onTextChange { text ->
            viewModel.onQueryChange(text)
        }
        svQuery.editText.setOnEditorActionListener { _, _, _ ->
            viewModel.onQuerySubmit()
            true
        }
    }

    private fun onCollectChartTracksProgressVisibility(isVisible: Boolean) {
        viewBinding.apply {
            progressChartTracks.root.isVisible = isVisible
            rvLoadedTracks.isVisible = !isVisible
        }
    }

    private fun onCollectFoundTracksProgressVisibility(isVisible: Boolean) {
        viewBinding.apply {
            progressFoundTracks.root.isVisible = isVisible
            rvLoadedTracks.isVisible = !isVisible
        }
    }

    private fun onCollectTracks(tracks: List<TrackModel>?) {
        chartTracksAdapter.submitList(tracks)
    }

    private fun onCollectFoundTracks(tracks: List<TrackModel>?) {
        lifecycleScope.launch {
            foundTracksAdapter.submitList(tracks)
        }
    }
}
