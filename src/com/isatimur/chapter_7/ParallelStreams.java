package com.isatimur.chapter_7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by tisachenko on 07.02.16.
 */
public class ParallelStreams {

    public static final int N = 10_000_000;

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
    }

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i < n; i++) {
            result += i;
        }
        return result;
    }

    public static long parallelStream(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
    }

    public static void main(String[] args) {
        //the amount of available processors
//        System.out.println(Runtime.getRuntime().availableProcessors());

        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");

//        System.out.println(System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism"));

        System.out.println("Parallel Harness \n");

        System.out.println("Sequential sum done in: " + measureSumPerf(ParallelStreams::sequentialSum, N) + " msec");

        System.out.println("Iterative sum done in: " + measureSumPerf(ParallelStreams::iterativeSum, N) + " msec");

        System.out.println("Parallel sum done in: " + measureSumPerf(ParallelStreams::parallelStream, N) + " msec");

        System.out.println("Ranged sum done in: " + measureSumPerf(ParallelStreams::rangedSum, N) + " msec");

        System.out.println("Parallel ranged sum done in: " + measureSumPerf(ParallelStreams::parallelRangedSum, N) + " msec");

        System.out.println("SideEffect parallel sum done in: " + measureSumPerf(ParallelStreams::sideEffectParallelSum, N) + " msec");

        System.out.println("ForkJoin sum done in: " + measureSumPerf(ParallelStreams::forkJoinSum, N) + " msec");

    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }


    //7.1.3
    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;

    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;

    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

}
