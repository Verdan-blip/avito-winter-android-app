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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    implementation(libs.viewbinding)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
