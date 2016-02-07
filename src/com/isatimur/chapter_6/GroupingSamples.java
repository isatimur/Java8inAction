package com.isatimur.chapter_6;

import com.isatimur.Dish;
import com.isatimur.StreamMenu;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * Created by tisachenko on 03.02.2016.
 */
public class GroupingSamples {

    public static void main(String[] args) {
        System.out.println("Dishes by Type");
        Map<Dish.Type, List<Dish>> dishesByType = StreamMenu.menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(dishesByType);

        System.out.println("Dishes by Caloric Level");
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = StreamMenu.menu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }));

        System.out.println(dishesByCaloricLevel);

        System.out.println("Dishes by Type and Caloric Level");
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = StreamMenu.menu.stream().collect(groupingBy(Dish::getType, groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        })));

        System.out.println(dishesByTypeCaloricLevel);


        System.out.println("Counting");
        Map<Dish.Type, Long> typesCount = StreamMenu.menu.stream().collect(
                groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);

        System.out.println("Most caloric dish by type");
        Map<Dish.Type, Optional<Dish>> mostCaloricByType = StreamMenu.menu.stream().collect(groupingBy(Dish::getType, maxBy(comparing(Dish::getCalories))));
        System.out.println(mostCaloricByType);

        System.out.println("Most caloric dish by type - result not Optional");
        Map<Dish.Type, Dish> mostCaloricByTypeNotOptioanl = StreamMenu.menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(comparing(Dish::getCalories)), Optional::get)));
        System.out.println(mostCaloricByTypeNotOptioanl);

        System.out.println("Total calories by type");
        Map<Dish.Type, Integer> totalCaloriesByType =
                StreamMenu.menu.stream().collect(groupingBy(Dish::getType,
                        summingInt(Dish::getCalories)));
        System.out.println(totalCaloriesByType);


        System.out.println("Caloric lavels by type");
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
                StreamMenu.menu.stream().collect(
                        groupingBy(Dish::getType, mapping(
                                dish -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                },
                                toSet())));
        System.out.println(caloricLevelsByType);


        System.out.println("Caloric lavels by type Another");
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByTypeAnother =
                StreamMenu.menu.stream().collect(
                        groupingBy(Dish::getType, mapping(
                                dish -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                },
                                toCollection(HashSet::new))));
        System.out.println(caloricLevelsByTypeAnother);


        System.out.println("==++============================++==");
        System.out.println("Partitioning");
        System.out.println("==++============================++==");

        Map<Boolean, List<Dish>> partitionedMap = StreamMenu.menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMap);


        System.out.println("Vegetarian dishes by type");
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = StreamMenu.menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));

        System.out.println(vegetarianDishesByType);


        System.out.println("most Caloric Partitioned By Vegeterian");

        Map<Boolean, Dish> mostCaloricPartitionedByVegeterian = StreamMenu.menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
        System.out.println(mostCaloricPartitionedByVegeterian);


        System.out.println("Tasks");
        System.out.println("1");
        System.out.println(StreamMenu.menu.stream().collect(partitioningBy(Dish::isVegetarian,
                partitioningBy(d -> d.getCalories() > 500))));

        System.out.println("2 \n //does not compile\n");
        //does not compile
//        System.out.println(StreamMenu.menu.stream().collect(partitioningBy(Dish::isVegetarian,
//                partitioningBy(Dish::getType))));

        System.out.println("3");
        System.out.println(StreamMenu.menu.stream().collect(partitioningBy(Dish::isVegetarian,
                counting())));


        System.out.println("Partitioning number stream");

        GroupingSamples samples = new GroupingSamples();
        System.out.println(samples.partitionPrimes(25));

        System.out.println("Table 6");

        List<Dish> menuStream = StreamMenu.menu;

        List<Dish> dishesList = menuStream.stream().collect(toList());
        Set<Dish> dishesSet = menuStream.stream().collect(toSet());

        Collection<Dish> dishesCollection = menuStream.stream().collect(toList());
        long howManyDishes = menuStream.stream().collect(counting());

        int totalCalories = menuStream.stream().collect(summingInt(Dish::getCalories));

        double avgCalories = menuStream.stream().collect(averagingDouble(Dish::getCalories));

        System.out.println("Statistic about calories in menu");
        IntSummaryStatistics menuStatistics = menuStream.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        String shortMenu = menuStream.stream().map(Dish::getName).collect(joining(", "));

        Optional<Dish> fattest = menuStream.stream().collect(maxBy(comparingInt(Dish::getCalories)));

        Optional<Dish> lightest = menuStream.stream().collect(minBy(comparingInt(Dish::getCalories)));

        int totCalories = menuStream.stream().collect(reducing(0, Dish::getCalories, Integer::sum));

        int howManyDishesSize = menuStream.stream().collect(collectingAndThen(toList(), List::size));

        System.out.println("=================================\n" +
                "Own collector interface");

        List<Dish> dishes = StreamMenu.menu.stream().collect(new ToListCollector<Dish>());
        dishes.forEach(s -> System.out.println(s));

        //another example of the same interface
        System.out.println("//another example of the same interface");
        List<Dish> dishesAnother = StreamMenu.menu.stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        dishes.forEach(s -> System.out.println(s));


        System.out.println("////////////////////////////\n" +
                "New collectore implementation");
        System.out.println(new GroupingSamples().partitionByPrimesWithCustomCollector(20));

    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }
//this is the first try
//    public static boolean isPrime(List<Integer> primes, int candidate) {
//        return primes.stream().noneMatch(i -> candidate % i == 0);
//    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(
                        partitioningBy(candidate -> isPrime(candidate)));
    }

    public static Map<Boolean, List<Integer>> partitionByPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector());
    }

    public static enum CaloricLevel {DIET, NORMAL, FAT}
}
