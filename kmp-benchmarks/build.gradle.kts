import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.kotlinx.benchmark")
}

repositories {
    mavenCentral()
}

kotlin {
    macosArm64()
    macosX64()
    linuxX64()
    linuxArm64()
    mingwX64()
    wasmJs { nodejs() }
    js(IR) { nodejs() }

    sourceSets {
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.10")
            }
        }
    }
}

benchmark {
    configurations {
        named("main") {
            iterations = 5
            iterationTime = 5
            iterationTimeUnit = "sec"
            outputTimeUnit = "ns"
        }
        create("Int.baseline") {
            include("IntSignumBenchmark.kotlinSignum")
            iterations = 15
            iterationTime = 5
            iterationTimeUnit = "sec"
            outputTimeUnit = "ns"
        }
        create("Int.bitmanip") {
            include("IntSignumBenchmark.signBitExtractingSignum")
            iterations = 15
            iterationTime = 5
            iterationTimeUnit = "sec"
            outputTimeUnit = "ns"
        }
        create("Long.baseline") {
            include("LongSignumBenchmark.kotlinSignum")
            iterations = 15
            iterationTime = 5
            iterationTimeUnit = "sec"
            outputTimeUnit = "ns"
        }
        create("Long.bitmanip") {
            include("LongSignumBenchmark.signBitExtractingSignum")
            iterations = 15
            iterationTime = 5
            iterationTimeUnit = "sec"
            outputTimeUnit = "ns"
        }

    }
    targets {
        register("macosArm64")
        register("macosX64")
        register("linuxX64")
        register("linuxArm64")
        register("mingwX64")
        register("wasmJs")
        register("js")
    }
}

rootProject.the<NodeJsRootExtension>().apply {
    nodeVersion = "21.0.0-v8-canary202310177990572111"
    nodeDownloadBaseUrl = "https://nodejs.org/download/v8-canary"
}

rootProject.tasks.withType<KotlinNpmInstallTask>().configureEach {
    args.add("--ignore-engines")
}
