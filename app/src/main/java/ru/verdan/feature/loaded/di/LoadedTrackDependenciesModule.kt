package ru.verdan.feature.loaded.di

import dagger.Binds
import dagger.Module
import ru.verdan.feature.loaded.LoadedTracksRouterImpl
import ru.verdan.feature.loaded.presention.LoadedTracksRouter

@Module
interface LoadedTrackDependenciesModule {

    @Binds
    fun bindsLoadedTracksRouter(
        routerImpl: LoadedTracksRouterImpl
    ): LoadedTracksRouter
}
