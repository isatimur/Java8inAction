package com.isatimur.old.presentation.sergey.kuksenko;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by tisachenko on 30.01.2016.
 */
public class FibIterator {
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
        Iterator<BigInteger> bigIntegerIterator = new FibonacciIterator();
        Spliterator<BigInteger> bigIntegerSpliterator = Spliterators.spliteratorUnknownSize(bigIntegerIterator, Spliterator.ORDERED | Spliterator.SORTED | Spliterator.NONNULL | Spliterator.IMMUTABLE);

        return StreamSupport.stream(bigIntegerSpliterator, false);
    }


    static class FibonacciIterator implements Iterator<BigInteger> {

        private BigInteger first = BigInteger.ZERO;
        private BigInteger second = BigInteger.ONE;

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public BigInteger next() {
            BigInteger s = second.add(first);
            first = second;
            second = s;
            return first;
        }
    }
}
