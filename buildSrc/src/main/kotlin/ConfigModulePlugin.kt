import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.*

class ConfigModulePlugin : Plugin<Project> {

    private val appProperties = Properties()

    override fun apply(project: Project) {
        appProperties.load(project.rootProject.file("app.properties").inputStream())

        project.plugins.apply("kotlin-android")

        val androidExtensions = project.extensions.getByName("android")
        if (androidExtensions is BaseExtension) {
            androidExtensions.apply {
                compileSdkVersion(AppConfig.compileSdk)

                defaultConfig {
                    minSdk = AppConfig.minSdk
                    targetSdk = AppConfig.targetSdk
                    versionCode = AppConfig.versionCode
                    versionName = AppConfig.versionName

                    testInstrumentationRunner = AppConfig.androidTestInstrumentation

                    buildConfigField(
                        "String",
                        "WEATHER_API_KEY",
                        appProperties.getProperty("weatherApiKey")
                    )
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }

                project.tasks.withType(KotlinCompile::class.java).configureEach {
                    kotlinOptions {
                        jvmTarget = JavaVersion.VERSION_1_8.toString()
                    }
                }

                packagingOptions {
                    resources.excludes.add("META-INF/*")
                }

                viewBinding.isEnabled = true
            }

            project.dependencies.apply {
                add("implementation", Dependencies.Kotlin.core)
                add("implementation", Dependencies.Jetpack.core)
                //add("implementation", "com.squareup:javapoet:1.13.0")

                add("testImplementation", Dependencies.Tests.junit)
                add("androidTestImplementation", Dependencies.Tests.androidJunit)
            }
        }
    }
}