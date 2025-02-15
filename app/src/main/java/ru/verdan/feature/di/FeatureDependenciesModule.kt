package ru.verdan.feature.di

import dagger.Module
import ru.verdan.feature.home.di.HomeDependenciesModule
import ru.verdan.feature.player.di.TrackPlayerDependenciesModule

@Module(
    includes = [
        HomeDependenciesModule::class,
        TrackPlayerDependenciesModule::class
    ]
)
interface FeatureDependenciesModule
