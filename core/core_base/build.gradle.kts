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
    implementation(Dependencies.Jetpack.core)
    implementation(Dependencies.Jetpack.lifecycle)

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.toolingPreview)
    implementation(Dependencies.Compose.material)

    implementation(Dependencies.Utils.time)
    implementation(Dependencies.Di.koinCore)
    implementation(Dependencies.Di.koinAndroid)
}