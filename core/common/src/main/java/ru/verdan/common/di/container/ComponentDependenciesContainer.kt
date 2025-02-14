package ru.verdan.common.di.container

import ru.verdan.common.di.component.ComponentDependencies

interface ComponentDependenciesContainer {

    fun <T : ComponentDependencies> getDependencies(key: Class<out T>): T
}
