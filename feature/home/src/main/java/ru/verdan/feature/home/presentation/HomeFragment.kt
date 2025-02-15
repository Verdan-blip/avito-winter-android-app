package ru.verdan.feature.home.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import dev.androidbroadcast.vbpd.viewBinding
import kotlinx.coroutines.launch
import ru.verdan.common.base.BaseFragment
import ru.verdan.common.util.collectWithLifecycle
import ru.verdan.core.theme.entity.TrackModel
import ru.verdan.core.theme.view.adapter.TrackAdapter
import ru.verdan.core.theme.view.adapter.TrackPagingAdapter
import ru.verdan.core.theme.view.decoration.DefaultItemDecoration
import ru.verdan.core.theme.view.text.onTextChange
import ru.verdan.feature.home.R
import ru.verdan.feature.home.databinding.FragmentHomeBinding
import ru.verdan.feature.home.di.HomeComponentHolder

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    id = R.layout.fragment_home
) {
    override val viewBinding by viewBinding(FragmentHomeBinding::bind)
    
    private val viewModel by viewModels<HomeViewModel> {
        HomeComponentHolder
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
        TrackPagingAdapter(
            context = requireContext(),
            onTrackClick = { viewModel.onTrackClick(it) }
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
            viewModel.chartTracks.collectWithLifecycle(
                viewLifecycleOwner, ::onCollectChartTracks
            )
            viewModel.foundTracks.collectWithLifecycle(
                viewLifecycleOwner, ::onCollectFoundTracks
            )
            viewModel.showChartTracksProgressBar.collectWithLifecycle(
                viewLifecycleOwner, ::onCollectChartTracksProgressVisibility
            )
            viewModel.showFoundTracksProgressBar.collectWithLifecycle(
                viewLifecycleOwner, ::onCollectFoundTracksProgressVisibility
            )
        }
    }

    private fun FragmentHomeBinding.setupChartTracksRecyclerView() {
        rvChartTracks.adapter = chartTracksAdapter
        rvChartTracks.addItemDecoration(DefaultItemDecoration(requireContext()))
    }

    private fun FragmentHomeBinding.setupFoundTracksRecyclerView() {
        rvFoundTracks.adapter = foundTracksAdapter
        rvFoundTracks.addItemDecoration(DefaultItemDecoration(requireContext()))
    }

    private fun FragmentHomeBinding.setupSearchView() {
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
            rvChartTracks.isVisible = !isVisible
        }
    }

    private fun onCollectFoundTracksProgressVisibility(isVisible: Boolean) {
        viewBinding.apply {
            progressFoundTracks.root.isVisible = isVisible
            rvFoundTracks.isVisible = !isVisible
        }
    }

    private fun onCollectChartTracks(tracks: List<TrackModel>?) {
        chartTracksAdapter.submitList(tracks)
    }

    private fun onCollectFoundTracks(tracks: PagingData<TrackModel>) {
        lifecycleScope.launch {
            foundTracksAdapter.submitData(tracks)
        }
    }
}
