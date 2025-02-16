package ru.verdan.feature.trackplayer.presentation.di

import dagger.Module

@Module(
    includes = [
        TrackPlayerPlayerModule::class,
        TrackPlayerSessionModule::class
    ]
)
internal class TrackPlayerPresentationModule
