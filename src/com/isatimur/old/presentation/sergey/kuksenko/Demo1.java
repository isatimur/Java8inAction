package com.isatimur.old.presentation.sergey.kuksenko;

import java.math.BigInteger;
import java.util.function.BinaryOperator;

/**
 * Created by tisachenko on 30.01.2016.
 */
public class Demo1 {

    public static final int SIZE = 20;


    static BinaryOperator<BigInteger> action = (x, y) -> x.add(y);

    public static BigInteger seqSum() {
        BigInteger first = BigInteger.ZERO;
        BigInteger second = BigInteger.ONE;
        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < SIZE; i++) {
            BigInteger s = second.add(first);
            first = second;
            second = s;
            sum = sum.add(first);
        }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println("Fibonachi Generate");
        System.out.println(FibGenerate.parSum());
        System.out.println("Fibonachi Iterate");
        System.out.println(FibIterate.parSum());
        System.out.println("Fibonachi Iterator");
        System.out.println(FibIterator.parSum());
        System.out.println("Fibonachi old school");
        System.out.println(seqSum());
    }

}
