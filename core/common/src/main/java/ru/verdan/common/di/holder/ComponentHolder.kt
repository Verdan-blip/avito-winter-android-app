package ru.verdan.common.di.holder

import android.content.Context
import ru.verdan.common.di.component.DiComponent
import ru.verdan.common.di.container.ComponentDependenciesContainer

abstract class ComponentHolder<T : DiComponent> {

    private var component: T? = null

    protected abstract fun init(container: ComponentDependenciesContainer): T

    fun create(context: Context): T {
        return init(context.applicationContext as ComponentDependenciesContainer)
            .also { component = it }
    }

    fun release() {
        component = null
    }
}
