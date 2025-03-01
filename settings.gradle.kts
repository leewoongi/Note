pluginManagement {
    includeBuild("build_logic")

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

rootProject.name = "Note"
include(":app")
include(":domain")
include(":data")
include(":core")
include(":presenter")
include(":presenter:home")
include(":presenter:detail")
include(":presenter:navigator")
