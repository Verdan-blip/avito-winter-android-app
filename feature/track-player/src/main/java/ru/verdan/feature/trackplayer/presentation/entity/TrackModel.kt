package ru.verdan.feature.trackplayer.presentation.entity

data class TrackModel(
    val id: Long,
    val title: String,
    val albumTitle: String?,
    val artist: String,
    val coverUrl: String?,
    val audioUrl: String,
    val isSaved: Boolean
)
