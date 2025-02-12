package ru.verdan.feature.home.data.datasource

import ru.verdan.feature.home.data.api.TrackApiService
import ru.verdan.feature.home.data.entity.TrackEntity
import ru.verdan.feature.home.data.mapper.toTrackEntityList
import javax.inject.Inject

internal class TrackChartsDataSource @Inject constructor(
    private val trackApiService: TrackApiService
) {

    fun getChartTracks(): List<TrackEntity> {
        return trackApiService.getChartTracks()
            .tracksData
            .tracks
            .toTrackEntityList()
    }
}
