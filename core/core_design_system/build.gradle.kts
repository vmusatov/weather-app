plugins {
    id("com.android.library")
    id("config-plugin")
    kotlin("kapt")
}

android {
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(Modules.Core.base))

    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.tooling)
    implementation(Dependencies.Compose.toolingPreview)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.systemUiController)

    implementation(Dependencies.Di.koinCore)
    implementation(Dependencies.Di.koinAndroid)
}