package com.isatimur.old.presentation.sergey.kuksenko;

import java.math.BigInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by tisachenko on 30.01.2016.
 */
public class FibGenerate {
    private static int SIZE = Demo1.SIZE;

    public static void main(String[] args) {
        System.out.println("Sequential sum of Fib numbers");
        System.out.println(seqSum());

        System.out.println("Parallel sum of Fib numbers");
        System.out.println(parSum());
    }


    public static BigInteger seqSum() {
        return stream().limit(SIZE).reduce(BigInteger.ZERO, BigInteger::add);
    }

    private static Stream<BigInteger> stream() {
        return Stream.generate(
                new Supplier<BigInteger>() {
                    private BigInteger first = BigInteger.ZERO;
                    private BigInteger second = BigInteger.ONE;

                    @Override
                    public BigInteger get() {
                        BigInteger s = second.add(first);
                        first = second;
                        second = s;
                        return first;
                    }
                }
        );

    }

    public static BigInteger parSum() {
        return stream().parallel().limit(SIZE).reduce(BigInteger.ZERO, BigInteger::add);
    }
}
