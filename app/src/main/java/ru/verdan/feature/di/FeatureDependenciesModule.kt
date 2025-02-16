package ru.verdan.feature.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.verdan.common.di.component.ComponentDependencies
import ru.verdan.common.di.key.ComponentDependenciesKey
import ru.verdan.di.AppComponent
import ru.verdan.feature.home.di.HomeComponentDependencies
import ru.verdan.feature.home.di.HomeDependenciesModule
import ru.verdan.feature.loaded.di.LoadedTrackDependenciesModule
import ru.verdan.feature.loaded.di.LoadedTracksDependencies
import ru.verdan.feature.player.di.TrackPlayerDependenciesModule
import ru.verdan.feature.trackplayer.di.TrackPlayerDependencies

@Module(
    includes = [
        HomeDependenciesModule::class,
        TrackPlayerDependenciesModule::class,
        LoadedTrackDependenciesModule::class
    ]
)
interface FeatureDependenciesModule {

    @[Binds IntoMap ComponentDependenciesKey(HomeComponentDependencies::class)]
    fun bindsHomeDependencies(appComponent: AppComponent): ComponentDependencies

    @[Binds IntoMap ComponentDependenciesKey(TrackPlayerDependencies::class)]
    fun bindsTrackPlayerDependencies(appComponent: AppComponent): ComponentDependencies

    @[Binds IntoMap ComponentDependenciesKey(LoadedTracksDependencies::class)]
    fun bindsLoadedTrackDependencies(appComponent: AppComponent): ComponentDependencies
}
