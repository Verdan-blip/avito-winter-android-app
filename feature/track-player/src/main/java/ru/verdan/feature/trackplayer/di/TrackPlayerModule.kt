package ru.verdan.feature.trackplayer.di

import dagger.Module
import ru.verdan.common.di.module.CommonModule
import ru.verdan.feature.trackplayer.data.di.TrackPlayerDataModule

@Module(
    includes = [
        TrackPlayerDataModule::class,
        CommonModule::class
    ]
)
interface TrackPlayerModule
