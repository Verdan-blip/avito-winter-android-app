package ru.verdan.feature.home.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.verdan.common.base.BaseViewModel
import ru.verdan.common.resource.ResourceProvider
import ru.verdan.core.theme.entity.TrackModel
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

    private val _chartTracks = MutableStateFlow<List<TrackModel>?>(listOf())
    val chartTracks: StateFlow<List<TrackModel>?> get() = _chartTracks

    private val _foundTracks = MutableStateFlow<List<TrackModel>?>(listOf())
    val foundTracks: StateFlow<List<TrackModel>?> get() = _foundTracks

    private val _showTracksProgressBar = MutableStateFlow(true)
    val showTracksProgressBar: StateFlow<Boolean> get() = _showTracksProgressBar

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    fun onLaunch() {
        viewModelScope.launch {
            doSafeCall {
                _showTracksProgressBar.value = true
                val chartTracks = getChartTracksUseCase()
                _chartTracks.emit(chartTracks.toTrackModelList())
                _showTracksProgressBar.value = false
            }
        }
    }

    fun onQueryChange(query: String) {
        _query.value = query
    }

    fun onTrackModelClick(id: Int) {
        homeRouter.navigateToPlayer()
    }
}
