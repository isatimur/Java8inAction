package com.isatimur;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by tisachenko on 24.01.2016.
 */
public class InfiniteStreams {

    public static void main(String[] args) {
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

        System.out.println("//quiz  5.4");
        Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], (n[0] + n[1])}).limit(20).forEach(s -> System.out.println(s[0] + "," + s[1]));
        System.out.println("//fibonacci");
        Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], (n[0] + n[1])}).map(ints -> ints[0]).limit(20).forEach(s -> System.out.print(s + ","));

        System.out.println("");
        System.out.println("============================");
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        System.out.println("============================");
        IntStream ones = IntStream.generate(() -> 1);
        IntStream twos = IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        });

        System.out.println("//let's make an IntSupplier for fibonacci task");

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int next = this.previous + this.current;
                this.previous = this.current;
                this.current = next;
                return oldPrevious;
            }
        };

        IntStream.generate(fib).limit(10).forEach(System.out::println);


    }
}
