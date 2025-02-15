package ru.verdan.feature.trackplayer.data.mapper

import ru.verdan.feature.trackplayer.data.entity.TrackEntity
import ru.verdan.feature.trackplayer.domain.entity.Track

internal fun TrackEntity.toTrack(): Track =
    Track(
        id = id,
        title = title,
        albumTitle = albumTitle,
        artist = artist,
        coverUrl = coverUrl,
        audioUrl = audioUrl
    )
