pluginManagement {
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

rootProject.name = "JetpackComposeMVVMCleanArchitecture"
include(":app")
include(":common:navigation")
include(":common:ui:theme")
include(":common:ui:compositions")
include(":common:ui:resources:strings")
include(":common:ui:resources:drawables")
