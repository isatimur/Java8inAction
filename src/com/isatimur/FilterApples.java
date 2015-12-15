package com.isatimur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

//Chapter 1 of the Java 8 in action book
public class FilterApples {


    public static void main(String[] args) {
        //File[] hiddenFiles = new File("/home/tisachenko/.").listFiles(File::isHidden);
        List<Apple> appleList = Arrays.asList(new Apple(12, "green"), new Apple(44, "blue"), new Apple(56, "red"));
        System.out.println(filterApples(appleList, a -> a.getWeight() > 30));
        System.out.println(filterApples(appleList, a -> "green".equals(a.getColor())));
        System.out.println(filterApples(appleList, Apple::isBlue));

        //Example of streams
        System.out.println(appleList.parallelStream().filter(a -> a.getWeight() > 13 && a.getColor().equals("red")).collect(toList()));
        System.out.println(appleList.stream().filter(a -> a.getWeight() > 13 && a.getColor().equals("red")).collect(toList()));


    }

    public static List<Apple> filterApples(List<Apple> apples, Predicate<Apple> predicate) {
        List<Apple> resultApple = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple)) {
                resultApple.add(apple);
            }
        }
        return resultApple;
    }

    public static class Apple {
        private int weight;
        private String color;


        public static boolean isBlue(Apple apple) {
            return apple.getColor().equals("blue");
        }


        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "weight=" + weight +
                    ", color='" + color + '\'' +
                    '}';
        }
    }
}

