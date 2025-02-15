plugins {
    id("base.app.dependencies")
    id("base.app.config")
    alias(libs.plugins.android.navigation.safeargs)
}

android {
    namespace = "ru.verdan"

    defaultConfig {
        applicationId = "ru.verdan"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:theme"))
    implementation(project(":core:local-api"))
    implementation(project(":core:local-impl"))
    implementation(project(":core:network-api"))
    implementation(project(":core:network-impl"))
    implementation(project(":core:player-api"))
    implementation(project(":core:player-impl"))
    implementation(project(":feature:home"))
    implementation(project(":feature:loaded-tracks"))
    implementation(project(":feature:track-player"))
    implementation(libs.bundles.exoplayer)
}
