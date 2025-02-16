package ru.verdan.feature.trackplayer.data.repository

import ru.verdan.feature.trackplayer.data.datasource.TrackDataSource
import ru.verdan.feature.trackplayer.data.mapper.toTrack
import ru.verdan.feature.trackplayer.domain.entity.Track
import ru.verdan.feature.trackplayer.domain.mapper.toTrack
import ru.verdan.feature.trackplayer.domain.repository.TrackRepository

internal class TrackRepositoryImpl(
    private val dataSource: TrackDataSource,
    private val trackLocalRepository: ru.verdan.local.api.repository.TrackLocalRepository
) : TrackRepository {

    override suspend fun getTrackById(id: Long): Track {
        val localTrack = trackLocalRepository.getById(id)
        return localTrack?.toTrack(isSaved = true)
            ?: dataSource.getTrackById(id).toTrack(isSaved = false)
    }
}
