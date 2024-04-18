plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kotlinMultiplatformPlugin") {
            id = "com.wulinpeng.ezreader.kotlin.multiplatform"
            implementationClass = "com.wulinpeng.ezreader.KotlinMultiplatformPlugin"
        }
        register("KoinPlugin") {
            id = "com.wulinpeng.ezreader.koin"
            implementationClass = "com.wulinpeng.ezreader.KoinPlugin"
        }
    }
}