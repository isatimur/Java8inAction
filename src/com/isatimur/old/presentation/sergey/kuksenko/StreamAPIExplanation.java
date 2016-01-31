package com.isatimur.old.presentation.sergey.kuksenko;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Тимакс on 29.01.2016.
 */
public class StreamAPIExplanation {

    public static void main(String[] args) {
        Stream<Integer> stream = IntStream.range(0, 100).mapToObj(i -> 1);
        System.out.println(getSum(stream));


        Stream<Integer> s = IntStream.range(0, 100).mapToObj(i -> 1).parallel();
        System.out.println(getSum(s));
    }

    //wrong example
//    private int getSum(Stream<Integer> integerStream){
//        int sum=0;
//        //integerStream.forEach(s->sum+s);// doesnt compile because int sum; is not final
//        return sum;
//    }
    private static int getSum(Stream<Integer> integerStream) {
        int[] sum = new int[1];
        integerStream.forEach(i -> sum[0] += i);
        return sum[0];
    }
}
