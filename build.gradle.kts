import kotlinx.benchmark.gradle.JvmBenchmarkTarget

plugins {
    kotlin("jvm") version "1.9.22" apply false
    kotlin("android") version "1.9.22" apply false
    id("org.jetbrains.kotlinx.benchmark") version "0.4.10" apply false
    id("com.android.application") version "8.2.0" apply false
    id("com.android.library") version "8.2.0" apply false
    id("androidx.benchmark") version "1.1.1" apply false
}

group = "org.example"
version = "1.0-SNAPSHOT"
