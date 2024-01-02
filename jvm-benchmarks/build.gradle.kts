import kotlinx.benchmark.gradle.JvmBenchmarkTarget

plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlinx.benchmark")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.10")
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}

benchmark {
    targets {
        register("main") {
            this as JvmBenchmarkTarget
            jmhVersion = "1.37"
        }
    }
}
