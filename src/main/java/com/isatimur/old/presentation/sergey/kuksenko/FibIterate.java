package com.isatimur.old.presentation.sergey.kuksenko;

import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * Created by tisachenko on 30.01.2016.
 */
public class FibIterate {

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

    public static BigInteger parSum() {
        return stream().parallel().limit(SIZE).reduce(BigInteger.ZERO, BigInteger::add);
    }

    public static Stream<BigInteger> stream() {
        return Stream.iterate(new BigInteger[]{BigInteger.ZERO, BigInteger.ONE}, (BigInteger[] p) -> new BigInteger[]{p[1], p[0].add(p[1])}).map((BigInteger[] i) -> i[1]);
    }
}
