
plugins {
    alias(libs.plugins.android.library)
    id("com.wulinpeng.ezreader.kotlin.multiplatform")
}

kotlin {
    sourceSets.commonMain.dependencies {
        implementation(libs.voyager.tabNavigator)
    }
}