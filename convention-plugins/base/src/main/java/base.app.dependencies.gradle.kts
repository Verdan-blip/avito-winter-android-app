import ru.verdan.conventionplugins.base.extensions.androidAppConfig
import ru.verdan.conventionplugins.base.extensions.androidTestImplementation
import ru.verdan.conventionplugins.base.extensions.implementation
import ru.verdan.conventionplugins.base.extensions.libs
import ru.verdan.conventionplugins.base.extensions.testImplementation

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

androidAppConfig {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
