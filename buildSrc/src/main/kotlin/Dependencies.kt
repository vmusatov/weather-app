object Dependencies {

    object Kotlin {
        const val core = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    }

    object Di {
        const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
        const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
        const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koinCompose}"
    }

    object Jetpack {
        const val core = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    }

    object Compose {
        const val activity = "androidx.activity:activity-compose:${Versions.compose}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.compose_navigation}"
        const val systemUiController =
            "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist_systemUiController}"
        const val accompanistPager = "com.google.accompanist:accompanist-pager:${Versions.accompanistPager}"

        const val collapsingToolbar = "me.onebone:toolbar-compose:${Versions.collapsingToolbar}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterJson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    }

    object Image {
        const val coilCompose = "io.coil-kt:coil-compose:${Versions.coilCompose}"
        const val coilSvg = "io.coil-kt:coil-svg:${Versions.coilCompose}"
    }

    object Utils {
        const val time = "com.jakewharton.threetenabp:threetenabp:1.4.0"
    }

    object Tests {
        const val junit = "junit:junit:4.13.2"
        const val androidJunit = "androidx.test.ext:junit:1.1.3"
    }
}