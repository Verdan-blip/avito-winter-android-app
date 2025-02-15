package ru.verdan.player.impl.controller

import androidx.media3.common.Player
import androidx.media3.session.MediaController
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.guava.await
import kotlinx.coroutines.launch
import ru.verdan.common.di.module.ApplicationCoroutineScope
import ru.verdan.player.controller.Controller
import ru.verdan.player.entity.PlayableItem
import ru.verdan.player.impl.mapper.toMediaItem
import ru.verdan.player.impl.mapper.toMusicItem
import ru.verdan.player.impl.utils.currentMediaItemAsFlow
import ru.verdan.player.impl.utils.currentPlayingItemDurationAsFlow
import ru.verdan.player.impl.utils.currentPlayingPositionAsFlow
import ru.verdan.player.impl.utils.isPlayingAsFlow
import ru.verdan.player.state.ControllerState
import javax.inject.Inject

class ControllerImpl @Inject constructor(
    private val mediaControllerFuture: ListenableFuture<MediaController>,
    @ApplicationCoroutineScope private val applicationScope: CoroutineScope
) : Controller {

    private var player: Player? = null

    private val _state = MutableStateFlow(ControllerState())
    override val state: StateFlow<ControllerState> get() = _state

    init {
        applicationScope.launch {
            initPlayer()
            launch { collectIsPlayingFlow() }
            launch { collectCurrentMediaItemFlow() }
            launch { collectCurrentPlayingPositionFlow() }
            launch { collectCurrentPlayingItemDurationFlow() }
        }
    }

    override fun play() {
        player?.play()
    }

    override fun play(item: PlayableItem) {
        player?.setMediaItem(item.toMediaItem())
        player?.play()
    }

    override fun pause() {
        player?.pause()
    }

    override fun playNext() {

    }

    override fun playPrevious() {

    }

    override fun seekTo(millis: Long) {
        player?.seekTo(millis)
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
                    val playableItem = mediaItem.toMusicItem()
                    _state.emit(_state.value.copy(currentPlayable = playableItem))
                }
        }
    }

    private suspend fun collectCurrentPlayingItemDurationFlow() {
        player?.apply {
            currentPlayingItemDurationAsFlow()
                .filterNotNull()
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
}
