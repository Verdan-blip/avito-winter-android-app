package ru.verdan.feature.trackplayer.di

import dagger.Component
import ru.verdan.common.di.component.DiComponent
import ru.verdan.common.di.scopes.FeatureScope
import ru.verdan.feature.trackplayer.presentation.TrackPlayerViewModel

@FeatureScope
@Component(
    modules = [TrackPlayerModule::class],
    dependencies = [TrackPlayerDependencies::class]
)
interface TrackPlayerComponent : DiComponent {

    val factoryOfViewFactory: TrackPlayerViewModel.Companion.Factory.Factory

    @Component.Factory
    interface Factory {

        fun create(
            trackPlayerDependencies: TrackPlayerDependencies
        ): TrackPlayerComponent
    }
}
