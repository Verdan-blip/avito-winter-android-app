plugins {
    id("base.common.dependencies")
    id("base.common.config")
}

android {
    namespace = "ru.verdan.player.impl"
}

dependencies {
    implementation(libs.bundles.exoplayer)
}
