
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
            api(libs.kstore)
            api(libs.kstore.file)
            api(libs.ktor.serialization.json)
        }
    }
}