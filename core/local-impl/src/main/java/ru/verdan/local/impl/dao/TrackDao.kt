package ru.verdan.local.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.verdan.local.impl.entity.TrackDbEntity

@Dao
interface TrackDao {

    @Query("SELECT * FROM tracks")
    suspend fun getAll(): List<TrackDbEntity>

    @Insert
    suspend fun insert(track: TrackDbEntity)

    @Query("SELECT * FROM tracks WHERE " +
            "title LIKE '%' || :name || '%' OR " +
            "artist LIKE '%' || :name || '%' OR " +
            "album_title LIKE '%' || :name || '%'"
    )
    suspend fun getAllByName(name: String): List<TrackDbEntity>

    @Query("SELECT * FROM tracks WHERE id = :id")
    suspend fun getById(id: Long): TrackDbEntity?
}
