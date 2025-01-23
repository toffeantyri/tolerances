import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

//    listOf(
//        iosX64()
//    ).forEach { iosTarget ->
//        iosTarget.binaries.framework {
//            baseName = "ComposeApp"
//            isStatic = true
//        }
//    }

    jvm()

    js(IR) {
        moduleName = "composeApp"

        browser {
            commonWebpackConfig {
                devServer?.open = false
                devServer?.port = 3000
                outputFileName = "composeApp.js"

            }

        }
        binaries.executable()
        useEsModules()
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        common {
            withJs()
            group("nonJs") {
                withAndroidTarget()
                withJvm()
//                group("ios") {
//                    withIos()
//                }
            }
        }
    }

    sourceSets {

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            implementation(libs.multiplatform.settings)
            implementation(libs.decompose.decompose)
            implementation(libs.decompose.extensionsComposeJetbrains)
            implementation(libs.essenty.lifecycle)

            implementation(libs.koin.core)

//            implementation(libs.kotlinx)
        }
        val nonJsMain by getting {
            dependencies {

            }
        }

        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }


        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)


        }

        jsMain.dependencies {
            implementation(compose.html.core)
            implementation(compose.runtime)
        }
    }
}

android {
    namespace = "ru.tolerances.app"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "ru.tolerances.app"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ru.tolerances.app"
            packageVersion = "1.0.0"
        }
    }
}
