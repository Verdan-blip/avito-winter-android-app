package ru.verdan.feature.trackplayer.di

import ru.verdan.common.di.container.ComponentDependenciesContainer
import ru.verdan.common.di.holder.ComponentHolder

object TrackPlayerComponentHolder : ComponentHolder<
        TrackPlayerComponent,
        TrackPlayerDependencies>() {

    override fun init(container: ComponentDependenciesContainer): TrackPlayerComponent {
        return DaggerTrackPlayerComponent
            .factory()
            .create(container.getDependencies(TrackPlayerDependencies::class.java))
    }
}
