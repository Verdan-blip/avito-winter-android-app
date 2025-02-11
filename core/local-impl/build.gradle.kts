plugins {
    id("base.common.dependencies")
    id("base.common.config")
    id("com.google.devtools.ksp")
}

android {
    namespace = "ru.verdan.local.impl"
}

dependencies {
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
}
