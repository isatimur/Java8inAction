package com.isatimur;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

/**
 * Created by Тимакс on 22.01.2016.
 */
public class Streams5_5 {


    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");


        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        System.out.println("1. Find all transactions in the year 2011 and sort them by value (small to high).");
        transactions.stream().filter(d -> d.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).collect(toList()).forEach(s -> System.out.println(s));

        System.out.println("2. What are all the unique cities where the traders work?");
        transactions.stream().map(tr -> tr.getTrader()).map(c -> c.getCity()).distinct().collect(toList()).forEach(s -> System.out.println(s));
        System.out.println("//author advised also another solution there at the terminate operation you use Set Collector by usage of .collect(toSet()) instead of usual .collect(toList())");
        transactions.stream().map(tr -> tr.getTrader()).map(c -> c.getCity()).collect(toSet()).forEach(s -> System.out.println(s));

        System.out.println("3. Find all traders from Cambridge and sort them by name.");
        transactions.stream().map(tr -> tr.getTrader()).filter(c -> c.getCity().equals("Cambridge")).distinct().sorted(Comparator.comparing(Trader::getName)).collect(toList()).forEach(s -> System.out.println(s));


        System.out.println("4. Return a string of all traders’ names sorted alphabetically.");
        String names = transactions.stream().map(tr -> tr.getTrader()).map(Trader::getName).distinct().sorted().reduce("", (a, b) -> a + " " + b);
        System.out.println(names);
        System.out.println("//another solution from author is to use collector with joining() function instead of reduce() method.");
        System.out.println("//it uses StringBuilder internally");
        String namesStrBuilder = transactions.stream().map(tr -> tr.getTrader()).map(Trader::getName).distinct().sorted().collect(joining(" "));
        System.out.println(namesStrBuilder);

        System.out.println("5. Are any traders based in Milan?");
        Optional<Trader> anyTrader = transactions.parallelStream().map(transaction -> transaction.getTrader()).filter(trader -> trader.getCity().equals("Milan")).findAny();
        anyTrader.ifPresent(s -> System.out.println(s));
        System.out.println("//let's see other way - authors' point of view: ");
        boolean milanBased = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(milanBased);

        System.out.println("6. Print all transactions’ values from the traders living in Cambridge.");
        transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).collect(toList()).forEach(s -> System.out.println(s));

        System.out.println("7. What’s the highest value of all the transactions?");
        Optional<Integer> highestValue = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        highestValue.ifPresent(s -> System.out.println(s));

        System.out.println("8. Find the transaction with the smallest value.");
        Optional<Transaction> minimalTransaction = transactions.stream().reduce((tr1,tr2)->(tr1.getValue() < tr2.getValue() ? tr1 : tr2));
        minimalTransaction.ifPresent(s-> System.out.println(s));


    }


}
