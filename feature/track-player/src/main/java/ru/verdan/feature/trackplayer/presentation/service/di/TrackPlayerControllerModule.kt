package ru.verdan.feature.trackplayer.presentation.service.di

import dagger.Binds
import dagger.Module
import ru.verdan.feature.trackplayer.presentation.service.controller.Controller
import ru.verdan.feature.trackplayer.presentation.service.controller.ControllerImpl

@Module
internal interface TrackPlayerControllerModule {

    @Binds
    fun bindsController(controllerImpl: ControllerImpl): Controller
}
