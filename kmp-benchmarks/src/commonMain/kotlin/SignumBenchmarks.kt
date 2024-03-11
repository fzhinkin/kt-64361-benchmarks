package org.example

import kotlinx.benchmark.*
import kotlin.math.sign

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
open class IntSignumBenchmark {
    private var x = 1

    fun nextValue(): Int {
        val r = x
        x = x * 1664525 + 1013904223
        return r
    }

    @Benchmark
    fun kotlinSignum(): Int = nextValue().sign

    @Benchmark
    fun signBitExtractingSignum(): Int {
        val value = nextValue()
        return value shr 31 or (-value ushr 31)
    }

    @Benchmark
    fun kotlinSignumBh(blackhole: Blackhole) {
        blackhole.consume(nextValue().sign)
    }

    @Benchmark
    fun signBitExtractingSignumBh(blackhole: Blackhole) {
        val value = nextValue()
        blackhole.consume(value shr 31 or (-value ushr 31))
    }
}

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
open class LongSignumBenchmark {
    private var x = 1L

    fun nextValue(): Long {
        val r = x
        x = x * 6364136223846793005 + 1442695040888963407
        return r
    }

    @Benchmark
    fun kotlinSignum(): Int = nextValue().sign

    @Benchmark
    fun signBitExtractingSignum(): Int {
        val value = nextValue()
        return (value shr 63 or (-value ushr 63)).toInt()
    }

    @Benchmark
    fun kotlinSignumBh(blackhole: Blackhole) {
        blackhole.consume(nextValue().sign)
    }

    @Benchmark
    fun signBitExtractingSignum(blackhole: Blackhole) {
        val value = nextValue()
        blackhole.consume((value shr 63 or (-value ushr 63)).toInt())
    }
}
