package ru.verdan.feature.trackplayer.presentation.service.controller

import kotlinx.coroutines.flow.StateFlow
import ru.verdan.feature.trackplayer.presentation.entity.TrackModel
import ru.verdan.feature.trackplayer.presentation.service.state.ControllerState

interface Controller {

    val state: StateFlow<ControllerState>

    fun play()

    fun play(track: TrackModel)

    fun pause()

    fun playNext()

    fun playPrevious()

    fun seekTo(millis: Long)
}
