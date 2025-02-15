package ru.verdan.feature.trackplayer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class ArtistDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String
)
