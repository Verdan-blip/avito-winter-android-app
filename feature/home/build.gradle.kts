import ru.verdan.conventionplugins.base.extensions.implementation

plugins {
    id("base.feature.dependencies")
    id("base.feature.config")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "ru.verdan.feature.home"
}

dependencies {
    implementation(libs.kotlin.serialization.json)
    implementation(libs.bundles.network)
    implementation(libs.paging)
}
