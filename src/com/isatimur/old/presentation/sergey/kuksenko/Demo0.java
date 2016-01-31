package com.isatimur.old.presentation.sergey.kuksenko;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;

/**
 * Created by tisachenko on 30.01.2016.
 */

public class Demo0 {


    static List<Long> list = LongStream.range(0, 1028).boxed().collect(toList());

    public static void main(String[] args) {

        System.out.println("Old school");
        System.out.println(oldSchool());

        System.out.println("Stream API");
        System.out.println("Ordered");
        list.stream().filter(v -> (v & 0xff) == 0).peek(s -> System.out.println(s)).collect(toList());

        System.out.println("Unordered");
        list.stream().unordered().filter(v -> (v & 0xff) == 0).peek(s -> System.out.println(s)).collect(toList());


        System.out.println("Parallel/Ordered");
        list.parallelStream().filter(v -> (v & 0xff) == 0).peek(s -> System.out.println(s)).collect(toList());

        System.out.println("Parallel/Unordered");
        list.parallelStream().unordered().filter(v -> (v & 0xff) == 0).peek(s -> System.out.println(s)).collect(toList());

    }

    public static List<Long> oldSchool() {
        List<Long> l = new ArrayList<>();
        for (Long v : list) {
            if ((v & 0xff) == 0) {
                l.add(v);
            }

        }
        return l;
    }


}
