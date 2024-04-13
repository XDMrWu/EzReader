plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kotlinMultiplatform") {
            id = "com.wulinpeng.ezreader.kotlin.multiplatform"
            implementationClass = "com.wulinpeng.ezreader.KotlinMultiplatformPlugin"
        }
    }
}