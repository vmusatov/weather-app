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
    implementation(project(Modules.Core.network))
    implementation(project(Modules.Core.designSystem))

    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.toolingPreview)
    implementation(Dependencies.Compose.material)

    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.converterJson)

    implementation(Dependencies.Utils.time)

    implementation(Dependencies.Di.koinCore)
    implementation(Dependencies.Di.koinAndroid)
    implementation(Dependencies.Di.koinCompose)
}