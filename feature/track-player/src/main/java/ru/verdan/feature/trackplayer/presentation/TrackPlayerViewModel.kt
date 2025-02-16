package ru.verdan.feature.trackplayer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.verdan.common.base.BaseViewModel
import ru.verdan.common.base.mvvm.BaseEvent
import ru.verdan.common.resource.ResourceProvider
import ru.verdan.common.util.millisToMmSsString
import ru.verdan.common.util.progressToTime
import ru.verdan.common.util.timeToProgress
import ru.verdan.feature.loaded.R
import ru.verdan.feature.trackplayer.domain.usecase.DownloadTrackUseCase
import ru.verdan.feature.trackplayer.domain.usecase.GetTrackUseCase
import ru.verdan.feature.trackplayer.domain.usecase.SaveDownloadedTrackUseCase
import ru.verdan.feature.trackplayer.presentation.entity.TrackModel
import ru.verdan.feature.trackplayer.presentation.mapper.toTrack
import ru.verdan.feature.trackplayer.presentation.mapper.toTrackModel
import ru.verdan.feature.trackplayer.presentation.service.controller.Controller

class TrackPlayerViewModel @AssistedInject constructor(
    @Assisted("queueTrackIds") private val queueTrackIds: List<Long>,
    private val controller: Controller,
    private val getTrackUseCase: GetTrackUseCase,
    private val downloadTrackUseCase: DownloadTrackUseCase,
    private val saveDownloadedTrackUseCase: SaveDownloadedTrackUseCase,
    resourceProvider: ResourceProvider
) : BaseViewModel(resourceProvider) {

    private val _currentTrack = MutableStateFlow<TrackModel?>(null)
    val currentTrack: StateFlow<TrackModel?> get() = _currentTrack

    private val _isPLaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> get() = _isPLaying

    private val _currentProgress = MutableStateFlow(0)
    val currentProgress: StateFlow<Int> get() = _currentProgress

    private val _currentTimeProgress = MutableStateFlow(0L.millisToMmSsString())
    val currentTimeProgress: StateFlow<String> get() = _currentTimeProgress

    private val _currentTimeDuration = MutableStateFlow(0L.millisToMmSsString())
    val currentTimeDuration: StateFlow<String> get() = _currentTimeDuration

    private val _canPlayPrevious = MutableStateFlow(true)
    val canPlayPrevious: StateFlow<Boolean> get() = _canPlayPrevious

    private val _canPlayNext = MutableStateFlow(true)
    val canPlayNext: StateFlow<Boolean> get() = _canPlayNext

    private var shouldTrackProgress: Boolean = true

    private var index: Int = 0
    private val downloadsMap: MutableMap<Long, TrackModel> = mutableMapOf()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch { collectControllerState() }
            launch { collectControllerErrors() }
        }
    }

    fun onLaunch() {
        viewModelScope.launch {
            doSafeCall {
                if (controller.state.value.currentPlayable == null) {
                    val track = fetchTrack(queueTrackIds[index])
                    controller.play(track)
                }
            }
        }
    }

    fun onPlayPause() {
        if (controller.state.value.isPlaying) {
            controller.pause()
        } else {
            controller.play()
        }
    }

    fun onPlayNext() {
        viewModelScope.launch {
            if (index != queueTrackIds.lastIndex) {
                play(queueTrackIds[--index])
                _canPlayNext.value = true
            } else {
                _canPlayNext.value = false
            }
        }
    }

    fun onPlayPrevious() {
        viewModelScope.launch {
            if (index != 0) {
                play(queueTrackIds[--index])
                _canPlayNext.value = true
            } else {
                _canPlayNext.value = false
            }
        }
    }

    fun onProgressChange(progress: Int) {
        shouldTrackProgress = false
        _currentProgress.value = progress
        _currentTimeProgress.value = progress
            .progressToTime(controller.state.value.duration)
            .millisToMmSsString()
    }

    fun onProgressChanged() {
        shouldTrackProgress = true
        controller.seekTo(_currentProgress.value
            .progressToTime(controller.state.value.duration)
        )
    }

    fun onDownloadTrack() {
        viewModelScope.launch {
            _currentTrack.value?.also { track ->
                val downloadId = downloadTrackUseCase(track.toTrack())
                downloadsMap[downloadId] = track
            }
        }
    }

    fun onDownloadFinished(id: Long, localUri: String) {
        viewModelScope.launch {
            downloadsMap.remove(id)?.also { track ->
                saveDownloadedTrackUseCase(track.copy(audioUrl = localUri).toTrack())
                _currentTrack.value = _currentTrack.value?.copy(isSaved = true)
            }
        }
    }

    private suspend fun fetchTrack(id: Long): TrackModel {
        return getTrackUseCase(id).toTrackModel()
    }

    private suspend fun play(id: Long) {
        doSafeCall {
            val track = fetchTrack(id)
            controller.play(track)
        }
    }

    private suspend fun collectControllerState() {
        controller.state.collect { state ->
            _currentTrack.value = state.currentPlayable
            if (shouldTrackProgress) {
                _isPLaying.value = state.isPlaying
                _currentTimeDuration.value = state.duration.millisToMmSsString()
                _currentProgress.value = state.positionMs.timeToProgress(state.duration)
                _currentTimeProgress.value = state.positionMs.millisToMmSsString()
            }
        }
    }

    private suspend fun collectControllerErrors() {
        controller.error.collect {
            emitEvent(BaseEvent.ShowSnackbar(
                resourceProvider.getString(R.string.playback_error_format)
            ))
        }
    }

    companion object {

        class Factory @AssistedInject constructor(
            @Assisted("queueTrackIds") private val queueTrackIds: List<Long>,
            private val controller: Controller,
            private val getTrackUseCase: GetTrackUseCase,
            private val downloadTrackUseCase: DownloadTrackUseCase,
            private val saveDownloadedTrackUseCase: SaveDownloadedTrackUseCase,
            private val resourceProvider: ResourceProvider
        ) : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TrackPlayerViewModel(
                    queueTrackIds = queueTrackIds,
                    controller = controller,
                    getTrackUseCase = getTrackUseCase,
                    downloadTrackUseCase = downloadTrackUseCase,
                    saveDownloadedTrackUseCase = saveDownloadedTrackUseCase,
                    resourceProvider = resourceProvider
                ) as T
            }

            @AssistedFactory interface Factory {

                fun create(
                    @Assisted("queueTrackIds") queueTrackIds: List<Long>
                ): Companion.Factory
            }
        }
    }
}
