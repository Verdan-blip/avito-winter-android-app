import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import ru.verdan.conventionplugins.base.extensions.androidConfig
import ru.verdan.conventionplugins.base.extensions.kotlinJvmCompilerOptions
import ru.verdan.conventionplugins.base.extensions.libs
import ru.verdan.conventionplugins.base.extensions.projectJavaVersion

androidConfig {

    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(projectJavaVersion)
        targetCompatibility = JavaVersion.toVersion(projectJavaVersion)
    }
}

kotlinJvmCompilerOptions {
    jvmTarget.set(JvmTarget.fromTarget(projectJavaVersion.toString()))
}
