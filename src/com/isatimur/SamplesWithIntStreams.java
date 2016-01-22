package com.isatimur;

import java.util.OptionalInt;
import java.util.stream.Stream;

/**
 * Created by Тимакс on 22.01.2016.
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

    }
}
