import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.android.application)
    id("com.wulinpeng.ezreader.kotlin.multiplatform")
    id("com.wulinpeng.ezreader.compose")
    alias(libs.plugins.ksp)
    id("com.wulinpeng.ezreader.koin")
}

kotlin {

    sourceSets {

        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.annotations)
            implementation(libs.voyager.navigator)

            implementation(project(":infra:appcontext"))
            implementation(project(":infra:network"))
            implementation(project(":infra:storage"))
            implementation(project(":infra:launcher:launcher-impl"))
            implementation(project(":business:homepage:homepage-impl"))
            implementation(project(":business:book-shelf:book-shelf-impl"))
            implementation(project(":business:discovery:discovery-impl"))
            implementation(project(":business:assistant:assistant-impl"))
            implementation(project(":business:profile:profile-impl"))
            implementation(project(":business:search:search-impl"))
        }

        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
        }

        jvmMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.okhttp)
        }
    }
}

android {
    namespace = "com.wulinpeng.ezreader"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34

        applicationId = "com.wulinpeng.ezreader.androidApp"
        versionCode = 1
        versionName = "1.0.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
}

compose.desktop {
    application {
        mainClass = "com.wulinpeng.ezreader.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.wulinpeng.ezreader"
            packageVersion = "1.0.0"
        }
    }
}