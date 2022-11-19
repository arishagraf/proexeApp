pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "EpgApp"
include(":app")
include(":domain")
include(":data")
