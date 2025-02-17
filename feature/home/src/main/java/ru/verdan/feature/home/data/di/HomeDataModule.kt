package ru.verdan.feature.home.data.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.verdan.feature.home.data.api.TrackApiService
import ru.verdan.feature.home.data.datasource.TrackChartsDataSource
import ru.verdan.feature.home.data.datasource.TrackSearchPagingSource
import ru.verdan.feature.home.data.repository.TrackRepositoryImpl
import ru.verdan.feature.home.domain.repository.TrackRepository

@Module
internal class HomeDataModule {

    @Provides
    fun provideTrackApiService(retrofit: Retrofit): TrackApiService {
        return retrofit.create(TrackApiService::class.java)
    }

    @Provides
    fun provideTrackRepository(
        dataSource: TrackChartsDataSource,
        factory: TrackSearchPagingSource.Companion.Factory
    ): TrackRepository {
        return TrackRepositoryImpl(dataSource, factory)
    }
}
