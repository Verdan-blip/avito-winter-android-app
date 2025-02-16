package ru.verdan.feature.trackplayer.presentation.service.controller

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.verdan.feature.trackplayer.presentation.entity.TrackModel
import ru.verdan.feature.trackplayer.presentation.service.state.ControllerState

interface Controller {

    val state: StateFlow<ControllerState>

    val error: Flow<Exception>

    fun play()

    fun play(track: TrackModel)

    fun play(tracks: List<TrackModel>)

    fun add(track: TrackModel)

    fun add(tracks: List<TrackModel>)

    fun pause()

    fun playNext()

    fun playPrevious()

    fun seekTo(millis: Long)

    fun clear()
}
