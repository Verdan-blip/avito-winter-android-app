package ru.verdan.feature.trackplayer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class AlbumDto(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String?,
    @SerialName("cover") val cover: String?
)
