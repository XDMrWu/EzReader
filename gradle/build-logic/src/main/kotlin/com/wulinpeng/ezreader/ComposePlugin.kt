package com.wulinpeng.ezreader

import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.all
import org.gradle.kotlin.dsl.get
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposePlugin: Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.compose")
        }
        extensions.configure(KotlinMultiplatformExtension::class.java) {
            val composeDependencies = extensions.getByType(ComposeExtension::class.java).dependencies
            sourceSets.commonMain.dependencies {
                implementation(composeDependencies.runtime)
                implementation(composeDependencies.foundation)
                implementation(composeDependencies.material3)
                implementation(composeDependencies.material)
                implementation(composeDependencies.components.resources)
                implementation(composeDependencies.components.uiToolingPreview)
            }
            sourceSets.androidMain.dependencies {
                implementation(composeDependencies.uiTooling)
                implementation("androidx.activity:activity-compose:1.8.2")
            }

            sourceSets.jvmMain.dependencies {
                implementation(composeDependencies.desktop.currentOs)
            }
        }

    }
}