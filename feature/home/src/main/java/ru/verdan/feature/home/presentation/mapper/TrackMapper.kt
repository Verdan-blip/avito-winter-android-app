package ru.verdan.feature.home.presentation.mapper

import ru.verdan.feature.home.domain.entity.Track
import ru.verdan.core.theme.entity.TrackModel

internal fun Track.toTrackModelList(): TrackModel =
    TrackModel(
        id = id,
        title = title,
        artist = artist,
        coverUrl = coverUrl
    )

internal fun List<Track>.toTrackModelList(): List<TrackModel> =
    map { it.toTrackModelList() }
