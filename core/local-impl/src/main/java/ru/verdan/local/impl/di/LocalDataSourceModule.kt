package ru.verdan.local.impl.di

import dagger.Module
import dagger.Provides
import ru.verdan.local.impl.database.AppDatabase
import ru.verdan.local.impl.datasource.TrackDataSource

@Module
internal class LocalDataSourceModule {

    @Provides
    fun providesTrackDataSource(db: AppDatabase): TrackDataSource {
        return TrackDataSource(db.trackDao)
    }
}
