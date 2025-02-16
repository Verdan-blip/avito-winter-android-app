package ru.verdan.feature.loaded.presention

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.verdan.common.base.BaseViewModel
import ru.verdan.common.resource.ResourceProvider
import ru.verdan.core.theme.entity.TrackModel
import ru.verdan.feature.loaded.domain.usecase.GetLoadedTracksUseCase
import ru.verdan.feature.loaded.domain.usecase.SearchTracksByNameUseCase
import ru.verdan.feature.loaded.presention.mapper.toTrackModelList
import javax.inject.Inject

class LoadedTracksViewModel @Inject constructor(
    private val loadedTracksRouter: LoadedTracksRouter,
    private val getLoadedTracksUseCase: GetLoadedTracksUseCase,
    private val searchTracksByNameUseCase: SearchTracksByNameUseCase,
    resourceProvider: ResourceProvider
) : BaseViewModel(resourceProvider) {

    private val _tracks = MutableStateFlow<List<TrackModel>?>(null)
    val tracks: StateFlow<List<TrackModel>?> get() = _tracks

    private val _foundTracks = MutableStateFlow<List<TrackModel>?>(null)
    val foundTracks: StateFlow<List<TrackModel>?> get() = _foundTracks

    private val _showTracksProgressBar = MutableStateFlow(true)
    val showTracksProgressBar: StateFlow<Boolean> get() = _showTracksProgressBar

    private val _showFoundTracksProgressBar = MutableStateFlow(false)
    val showFoundTracksProgressBar: StateFlow<Boolean> get() = _showFoundTracksProgressBar

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    fun onLaunch() {
        viewModelScope.launch {
            _showTracksProgressBar.value = true
            val tracks = getLoadedTracksUseCase()
            _tracks.emit(tracks.toTrackModelList())
            _showTracksProgressBar.value = false
        }
    }

    fun onQueryChange(query: String) {
        _query.value = query
    }

    fun onQuerySubmit() {
        viewModelScope.launch {
            doSafeCall {
                _showFoundTracksProgressBar.value = true
                val foundTracks = searchTracksByNameUseCase(_query.value).toTrackModelList()
                _foundTracks.value = foundTracks
                _showFoundTracksProgressBar.value = false
            }
        }
    }

    fun onFoundTrackClick(trackModel: TrackModel) {
        foundTracks.value?.also { tracks ->
            loadedTracksRouter.navigateToPlayer(tracks.map { it.id }, trackModel.id)
        }
    }

    fun onTrackClick(trackModel: TrackModel) {
        tracks.value?.also { tracks ->
            loadedTracksRouter.navigateToPlayer(tracks.map { it.id }, trackModel.id)
        }
    }
}
