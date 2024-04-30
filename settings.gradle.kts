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
include(":infra:network")
include(":infra:architecture")
include(":infra:common-model")
include(":infra:storage")

include(":business")
include(":business:homepage")
include(":business:homepage:homepage-api")
include(":business:homepage:homepage-impl")
include(":business:book-shelf")
include(":business:book-shelf:book-shelf-impl")
include(":business:discovery")
include(":business:discovery:discovery-impl")
include(":business:assistant")
include(":business:assistant:assistant-impl")
include(":business:profile")
include(":business:profile:profile-impl")
include(":business:search")
include(":business:search:search-impl")
include(":business:search:search-api")