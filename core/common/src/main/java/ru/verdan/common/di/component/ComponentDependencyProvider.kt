package ru.verdan.common.di.component

import javax.inject.Inject

typealias DependenciesMap = Map<
        Class<out ComponentDependencies>,
        @JvmSuppressWildcards ComponentDependencies>

class ComponentDependencyProvider @Inject constructor(
    private val dependenciesMap: DependenciesMap
) {

    @Suppress("UNCHECKED_CAST")
    fun <T : ComponentDependencies> provide(clazz: Class<T>): T {
        return dependenciesMap[clazz] as? T
            ?: throw IllegalArgumentException("Missing dependencies for $clazz")
    }
}
