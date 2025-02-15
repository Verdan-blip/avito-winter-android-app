package ru.verdan.feature.trackplayer.domain.repository

import ru.verdan.feature.trackplayer.domain.entity.Track

interface TrackRepository {

    suspend fun getTrackById(id: Long): Track
}
