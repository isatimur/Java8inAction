package com.isatimur;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Created by tisachenko on 18.01.2016.
 */
public class ReduceFunction {
    public static void main(String[] args) {
        IntStream integers = IntStream.range(1, 100);
//        int sum = integers.sum();
        int sum = integers.reduce(0, (a, b) -> (a + b));

        System.out.println("Summary of first hundread int elements: " + sum);

        LongStream longStream = LongStream.range(1, 50);
        long factorial = longStream.reduce(1, (a, b) -> (a * b));

        System.out.println("Factorial of first fifty int elements: " + factorial);
    }
}
