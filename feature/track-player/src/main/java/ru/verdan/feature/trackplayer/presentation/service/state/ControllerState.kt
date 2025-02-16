package ru.verdan.feature.trackplayer.presentation.service.state

import ru.verdan.feature.trackplayer.presentation.entity.TrackModel

data class ControllerState(
    val currentPlayable: TrackModel? = null,
    val positionMs: Long = 0,
    val duration: Long = 0,
    val isPlaying: Boolean = false,
    val hasNextPlayable: Boolean = false,
    val hasPreviousPlayable: Boolean = false
)
