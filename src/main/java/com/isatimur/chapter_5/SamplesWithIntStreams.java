package com.isatimur.chapter_5;

import com.isatimur.chapter_4.Dish;
import com.isatimur.chapter_4.StreamMenu;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by tisachenko on 22.01.2016.
 */
public class SamplesWithIntStreams {

    public static void main(String[] args) {
        System.out.println("IntStream");
        System.out.println("//how much calories in this menu?");
        StreamMenu.menu.forEach(s -> System.out.println(s));
        int sumCaloriesInMenu = StreamMenu.menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(sumCaloriesInMenu);
        System.out.println("Converting stream from IntStream back to usual Stream with boxed() method");
        Stream<Integer> boxedCalories = StreamMenu.menu.stream().mapToInt(Dish::getCalories).boxed();

        System.out.println("//Optional primitive value: Opt");
        OptionalInt maxCalories = StreamMenu.menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max = maxCalories.orElse(1);
        System.out.println(max);

        Stream<int[]> pythagoreanTriple = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> (IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed()
                        .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}))).limit(3);
        pythagoreanTriple.forEach(s -> System.out.println(s[0] + ", " + s[1] + s[2]));

        Stream<double[]> pythagonTripleDouble = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> (IntStream.rangeClosed(a, 100).mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0))).limit(3);
        pythagonTripleDouble.forEach(s -> System.out.println(s[0] + ", " + s[1] + s[2]));

        int[] number = {2, 3, 7, 5, 11, 13};
        int sum = Arrays.stream(number).sum();
        System.out.println(sum);
    }


}
