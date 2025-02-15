package ru.verdan.feature.trackplayer.presentation.mapper

import ru.verdan.feature.trackplayer.presentation.entity.TrackModel
import ru.verdan.player.entity.PlayableItem

internal fun PlayableItem.toTrackModel(): TrackModel =
    TrackModel(
        id = id,
        title = title,
        audioUrl = audioUrl,
        albumTitle = albumTitle,
        coverUrl = coverUrl,
        artist = artist
    )

internal fun TrackModel.toPlayableItem(): PlayableItem =
    PlayableItem(
        id = id,
        title = title,
        audioUrl = audioUrl,
        albumTitle = albumTitle,
        coverUrl = coverUrl,
        artist = artist
    )
