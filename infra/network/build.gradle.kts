
plugins {
    alias(libs.plugins.android.library)
    id("com.wulinpeng.ezreader.kotlin.multiplatform")
    id("com.wulinpeng.ezreader.compose")
    alias(libs.plugins.ksp)
    id("com.wulinpeng.ezreader.koin")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.koin.annotations)
            implementation(libs.kotlinx.coroutines.core)
            implementation(project(":infra:launcher:launcher-api"))
            api(libs.ktor.client.core)
            api(libs.ktor.client.content.negotiation)
            api(libs.ktor.serialization.json)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        jvmMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}