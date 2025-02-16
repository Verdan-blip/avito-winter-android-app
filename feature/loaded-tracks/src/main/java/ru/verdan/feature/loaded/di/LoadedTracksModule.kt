package ru.verdan.feature.loaded.di

import dagger.Module
import ru.verdan.common.di.module.CommonModule
import ru.verdan.feature.loaded.presention.di.LoadedTracksPresentationModule

@Module(
    includes = [
        LoadedTracksPresentationModule::class,
        CommonModule::class
    ]
)
interface LoadedTracksModule
