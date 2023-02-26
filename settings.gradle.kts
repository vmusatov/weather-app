pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "weather-app"

include(":app")
include(":core")
include(":core:core_base")
include(":core:core_coroutines")
include(":core:core_network")
include(":core:core_design_system")
include(":feature")
include(":feature:feature_home")
include(":core:core_navigation")
include(":feature:feature_locations")
include(":feature:feature_settings_impl")
include(":feature:feature_settings_api")
