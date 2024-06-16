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
include(":common:data")
include(":common:domain")
include(":common:general:extensions")
include(":common:general:models")
include(":common:navigation")
include(":common:ui:compositions")
include(":common:ui:resources:strings")
include(":common:ui:resources:drawables")
include(":common:ui:theme")
