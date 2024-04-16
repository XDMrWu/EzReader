rootProject.name = "EzReader"

pluginManagement {
    includeBuild("gradle/build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":app")

include(":infra")

include(":infra:launcher")
include(":infra:launcher:launcher_api")
include(":infra:launcher:launcher_impl")