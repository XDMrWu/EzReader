plugins {
    alias(libs.plugins.android.library)
    id("com.wulinpeng.ezreader.kotlin.multiplatform")
    id("com.wulinpeng.ezreader.compose")
//    alias(libs.plugins.libres)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.libres)
        }
    }
}

//libres {
//    generatedClassName = "CommonRes" // "Res" by default
//    generateNamedArguments = true // false by default
//    baseLocaleLanguageCode = "en" // "en" by default
//    camelCaseNamesForAppleFramework = true // false by default
//}