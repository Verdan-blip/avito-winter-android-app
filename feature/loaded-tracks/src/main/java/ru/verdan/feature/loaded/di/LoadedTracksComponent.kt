package ru.verdan.feature.loaded.di

import dagger.Component
import ru.verdan.common.di.component.BaseComponent
import ru.verdan.common.di.scopes.FeatureScope
import ru.verdan.feature.loaded.presention.LoadedTracksFragment

@FeatureScope
@Component(
    modules = [LoadedTracksModule::class],
    dependencies = [LoadedTracksDependencies::class]
)
interface LoadedTracksComponent : BaseComponent {

    fun inject(fragment: LoadedTracksFragment)

    @Component.Factory
    interface Factory {

        fun create(
            dependencies: LoadedTracksDependencies
        ): LoadedTracksComponent
    }
}
