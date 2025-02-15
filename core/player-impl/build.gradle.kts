import ru.verdan.conventionplugins.base.extensions.implementation

plugins {
    id("base.common.dependencies")
    id("base.common.config")
}

android {
    namespace = "ru.verdan.player.impl"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:player-api"))
    implementation(libs.bundles.exoplayer)
    implementation(libs.bundles.coroutines)
}
