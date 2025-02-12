import ru.verdan.conventionplugins.base.extensions.implementation

plugins {
    id("base.common.dependencies")
    id("base.common.config")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "ru.verdan.network.impl"
}

dependencies {
    implementation(project(":core:network-api"))
    implementation(libs.bundles.network)
    implementation(libs.kotlin.serialization.json)
}
