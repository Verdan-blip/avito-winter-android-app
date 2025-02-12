package ru.verdan.feature.home.presentation

import android.os.Bundle
import android.view.View
import dev.androidbroadcast.vbpd.viewBinding
import ru.verdan.common.base.BaseFragment
import ru.verdan.common.util.collectWithLifecycle
import ru.verdan.core.theme.entity.TrackModel
import ru.verdan.core.theme.view.recycler.TrackAdapter
import ru.verdan.feature.home.databinding.FragmentHomeBinding
import ru.verdan.feature.home.di.HomeComponentHolder

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeComponentHolder>(
    componentHolder = HomeComponentHolder
) {
    override val viewBinding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel by daggerViewModel<HomeViewModel>()

    private val adapter by lazy { TrackAdapter(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onLaunch()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            setupTracksRecyclerView()
            viewModel.chartTracks.collectWithLifecycle(viewLifecycleOwner, ::collectTracks)
        }
    }

    private fun FragmentHomeBinding.setupTracksRecyclerView() {
        rvTracks.adapter = adapter
    }

    private fun collectTracks(tracks: List<TrackModel>?) {
        adapter.submitList(tracks)
    }
}
