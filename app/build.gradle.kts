plugins {
    id("base.app.dependencies")
    id("base.app.config")
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
    implementation(project(":core:local-api"))
    implementation(project(":core:local-api"))
    implementation(project(":core:network-api"))
    implementation(project(":core:network-impl"))
}
