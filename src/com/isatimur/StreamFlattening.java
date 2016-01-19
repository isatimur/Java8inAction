package com.isatimur;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by tisachenko on 19.01.2016.
 */
public class StreamFlattening {

    public static void main(String[] args) {

        //lets identify unique letter in a list of strings - flattening is the only one option to map Stream of array to Stream of type
        List<String> words = Arrays.asList("Hello", "to", "my", "new", "World");


        words
                .stream()
                .map(w -> w.toLowerCase().split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(s -> System.out.print(s + " "));


        int[] numbers = new int[]{};
        //square of each number in a stream
        IntStream.of(1, 2, 3, 4, 5).map(d -> d * d).forEach(value -> System.out.println(value));


        // make all pairs of numbers within flattening
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5);

        list1.stream().flatMap(i -> list2.stream().map(j -> new int[]{i, j})).forEach(ints -> {
            System.out.println(ints[0] + ", " + ints[1]);
        });
        System.out.println("===================");

        //improved by filtering mod 3
        list1.stream()
                .flatMap(i -> list2
                        .stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .forEach(ints -> System.out.println(ints[0] + ", " + ints[1]));

    }

}
