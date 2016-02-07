package com.isatimur;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by tisachenko on 14.12.15.
 */
public class TestStreamUsage {

    public static void main(String[] args) {


        //creation String's stream
        Stream<String> stringStream = Stream.of("a", "b", "c");
        System.out.println(stringStream.collect(toList()));


        String[] array = {"a1", "a2", "a3"};
        Stream<String> streamFromArrays = Arrays.stream(array);
        System.out.println(streamFromArrays.collect(toList()));

    }
}
