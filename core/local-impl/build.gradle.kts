plugins {
    id("base.common.dependencies")
    id("base.common.config")
    id("com.google.devtools.ksp")
}

android {
    namespace = "ru.verdan.local.impl"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:local-api"))
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
}
