package com.isatimur.chapter_6;

import org.openjdk.jmh.annotations.Benchmark;

import static com.isatimur.chapter_6.GroupingSamples.partitionByPrimesWithCustomCollector;
import static com.isatimur.chapter_6.GroupingSamples.partitionPrimes;

/**
 * Created by tisachenko on 07.02.16.
 */
public class CollectorHarness {

    public static final int SIZE = 1_000_000;

    public static void main(String[] args) {
//        long fastest = Long.MAX_VALUE;
//        for(int i=0; i<=10; i++){
//            long start = System.nanoTime();
////            partitionPrimes(1_000_000);
//            partitionByPrimesWithCustomCollector(SIZE);
//            long duration = (System.nanoTime()-start)/SIZE;
//            if(duration<fastest) fastest = duration;
//        }
//        System.out.println("Fastest execution done in " + fastest + " msecs");
    }

    @Benchmark
    public void firstPartitionPrimes() {
        partitionPrimes(SIZE);

    }

    @Benchmark
    public void secondPartitionPrimes() {
        partitionByPrimesWithCustomCollector(SIZE);

    }
}


