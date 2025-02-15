package ru.verdan.local.impl.di

import dagger.Binds
import dagger.Module
import ru.verdan.local.api.repository.TrackRepository
import ru.verdan.local.impl.repository.TrackRepositoryImpl

@Module
internal interface LocalRepositoryModule {

    @Binds
    fun bindsTrackRepository(trackRepositoryImpl: TrackRepositoryImpl): TrackRepository
}
