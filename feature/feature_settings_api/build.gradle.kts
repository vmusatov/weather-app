plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = AppConfig.compileSdk
}

dependencies {
    implementation(project(Modules.Core.base))
    implementation(project(Modules.Core.coroutines))
    implementation(Dependencies.Kotlin.coroutines)
}