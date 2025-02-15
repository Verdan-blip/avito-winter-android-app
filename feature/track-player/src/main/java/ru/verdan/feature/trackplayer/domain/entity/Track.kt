package ru.verdan.feature.trackplayer.domain.entity

class Track(
    val id: Long,
    val title: String,
    val albumTitle: String?,
    val artist: String,
    val coverUrl: String?,
    val audioUrl: String
)
