
plugins {
    alias(libs.plugins.android.library)
    id("com.wulinpeng.ezreader.kotlin.multiplatform")
    id("com.wulinpeng.ezreader.compose")
}

kotlin {
    sourceSets.commonMain.dependencies {
        implementation(libs.voyager.tabNavigator)
    }
}