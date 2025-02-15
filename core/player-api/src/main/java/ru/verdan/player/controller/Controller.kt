package ru.verdan.player.controller

import kotlinx.coroutines.flow.StateFlow
import ru.verdan.player.entity.PlayableItem
import ru.verdan.player.state.ControllerState

interface Controller {

    val state: StateFlow<ControllerState>

    fun play()

    fun play(item: PlayableItem)

    fun pause()

    fun playNext()

    fun playPrevious()

    fun seekTo(millis: Long)
}
