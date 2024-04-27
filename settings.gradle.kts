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
include(":infra:launcher:launcher-api")
include(":infra:launcher:launcher-impl")
include(":infra:appcontext")
include(":infra:common-resources")

include(":business")
include(":business:homepage")
include(":business:homepage:homepage-api")
include(":business:homepage:homepage-impl")
include(":business:book-shelf")
include(":business:book-shelf:book-shelf-impl")
include(":business:discovery")
include(":business:discovery:discovery-impl")
include(":business:category")
include(":business:category:category-impl")
include(":business:profile")
include(":business:profile:profile-impl")