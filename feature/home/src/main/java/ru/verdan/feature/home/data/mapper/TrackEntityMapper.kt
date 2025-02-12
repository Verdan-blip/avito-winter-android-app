package ru.verdan.feature.home.data.mapper

import ru.verdan.feature.home.data.entity.TrackEntity
import ru.verdan.feature.home.domain.entity.Track

fun TrackEntity.toTrack(): Track =
    Track(
        id = id,
        title = title,
        artist = artist,
        coverUrl = coverUrl
    )

fun List<TrackEntity>.toTrackList(): List<Track> =
    map { it.toTrack() }
