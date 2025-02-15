package ru.verdan.local.impl.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.verdan.local.impl.dao.TrackDao
import ru.verdan.local.impl.entity.TrackDbEntity

@Database(
    entities = [TrackDbEntity::class],
    version = 1
)
internal abstract class AppDatabase : RoomDatabase() {

    abstract val trackDao: TrackDao
}
