package com.isatimur.chapter_4;

/**
 * Created by tisachenko on 14.01.2016.
 */
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    public String toString() {
        return "{Dish is " + name + " has " + calories + " calories}";
    }

    public enum Type {MEAT, FISH, OTHER}
}