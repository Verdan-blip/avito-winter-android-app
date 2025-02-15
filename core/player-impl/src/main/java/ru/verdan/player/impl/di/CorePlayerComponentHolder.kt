package ru.verdan.player.impl.di

import ru.verdan.common.di.container.ComponentDependenciesContainer
import ru.verdan.common.di.holder.ComponentHolder

object CorePlayerComponentHolder : ComponentHolder<CorePlayerComponent>() {

    override fun init(container: ComponentDependenciesContainer): CorePlayerComponent {
        return DaggerCorePlayerComponent
            .factory()
            .create(container.getDependencies(CorePlayerComponentDependencies::class.java))
    }
}
