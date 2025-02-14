package ru.verdan.feature.home.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class TrackDto(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("artist") val artist: ArtistDto,
    @SerialName("album") val album: AlbumDto
)
