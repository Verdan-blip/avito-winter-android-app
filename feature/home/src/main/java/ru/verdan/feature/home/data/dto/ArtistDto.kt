package ru.verdan.feature.home.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ArtistDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String
)
