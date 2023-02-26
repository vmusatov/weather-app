import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("config-plugin") {
            id = "config-plugin"
            implementationClass = "ConfigModulePlugin"
        }
    }
}

dependencies {
    compileOnly(gradleApi())
    implementation("com.android.tools.build:gradle:7.2.2")
    implementation("com.squareup:javapoet:1.13.0")
    implementation(kotlin("gradle-plugin", "1.7.10"))
}