package ru.verdan.feature.trackplayer.di

import dagger.Component
import ru.verdan.common.di.component.DiComponent
import ru.verdan.common.di.scopes.FeatureScope
import ru.verdan.feature.trackplayer.presentation.TrackPlayerViewModel
import ru.verdan.feature.trackplayer.presentation.service.PlayerService

@FeatureScope
@Component(
    modules = [TrackPlayerModule::class],
    dependencies = [TrackPlayerDependencies::class]
)
interface TrackPlayerComponent : DiComponent {

    val factoryOfViewFactory: TrackPlayerViewModel.Companion.Factory.Factory

    fun inject(playerService: PlayerService)

    @Component.Factory
    interface Factory {

        fun create(
            trackPlayerDependencies: TrackPlayerDependencies
        ): TrackPlayerComponent
    }
}
