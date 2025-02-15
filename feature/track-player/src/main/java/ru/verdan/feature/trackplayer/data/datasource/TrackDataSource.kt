package ru.verdan.feature.trackplayer.data.datasource

import ru.verdan.feature.trackplayer.data.api.TrackApiService
import ru.verdan.feature.trackplayer.data.entity.TrackEntity
import ru.verdan.feature.trackplayer.data.mapper.toTrackEntity

internal class TrackDataSource(
    private val trackApiService: TrackApiService
) {

    suspend fun getTrackById(id: Long): TrackEntity {
        return trackApiService.getTrackById(id).toTrackEntity()
    }
}
