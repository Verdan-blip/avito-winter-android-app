package ru.verdan.common.di.key

import dagger.MapKey
import ru.verdan.common.di.component.ComponentDependencies
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ComponentDependenciesKey(val klass: KClass<out ComponentDependencies>)
