package com.isatimur.aggregate;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by tisachenko on 21.01.2016.
 */
public class TestAggregationOverPerson {
    public static void main(String[] args) {
        List<Person> personsList = Stream.
                of(new Person("John", "Doe", 25, 1.80, 80), new Person("Jane", "Doe", 30, 1.69, 60), new Person("John", "Smith", 35, 174, 70)).collect(toList());
        Integer integer = personsList.stream()
                .filter(person -> person.getFirstName().equals("John"))
                .map(p -> 1).reduce(0,(a,b)->a+b);

        long integer2 = personsList.stream()
                .filter(person -> person.getFirstName().equals("John"))
                .count();
        System.out.println(integer);
        System.out.println(integer2);

    }
}
