plugins {
    id("base.common.dependencies")
    id("base.common.config")
}

android {
    namespace = "ru.verdan.core.theme"
}

dependencies {
    implementation(libs.coil)
}
