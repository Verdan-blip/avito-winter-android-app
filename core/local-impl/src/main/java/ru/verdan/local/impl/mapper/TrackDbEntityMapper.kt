package ru.verdan.local.impl.mapper

import ru.verdan.local.api.entity.TrackEntity
import ru.verdan.local.impl.entity.TrackDbEntity

fun TrackDbEntity.toTrackEntity(): TrackEntity =
    TrackEntity(
        id = id,
        title = title,
        albumTitle = albumTitle,
        artist = artist,
        coverUrl = coverUrl,
        audioUrl = audioUrl
    )

fun List<TrackDbEntity>.toTrackEntityList(): List<TrackEntity> =
    map { it.toTrackEntity() }

fun TrackEntity.toTrackDbEntity(): TrackDbEntity =
    TrackDbEntity(
        id = id,
        title = title,
        albumTitle = albumTitle,
        artist = artist,
        coverUrl = coverUrl,
        audioUrl = audioUrl
    )
