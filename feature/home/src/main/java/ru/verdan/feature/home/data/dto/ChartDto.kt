package ru.verdan.feature.home.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class ChartDto(
    @SerialName("tracks") val tracksData: TracksDto
)

@Serializable
internal class TracksDto(
    @SerialName("data") val tracks: List<TrackDto>
)
