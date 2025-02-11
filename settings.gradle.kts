pluginManagement {
    includeBuild("convention-plugins/base")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "avito-winter-android-app"
include(":app")
include(":core")
include(":core:network-api")
include(":core:network-impl")
include(":core:common")
include(":core:local-api")
include(":core:local-impl")
include(":core:player-api")
include(":core:player-impl")
