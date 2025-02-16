package ru.verdan.local.impl.datasource

import ru.verdan.local.api.entity.TrackEntity
import ru.verdan.local.impl.dao.TrackDao
import ru.verdan.local.impl.mapper.toTrackDbEntity
import ru.verdan.local.impl.mapper.toTrackEntity
import ru.verdan.local.impl.mapper.toTrackEntityList
import javax.inject.Inject

class TrackDataSource @Inject constructor(
    private val trackDao: TrackDao
) {

    suspend fun getAll(): List<TrackEntity> {
        return trackDao.getAll().toTrackEntityList()
    }

    suspend fun insert(track: TrackEntity) {
        trackDao.insert(track.toTrackDbEntity())
    }

    suspend fun searchByQuery(query: String): List<TrackEntity> {
        return trackDao.getAllByName(query).toTrackEntityList()
    }

    suspend fun getById(id: Long): TrackEntity? {
        return trackDao.getById(id)?.toTrackEntity()
    }
}
