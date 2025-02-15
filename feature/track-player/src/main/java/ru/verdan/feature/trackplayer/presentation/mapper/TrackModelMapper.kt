package ru.verdan.feature.trackplayer.presentation.mapper

import ru.verdan.feature.trackplayer.domain.entity.Track
import ru.verdan.feature.trackplayer.presentation.entity.TrackModel

internal fun Track.toTrackModel(): TrackModel =
    TrackModel(
        id = id,
        title = title,
        audioUrl = audioUrl,
        albumTitle = albumTitle,
        coverUrl = coverUrl,
        artist = artist
    )
