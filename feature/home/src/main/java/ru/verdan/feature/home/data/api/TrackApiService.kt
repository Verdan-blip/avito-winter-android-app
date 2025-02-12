package ru.verdan.feature.home.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.verdan.feature.home.data.dto.ChartDto
import ru.verdan.feature.home.data.dto.SearchDto

internal interface TrackApiService {

    @GET("/chart")
    fun getChartTracks(): ChartDto

    @GET("/search")
    fun searchTrackByName(
        @Query("q") name: String,
        @Query("index") index: Int = 0
    ): SearchDto
}
