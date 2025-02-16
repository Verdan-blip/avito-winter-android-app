package ru.verdan.local.impl.di

import dagger.Binds
import dagger.Module
import ru.verdan.local.api.repository.TrackLocalRepository
import ru.verdan.local.impl.repository.TrackLocalRepositoryImpl

@Module
internal interface LocalRepositoryModule {

    @Binds
    fun bindsTrackRepository(trackRepositoryImpl: TrackLocalRepositoryImpl): TrackLocalRepository
}
