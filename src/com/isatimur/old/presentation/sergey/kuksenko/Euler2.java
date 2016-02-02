package com.isatimur.old.presentation.sergey.kuksenko;

import java.math.BigInteger;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by tisachenko on 30.01.2016.
 */
public class Euler2 {
    private static final BigInteger LIMIT = BigInteger.valueOf(4).multiply(BigInteger.TEN.pow(2048));

    static Predicate<BigInteger> filterPredicate = b -> !b.testBit(0);
    static Predicate<BigInteger> takeWhilePredicate = b -> b.compareTo(LIMIT) < 0;

    public static BigInteger seqIterate() {
        Stream<BigInteger> s = FibIterate.stream().filter(filterPredicate);
        return TakeWhile.stream(s, takeWhilePredicate).reduce(BigInteger.ZERO, (x, y) -> y.add(x));
    }

    public static BigInteger parIterate() {
        Stream<BigInteger> s = FibIterate.stream().filter(filterPredicate);
        return TakeWhile.stream(s, takeWhilePredicate).parallel().reduce(BigInteger.ZERO, (x, y) -> y.add(x));
    }


    public static BigInteger seqIterator() {
        Stream<BigInteger> s = FibIterator.stream().filter(filterPredicate);
        return TakeWhile.stream(s, takeWhilePredicate).reduce(BigInteger.ZERO, (x, y) -> y.add(x));
    }


    public static BigInteger seqSum() {
        BigInteger first = BigInteger.ZERO;
        BigInteger second = BigInteger.ONE;
        BigInteger sum = BigInteger.ZERO;
        while (takeWhilePredicate.test(second)) {
            BigInteger s = second.add(first);
            first = second;
            second = s;
            if (filterPredicate.test(first)) {
                sum = sum.add(first);
            }
        }
        return sum;

    }


    public static void main(String[] args) {
        System.out.println("Sequential Iterate");
        System.out.println(seqIterate());
        System.out.println("Parallel Iterate");
        System.out.println(parIterate());
        System.out.println("Sequential Iterator");
        System.out.println(seqIterator());
        System.out.println("Sequential sum old school");
        System.out.println(seqSum());
    }


}
