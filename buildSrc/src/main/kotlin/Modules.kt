object Modules {

    const val app = ":app"

    object Core {
        const val base = ":core:core_base"
        const val coroutines = ":core:core_coroutines"
        const val network = ":core:core_network"
        const val designSystem = ":core:core_design_system"
        const val navigation = ":core:core_navigation"
    }

    object Feature {
        const val home = ":feature:feature_home"
        const val locations = ":feature:feature_locations"
        const val settingsImpl = ":feature:feature_settings_impl"
        const val settingsApi = ":feature:feature_settings_api"
    }
}