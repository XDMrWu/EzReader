
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
            implementation(libs.coil.compose)
            implementation(libs.coil.ktor)
            implementation(project(":infra:storage"))
            implementation(project(":infra:common-resources"))
            implementation(project(":infra:network"))
            implementation(project(":infra:architecture"))
            implementation(project(":infra:appcontext"))
            implementation(project(":infra:common-model"))
            implementation(project(":business:book-detail:book-detail-api"))
            implementation(project(":business:book-shelf:book-shelf-api"))
        }
    }
}