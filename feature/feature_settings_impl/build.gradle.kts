plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = AppConfig.compileSdk

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(Modules.Core.base))
    implementation(project(Modules.Core.designSystem))
    implementation(project(Modules.Core.coroutines))

    implementation(project(Modules.Feature.settingsApi))

    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.toolingPreview)
    implementation(Dependencies.Compose.material)

    implementation(Dependencies.Kotlin.coroutines)

    implementation(Dependencies.Di.koinCore)
    implementation(Dependencies.Di.koinAndroid)
}