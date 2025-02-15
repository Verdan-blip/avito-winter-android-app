package ru.verdan.feature.trackplayer.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.verdan.feature.trackplayer.data.dto.TrackDto

internal interface TrackApiService {

    @GET("/track/{id}")
    suspend fun getTrackById(@Path("id") id: Long): TrackDto
}
