package ru.verdan.feature.trackplayer.domain.mapper

import ru.verdan.feature.trackplayer.domain.entity.Track
import ru.verdan.local.api.entity.TrackEntity

fun Track.toTrackEntity(): TrackEntity =
    TrackEntity(
        id = id,
        title = title,
        albumTitle = albumTitle,
        artist = artist,
        coverUrl = coverUrl,
        audioUrl = audioUrl
    )

fun TrackEntity.toTrack(isSaved: Boolean = true): Track =
    Track(
        id = id,
        title = title,
        albumTitle = albumTitle,
        artist = artist,
        coverUrl = coverUrl,
        audioUrl = audioUrl,
        isSaved = isSaved
    )
