pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Karlflix"
include(":androidApp")
include(":shared")

enableFeaturePreview("VERSION_CATALOGS")