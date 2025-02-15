package ru.verdan.feature.trackplayer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class TrackDto(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("album") val album: AlbumDto,
    @SerialName("artist") val artist: ArtistDto,
    @SerialName("link") val audioUrl: String
)
