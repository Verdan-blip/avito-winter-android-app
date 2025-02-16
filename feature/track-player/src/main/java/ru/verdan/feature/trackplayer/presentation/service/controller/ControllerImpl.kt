package ru.verdan.feature.trackplayer.presentation.service.controller

import androidx.media3.common.Player
import androidx.media3.session.MediaController
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.guava.await
import kotlinx.coroutines.launch
import ru.verdan.common.scope.applicationScope
import ru.verdan.feature.trackplayer.presentation.entity.TrackModel
import ru.verdan.feature.trackplayer.presentation.service.mapper.toMediaItem
import ru.verdan.feature.trackplayer.presentation.service.mapper.toMediaItemList
import ru.verdan.feature.trackplayer.presentation.service.mapper.toTrackModel
import ru.verdan.feature.trackplayer.presentation.service.state.ControllerState
import ru.verdan.feature.trackplayer.presentation.service.util.currentMediaItemAsFlow
import ru.verdan.feature.trackplayer.presentation.service.util.currentPlayingItemDurationAsFlow
import ru.verdan.feature.trackplayer.presentation.service.util.currentPlayingPositionAsFlow
import ru.verdan.feature.trackplayer.presentation.service.util.errorsAsFlow
import ru.verdan.feature.trackplayer.presentation.service.util.hasNextItemAsFlow
import ru.verdan.feature.trackplayer.presentation.service.util.hasPreviousItemAsFlow
import ru.verdan.feature.trackplayer.presentation.service.util.isPlayingAsFlow
import javax.inject.Inject

class ControllerImpl @Inject constructor(
    private val mediaControllerFuture: ListenableFuture<MediaController>,
) : Controller {

    private var player: Player? = null

    private val _state = MutableStateFlow(ControllerState())
    override val state: StateFlow<ControllerState> get() = _state

    private val _error = MutableSharedFlow<Exception>()
    override val error: Flow<Exception> get() = _error

    init {
        applicationScope.launch {
            initPlayer()
            launch { collectIsPlayingFlow() }
            launch { collectCurrentMediaItemFlow() }
            launch { collectCurrentPlayingPositionFlow() }
            launch { collectCurrentPlayingItemDurationFlow() }
            launch { collectHasPreviousItemAsFlow() }
            launch { collectHasNextItemAsFlow() }
            launch { collectErrorFlow() }
        }
    }

    override fun play() {
        player?.play()
    }

    override fun play(track: TrackModel) {
        player?.setMediaItem(track.toMediaItem())
        player?.play()
    }

    override fun play(tracks: List<TrackModel>) {
        player?.setMediaItems(tracks.toMediaItemList())
    }

    override fun pause() {
        player?.pause()
    }

    override fun playNext() {
        player?.seekToNext()
    }

    override fun playPrevious() {
        player?.seekToPrevious()
    }

    override fun seekTo(millis: Long) {
        player?.seekTo(millis)
    }

    override fun add(track: TrackModel) {
        player?.addMediaItem(track.toMediaItem())
    }

    override fun add(tracks: List<TrackModel>) {
        player?.addMediaItems(tracks.toMediaItemList())
    }

    override fun clear() {
        player?.clearMediaItems()
    }

    private suspend fun initPlayer() {
        player = mediaControllerFuture.await()
        player?.prepare()
    }

    private suspend fun collectIsPlayingFlow() {
        player?.apply {
            isPlayingAsFlow()
                .filterNotNull()
                .collect { isPlaying ->
                    _state.emit(_state.value.copy(isPlaying = isPlaying))
                }
        }
    }

    private suspend fun collectCurrentMediaItemFlow() {
        player?.apply {
            currentMediaItemAsFlow()
                .filterNotNull()
                .collect { mediaItem ->
                    val playableItem = mediaItem.toTrackModel()
                    _state.emit(_state.value.copy(currentPlayable = playableItem))
                }
        }
    }

    private suspend fun collectCurrentPlayingItemDurationFlow() {
        player?.apply {
            currentPlayingItemDurationAsFlow()
                .filterNotNull()
                .filter { it > 0 }
                .collect { duration ->
                    _state.emit(_state.value.copy(duration = duration))
                }
        }
    }

    private suspend fun collectCurrentPlayingPositionFlow() {
        player?.apply {
            currentPlayingPositionAsFlow()
                .filterNotNull()
                .collect { positionInMs ->
                    _state.emit(_state.value.copy(positionMs = positionInMs))
                }
        }
    }

    private suspend fun collectErrorFlow() {
        player?.apply {
            errorsAsFlow()
                .filterNotNull()
                .collect { error ->
                    _error.emit(error)
                }
        }
    }

    private suspend fun collectHasNextItemAsFlow() {
        player?.apply {
            hasNextItemAsFlow()
                .collect { hasNext ->
                    _state.emit(_state.value.copy(hasNextPlayable = hasNext))
                }
        }
    }

    private suspend fun collectHasPreviousItemAsFlow() {
        player?.apply {
            hasPreviousItemAsFlow()
                .collect { hasPrevious ->
                    _state.emit(_state.value.copy(hasPreviousPlayable = hasPrevious))
                }
        }
    }
}
