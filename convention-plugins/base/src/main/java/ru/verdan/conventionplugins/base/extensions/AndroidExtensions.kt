package ru.verdan.conventionplugins.base.extensions

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.findByType

private typealias AndroidExtensions = LibraryExtension

private typealias AndroidAppExtensions = BaseAppModuleExtension

private val Project.androidLibraryExtension: AndroidExtensions
    get() = extensions.findByType(LibraryExtension::class)
        ?: error(
            "\"Project.androidLibraryExtension\" value may be called only from " +
                    "android library gradle script"
        )

private val Project.androidAppExtension: AndroidAppExtensions
    get() = extensions.findByType(BaseAppModuleExtension::class)
        ?: error(
            "\"Project.androidAppExtension\" value may be called only from " +
                    "android application"
        )

fun Project.androidConfig(block: AndroidExtensions.() -> Unit): Unit = block(androidLibraryExtension)

fun Project.androidAppConfig(block: AndroidAppExtensions.() -> Unit): Unit = block(androidAppExtension)

fun DependencyHandlerScope.androidTestImplementation(dependency: Any) {
    add("androidTestImplementation", dependency)
}
