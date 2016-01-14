package com.isatimur;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by tisachenko on 14.01.2016.
 */
public class StreamsSample {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        List<String> threeHighestCaloriesDishes = menu.parallelStream().
                filter(d -> d.getCalories() > 300).
                sorted(Comparator.comparing(Dish::getCalories)).
                map(Dish::getName).
                limit(3).
                collect(toList());

        threeHighestCaloriesDishes.forEach(s -> System.out.println(s));
        System.out.println("====================");
        threeHighestCaloriesDishes.stream().distinct().forEach(s -> System.out.println(s));

    }

}

