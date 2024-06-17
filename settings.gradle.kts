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
include(":common:domain:api")
include(":common:domain:models")
include(":common:general:extensions")
include(":common:general:models")
include(":common:navigation")
include(":common:ui:components")
include(":common:ui:compositions")
include(":common:ui:resources:drawables")
include(":common:ui:resources:strings")
include(":common:ui:theme")
include(":common:ui:widgets")

include(":features:nowplaying")
include(":features:popular")
include(":features:toprated")
include(":features:upcoming")
include(":features:moviedetail")
include(":features:artistdetail")
include(":features:genredetail")


