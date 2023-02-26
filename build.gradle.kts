buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath(Dependencies.Kotlin.plugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}