package org.example

import kotlinx.benchmark.*
import kotlin.math.sign

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(BenchmarkTimeUnit.NANOSECONDS)
open class IntSignumBenchmark {
    private var x = 1

    fun nextValue(): Int {
        val r = x
        x = x * 1664525 + 1013904223
        return r
    }

    @Benchmark
    fun kotlinSignum(blackhole: Blackhole) {
        val value = nextValue()
        blackhole.consume(value.sign)
    }

    @Benchmark
    fun signBitExtractingSignum(blackhole: Blackhole) {
        val value = nextValue()
        blackhole.consume(value.signumBM)
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
    fun kotlinSignum(blackhole: Blackhole) {
        blackhole.consume(nextValue().sign)
    }

    @Benchmark
    fun signBitExtractingSignum(blackhole: Blackhole) {
        val value = nextValue()
        blackhole.consume(value.signumBM)
    }
}

private const val INT_M_BITS = Int.SIZE_BITS - 1

private val Int.signumBM: Int
    get() = (this shr INT_M_BITS) or (-this ushr INT_M_BITS)

private const val LONG_M_BITS = Long.SIZE_BITS - 1

private val Long.signumBM: Int
    get() = (this shr LONG_M_BITS or (-this ushr LONG_M_BITS)).toInt()
