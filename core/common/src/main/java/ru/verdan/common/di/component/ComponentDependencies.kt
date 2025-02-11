package ru.verdan.common.di.component

import android.content.Context
import ru.verdan.common.di.container.ComponentDependenciesContainer

interface ComponentDependencies

inline fun <reified T : ComponentDependencies> Context.getDependencies(): T {
    return (applicationContext as ComponentDependenciesContainer)
        .getDependencies(T::class.java)
}
