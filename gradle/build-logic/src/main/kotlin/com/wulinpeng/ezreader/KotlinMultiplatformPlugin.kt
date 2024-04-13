package com.wulinpeng.ezreader

import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.all
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformPlugin: Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.multiplatform")
        }
        if (pluginManager.hasPlugin("com.android.library")) {
            extensions.configure(LibraryExtension::class.java) {
                namespace = "com.wulinpeng.ezreader"
                compileSdk = 34
            }
        }
        extensions.configure(KotlinMultiplatformExtension::class.java) {
            if (pluginManager.hasPlugin("com.android.application") || pluginManager.hasPlugin("com.android.library")) {
                androidTarget {
                    compilations.all {
                        kotlinOptions {
                            jvmTarget = "${JavaVersion.VERSION_1_8}"
                            freeCompilerArgs += "-Xjdk-release=${JavaVersion.VERSION_1_8}"
                        }
                    }
                }
            }

            jvm()

            listOf(
                iosX64(),
                iosArm64(),
                iosSimulatorArm64()
            ).forEach {
                it.binaries.framework {
                    baseName = project.name
                    isStatic = true
                }
            }
            sourceSets.all {
                languageSettings {
                    optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
                }
            }
        }
    }
}