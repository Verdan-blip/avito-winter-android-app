package ru.verdan.player.entity

data class PlayableItem(
    val id: Long,
    val title: String,
    val albumTitle: String?,
    val audioUrl: String,
    val coverUrl: String?,
    val artist: String
)
