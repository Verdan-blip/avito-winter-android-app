package ru.verdan.local.api.repository

import ru.verdan.local.api.entity.TrackEntity

interface TrackRepository {

    suspend fun downloadTrackFile(trackEntity: TrackEntity): Long

    suspend fun saveTrack(trackEntity: TrackEntity)

    suspend fun getAll(): List<TrackEntity>

    suspend fun getAllByName(title: String): List<TrackEntity>

    suspend fun getById(id: Long): TrackEntity?
}
