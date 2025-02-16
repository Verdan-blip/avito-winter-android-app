package ru.verdan.feature.loaded.presention.mapper

import ru.verdan.core.theme.entity.TrackModel
import ru.verdan.feature.loaded.domain.entity.LoadedTrack

fun LoadedTrack.toTrackModel(): TrackModel =
    TrackModel(
        id = id,
        title = title,
        artist = artist,
        coverUrl = coverUrl
    )

fun List<LoadedTrack>.toTrackModelList(): List<TrackModel> =
    map { it.toTrackModel() }
