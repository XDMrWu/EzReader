import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    alias(libs.plugins.android.library)
    id("com.wulinpeng.ezreader.kotlin.multiplatform")
    id("com.wulinpeng.ezreader.compose")
    alias(libs.plugins.buildKonfig)
}

kotlin {
    sourceSets {
        commonMain.configure{
            kotlin.srcDir("build/buildkonfig/commonMain")
        }
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.voyager.navigator)
            implementation(libs.kotlinx.serialization.json)
        }
    }
}

val valueMap = mutableMapOf<String, String>()
File(rootDir, "local.properties").readLines().filter { it.contains("=") }.forEach {
    valueMap.put(it.split("=")[0], it.split("=")[1])
}

buildkonfig {
    packageName = "com.wulinpeng.ezreader"
    objectName = "CommonConfig"
    exposeObjectWithName = "CommonConfig"

    defaultConfigs {
        buildConfigField(STRING, "ezreader_host", valueMap["READER_HOST"])
    }
}
