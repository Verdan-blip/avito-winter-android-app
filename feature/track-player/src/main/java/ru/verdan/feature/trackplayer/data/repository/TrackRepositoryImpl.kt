package ru.verdan.feature.trackplayer.data.repository

import ru.verdan.feature.trackplayer.data.datasource.TrackDataSource
import ru.verdan.feature.trackplayer.data.mapper.toTrack
import ru.verdan.feature.trackplayer.domain.entity.Track
import ru.verdan.feature.trackplayer.domain.repository.TrackRepository

internal class TrackRepositoryImpl(
    private val dataSource: TrackDataSource
) : TrackRepository {

    override suspend fun getTrackById(id: Long): Track {
        return dataSource.getTrackById(id).toTrack()
    }
}
