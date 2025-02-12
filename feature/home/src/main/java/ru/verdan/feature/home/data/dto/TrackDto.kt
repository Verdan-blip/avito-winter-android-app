package ru.verdan.feature.home.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class TrackDto(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("artists") val artist: String,
    @SerialName("preview") val preview: String
)
