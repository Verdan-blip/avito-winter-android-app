package ru.verdan.feature.home.data.mapper

import ru.verdan.feature.home.data.dto.TrackDto
import ru.verdan.feature.home.data.entity.TrackEntity

internal fun TrackDto.toTrackEntity(): TrackEntity =
    TrackEntity(
        id = id,
        title = title,
        artist = artist,
        coverUrl = preview
    )

internal fun List<TrackDto>.toTrackEntityList(): List<TrackEntity> =
    map { it.toTrackEntity() }
