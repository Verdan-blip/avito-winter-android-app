package ru.verdan.local.api.entity

data class TrackEntity(
    val id: Long,
    val title: String,
    val albumTitle: String?,
    val artist: String,
    val coverUrl: String?,
    val audioUrl: String
)
