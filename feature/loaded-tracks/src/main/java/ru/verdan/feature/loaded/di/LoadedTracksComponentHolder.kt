package ru.verdan.feature.loaded.di

import ru.verdan.common.di.container.ComponentDependenciesContainer
import ru.verdan.common.di.holder.ComponentHolder

object LoadedTracksComponentHolder : ComponentHolder<
        LoadedTracksComponent,
        LoadedTracksDependencies>() {

    override fun init(container: ComponentDependenciesContainer): LoadedTracksComponent {
        return DaggerLoadedTracksComponent.factory()
            .create(container.getDependencies(LoadedTracksDependencies::class.java))
    }
}
