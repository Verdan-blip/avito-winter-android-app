package ru.verdan.common.di.holder

import android.content.Context
import ru.verdan.common.di.component.ComponentDependencies
import ru.verdan.common.di.component.DiComponent
import ru.verdan.common.di.container.ComponentDependenciesContainer
import java.lang.ref.WeakReference

abstract class ComponentHolder<C : DiComponent, D : ComponentDependencies> {

    private var component: C? = null
    private var componentRef: WeakReference<C>? = null

    protected abstract fun init(container: ComponentDependenciesContainer): C

    fun create(context: Context): C {
        return component ?: init(context.applicationContext as ComponentDependenciesContainer)
            .also { component = it }
    }

    fun get(context: Context): C {
        var component: C?
        synchronized(this) {
            component = componentRef?.get()
            if (component == null) {
                component = init(context.applicationContext as ComponentDependenciesContainer)
                componentRef = WeakReference(component)
            }
        }
        return component ?: throw IllegalStateException("Component is not initialized")
    }
}
