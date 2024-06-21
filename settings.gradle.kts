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
include(":common:domain:api")
include(":common:domain:models")
include(":common:general:extensions")
include(":common:general:models")
include(":common:navigation")
include(":common:layers:presentation:widgets")
include(":common:ui:components")
include(":common:ui:compositions")
include(":common:ui:resources:drawables")
include(":common:ui:resources:strings")
include(":common:ui:theme")
include(":features:nowplaying")
include(":features:popular")
include(":features:toprated")
include(":features:upcoming")
include(":features:moviedetail")
include(":features:artistdetail")
include(":features:genredetail")
include(":business:artistdetail:data:di")
include(":business:artistdetail:data:entity")
include(":business:artistdetail:data:main")
include(":business:artistdetail:di")
include(":business:artistdetail:domain:di")
include(":business:artistdetail:domain:main")
include(":business:artistdetail:domain:model")
include(":business:moviedetail:data:di")
include(":business:moviedetail:data:entity")
include(":business:moviedetail:data:main")
include(":business:moviedetail:di")
include(":business:moviedetail:domain:di")
include(":business:moviedetail:domain:main")
include(":business:moviedetail:domain:model")
