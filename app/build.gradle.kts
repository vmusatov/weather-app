plugins {
    id("com.android.application")
    kotlin("kapt")
    id("config-plugin")
}

android {
    defaultConfig {
        applicationId = AppConfig.applicationId
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

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
    implementation(project(Modules.Core.coroutines))
    implementation(project(Modules.Core.designSystem))
    implementation(project(Modules.Core.navigation))

    implementation(project(Modules.Feature.home))
    implementation(project(Modules.Feature.locations))
    implementation(project(Modules.Feature.settingsImpl))

    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.toolingPreview)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.navigation)

    implementation(Dependencies.Di.koinCore)
    implementation(Dependencies.Di.koinAndroid)

    implementation(Dependencies.Utils.time)
}
