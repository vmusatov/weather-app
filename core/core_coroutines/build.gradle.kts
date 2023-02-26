plugins {
    id("com.android.library")
    id("config-plugin")
    kotlin("kapt")
}

dependencies {
    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.Di.koinCore)
    implementation(Dependencies.Di.koinAndroid)
}