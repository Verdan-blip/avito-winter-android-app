import ru.verdan.conventionplugins.base.extensions.androidConfig
import ru.verdan.conventionplugins.base.extensions.androidTestImplementation
import ru.verdan.conventionplugins.base.extensions.implementation
import ru.verdan.conventionplugins.base.extensions.libs
import ru.verdan.conventionplugins.base.extensions.testImplementation

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

androidConfig {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:theme"))
    implementation(project(":core:local-api"))
    implementation(project(":core:network-api"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.bundles.lifecycle)

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    implementation(libs.bundles.navigation)
    implementation(libs.bundles.coil)
    implementation(libs.viewbinding)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
