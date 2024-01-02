package com.example.benchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.math.sign

fun Int.lcgNext(): Int = this * 1664525 + 1013904223
fun Long.lcgNext(): Long = this * 6364136223846793005 + 1442695040888963407

@RunWith(AndroidJUnit4ClassRunner::class)
public class IntSignumBenchmarks {
    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @Test
    fun kotlinSignum() {
        var x = 1
        var sum = 0
        benchmarkRule.measureRepeated {
            sum += x.sign
            x = x.lcgNext()
        }
    }

    @Test
    fun javaSignum() {
        var x = 1
        var sum = 0
        benchmarkRule.measureRepeated {
            sum += Integer.signum(x)
            x = x.lcgNext()
        }
    }

    @Test
    fun signBitExtractingSignum() {
        var x = 1
        var sum = 0
        benchmarkRule.measureRepeated {
            sum += (x shr 31 or (-x ushr 31))
            x = x.lcgNext()
        }
    }
}

@RunWith(AndroidJUnit4ClassRunner::class)
public class LongSignumBenchmarks {
    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @Test
    fun kotlinSignum() {
        var x = 1L
        var sum = 0
        benchmarkRule.measureRepeated {
            sum += x.sign
            x = x.lcgNext()
        }
    }

    @Test
    fun javaSignum() {
        var x = 1L
        var sum = 0
        benchmarkRule.measureRepeated {
            sum += java.lang.Long.signum(x)
            x = x.lcgNext()
        }
    }

    @Test
    fun signBitExtractingSignum() {
        var x = 1L
        var sum = 0
        benchmarkRule.measureRepeated {
            sum += (x shr 63 or (-x ushr 63)).toInt()
            x = x.lcgNext()
        }
    }
}