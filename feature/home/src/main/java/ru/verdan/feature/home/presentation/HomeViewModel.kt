package ru.verdan.feature.home.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.verdan.common.base.BaseViewModel
import ru.verdan.common.resource.ResourceProvider
import ru.verdan.core.theme.entity.TrackModel
import ru.verdan.feature.home.domain.entity.Track
import ru.verdan.feature.home.domain.usecase.GetChartTracksUseCase
import ru.verdan.feature.home.domain.usecase.SearchTrackByNameUseCase
import ru.verdan.feature.home.presentation.mapper.toTrackModelList
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeRouter: HomeRouter,
    private val getChartTracksUseCase: GetChartTracksUseCase,
    private val searchTrackByNameUseCase: SearchTrackByNameUseCase,
    resourceProvider: ResourceProvider
) : BaseViewModel(resourceProvider) {

    private val _chartTracks = MutableStateFlow<List<TrackModel>?>(null)
    val chartTracks: StateFlow<List<TrackModel>?> get() = _chartTracks

    private val _foundTracks = MutableStateFlow<PagingData<TrackModel>>(PagingData.empty())
    val foundTracks: StateFlow<PagingData<TrackModel>> get() = _foundTracks

    private val _showChartTracksProgressBar = MutableStateFlow(true)
    val showChartTracksProgressBar: StateFlow<Boolean> get() = _showFoundTracksProgressBar

    private val _showFoundTracksProgressBar = MutableStateFlow(false)
    val showFoundTracksProgressBar: StateFlow<Boolean> get() = _showFoundTracksProgressBar

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    fun onLaunch() {
        viewModelScope.launch {
            _showChartTracksProgressBar.emit(true)
            val chartTracks = getChartTracksUseCase()
            _chartTracks.emit(chartTracks.toTrackModelList())
            _showChartTracksProgressBar.emit(false)
        }
    }

    fun onQueryChange(query: String) {
        _query.value = query
    }

    fun onQuerySubmit() {
        viewModelScope.launch {
            _showFoundTracksProgressBar.value = true
            val chartTracksFlow = searchTrackByNameUseCase(_query.value)
                .cachedIn(viewModelScope)
            launch(Dispatchers.IO) {
                collectFoundTracks(chartTracksFlow)
            }
        }
    }

    fun onTrackClick(trackModel: TrackModel) {
        homeRouter.navigateToPlayer(listOf(trackModel).map { it.id })
    }

    private suspend fun collectFoundTracks(flow: Flow<PagingData<Track>>) {
        flow.collect { trackData ->
            _showFoundTracksProgressBar.value = false
            _foundTracks.emit(trackData.map { it.toTrackModelList() })
        }
    }
}
