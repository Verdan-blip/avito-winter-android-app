package ru.verdan.core.theme.entity

data class TrackModel(
    val id: Long,
    val title: String,
    val artists: String,
    val coverUrl: String?
)
