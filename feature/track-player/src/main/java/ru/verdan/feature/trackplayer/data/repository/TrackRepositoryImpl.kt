package ru.verdan.feature.trackplayer.data.repository

import ru.verdan.feature.trackplayer.data.datasource.TrackDataSource
import ru.verdan.feature.trackplayer.data.mapper.toTrack
import ru.verdan.feature.trackplayer.domain.entity.Track
import ru.verdan.feature.trackplayer.domain.mapper.toTrack
import ru.verdan.feature.trackplayer.domain.repository.TrackRepository
import ru.verdan.local.api.repository.TrackLocalRepository
import javax.inject.Inject

internal class TrackRepositoryImpl @Inject constructor(
    private val remoteDataSource: TrackDataSource,
    private val trackLocalRepository: TrackLocalRepository
) : TrackRepository {

    override suspend fun getTrackById(id: Long): Track {
        return trackLocalRepository.getById(id)?.toTrack(isSaved = true)
            ?: remoteDataSource.getTrackById(id).toTrack(isSaved = false)
    }
}
