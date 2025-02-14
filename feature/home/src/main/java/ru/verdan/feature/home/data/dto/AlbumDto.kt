package ru.verdan.feature.home.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AlbumDto(
    @SerialName("id") val id: Long,
    @SerialName("cover_small") val coverUrl: String
)
