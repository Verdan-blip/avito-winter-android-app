package ru.verdan.feature.home.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class SearchDto(
    @SerialName("data") val data: List<TrackDto>,
    @SerialName("total") val total: Int,
    @SerialName("prev") val prev: String? = null,
    @SerialName("next") val next: String?
)
