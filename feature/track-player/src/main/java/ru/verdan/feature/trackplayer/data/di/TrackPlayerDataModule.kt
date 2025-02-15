package ru.verdan.feature.trackplayer.data.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.verdan.feature.trackplayer.data.api.TrackApiService
import ru.verdan.feature.trackplayer.data.datasource.TrackDataSource
import ru.verdan.feature.trackplayer.data.repository.TrackRepositoryImpl
import ru.verdan.feature.trackplayer.domain.repository.TrackRepository

@Module
internal class TrackPlayerDataModule {

    @Provides
    fun provideTrackApiService(retrofit: Retrofit): TrackApiService {
        return retrofit.create(TrackApiService::class.java)
    }

    @Provides
    fun provideTrackDataSource(trackApiService: TrackApiService): TrackDataSource {
        return TrackDataSource(trackApiService)
    }

    @Provides
    fun provideTrackRepository(
        trackDataSource: TrackDataSource,
        trackRepository: ru.verdan.local.api.repository.TrackRepository
    ): TrackRepository {
        return TrackRepositoryImpl(trackDataSource, trackRepository)
    }
}
