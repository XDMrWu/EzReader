package com.wulinpeng.ezreader

import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KoinPlugin: Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        extensions.configure(KotlinMultiplatformExtension::class.java) {
            sourceSets.commonMain.configure {
                kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            }
        }
        dependencies {
            add("kspCommonMainMetadata", "io.insert-koin:koin-ksp-compiler:1.3.1")
//            add("kspJvm", libs.koin.ksp.compiler)
//            add("kspAndroid", libs.koin.ksp.compiler)
//            add("kspIosX64", libs.koin.ksp.compiler)
//            add("kspIosArm64", libs.koin.ksp.compiler)
//            add("kspIosSimulatorArm64", libs.koin.ksp.compiler)
        }
        // WORKAROUND: ADD this dependsOn("kspCommonMainKotlinMetadata") instead of above dependencies
        tasks.withType<KotlinCompile<*>>().configureEach {
            if (name != "kspCommonMainKotlinMetadata") {
                dependsOn("kspCommonMainKotlinMetadata")
            }
        }
        afterEvaluate {
            tasks.filter {
                it.name.contains("SourcesJar", true)
            }?.forEach {
                println("SourceJarTask====>${it.name}")
                it.dependsOn("kspCommonMainKotlinMetadata")
            }
        }
        extensions.configure(KspExtension::class.java) {
            arg("KOIN_CONFIG_CHECK","true")
            arg("KOIN_DEFAULT_MODULE","false")
        }
    }
}