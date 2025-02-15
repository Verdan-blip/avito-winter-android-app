package ru.verdan.player.state

import ru.verdan.player.entity.PlayableItem

data class ControllerState(
    val currentPlayable: PlayableItem? = null,
    val positionMs: Long = 0,
    val duration: Long = 0,
    val isPlaying: Boolean = false
)
