pluginManagement {
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

rootProject.name = "Kotlin-gRPC"
include(":androidApp")
include(":shared")
include(":protos")
include(":stub")
include(":server")
