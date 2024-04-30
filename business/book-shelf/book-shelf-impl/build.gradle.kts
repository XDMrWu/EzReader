
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
            implementation(libs.koin.compose)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.tabNavigator)
            implementation(libs.voyager.bottomSheetNavigator)
            implementation(libs.composeIcons.feather)
            implementation(project(":infra:common-resources"))
            implementation(project(":infra:appcontext"))
            implementation(project(":business:homepage:homepage-api"))
            implementation(project(":business:search:search-api"))
        }
    }
}