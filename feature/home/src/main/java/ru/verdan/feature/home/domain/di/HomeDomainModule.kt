package ru.verdan.feature.home.domain.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import ru.verdan.common.di.module.IODispatcher
import ru.verdan.feature.home.domain.repository.TrackRepository
import ru.verdan.feature.home.domain.usecase.GetChartTracksUseCase
import ru.verdan.feature.home.domain.usecase.SearchTrackByNameUseCase

@Module
internal class HomeDomainModule {

    @Provides
    fun provideGetChartsUseCase(
        trackRepository: TrackRepository,
        @IODispatcher dispatcher: CoroutineDispatcher
    ): GetChartTracksUseCase = GetChartTracksUseCase(
        trackRepository, dispatcher
    )

    @Provides
    fun provideSearchByNameUseCase(
        trackRepository: TrackRepository,
        @IODispatcher dispatcher: CoroutineDispatcher
    ): SearchTrackByNameUseCase = SearchTrackByNameUseCase(
        trackRepository, dispatcher
    )
}
