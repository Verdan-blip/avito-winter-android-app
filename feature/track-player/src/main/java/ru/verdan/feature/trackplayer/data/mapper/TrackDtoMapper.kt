package ru.verdan.feature.trackplayer.data.mapper

import ru.verdan.feature.trackplayer.data.dto.TrackDto
import ru.verdan.feature.trackplayer.data.entity.TrackEntity

internal fun TrackDto.toTrackEntity(): TrackEntity =
    TrackEntity(
        id = id,
        title = title,
        albumTitle = album.title,
        artist = artist.name,
        coverUrl = album.cover,
        audioUrl = audioUrl
    )
