pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        google()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "stdlib-signum"
include("jvm-benchmarks")
include("android-benchmarks")
include("kmp-benchmarks")
