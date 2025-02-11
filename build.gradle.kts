// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    id("base.app.config") apply false
    id("base.app.dependencies") apply false
    id("base.feature.config") apply false
    id("base.feature.dependencies") apply false
    id("base.common.config") apply false
    id("base.common.dependencies") apply false
}
