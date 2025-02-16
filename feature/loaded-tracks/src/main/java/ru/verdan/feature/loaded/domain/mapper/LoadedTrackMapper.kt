package ru.verdan.feature.loaded.domain.mapper

import ru.verdan.feature.loaded.domain.entity.LoadedTrack
import ru.verdan.local.api.entity.TrackEntity

fun TrackEntity.toLoadedTrack(): LoadedTrack =
    LoadedTrack(
        id = id,
        title = title,
        artist = artist,
        coverUrl = coverUrl
    )

fun List<TrackEntity>.toLoadedTrackList(): List<LoadedTrack> =
    map { it.toLoadedTrack() }
