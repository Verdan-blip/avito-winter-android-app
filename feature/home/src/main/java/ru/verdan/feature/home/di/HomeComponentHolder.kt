package ru.verdan.feature.home.di

import ru.verdan.common.di.container.ComponentDependenciesContainer
import ru.verdan.common.di.holder.ComponentHolder

object HomeComponentHolder : ComponentHolder<
        HomeComponent,
        HomeComponentDependencies>() {

    override fun init(container: ComponentDependenciesContainer): HomeComponent {
        return DaggerHomeComponent.factory()
            .create(container.getDependencies(HomeComponentDependencies::class.java))
    }
}
