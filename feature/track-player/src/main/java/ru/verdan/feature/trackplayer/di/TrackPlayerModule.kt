package ru.verdan.feature.trackplayer.di

import dagger.Module
import ru.verdan.common.di.module.CommonModule
import ru.verdan.feature.trackplayer.data.di.TrackPlayerDataModule
import ru.verdan.feature.trackplayer.presentation.di.TrackPlayerPresentationModule
import ru.verdan.feature.trackplayer.presentation.service.di.TrackPlayerControllerModule

@Module(
    includes = [
        TrackPlayerDataModule::class,
        TrackPlayerPresentationModule::class,
        TrackPlayerControllerModule::class,
        CommonModule::class
    ]
)
class TrackPlayerModule
