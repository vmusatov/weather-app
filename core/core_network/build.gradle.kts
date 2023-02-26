plugins {
    id("com.android.library")
    id("config-plugin")
    kotlin("kapt")
}

dependencies {
    implementation(project(Modules.Core.base))

    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.converterJson)
    implementation(Dependencies.Network.okhttp)
    implementation(Dependencies.Network.loggingInterceptor)

    implementation(Dependencies.Di.koinCore)
    implementation(Dependencies.Di.koinAndroid)
}