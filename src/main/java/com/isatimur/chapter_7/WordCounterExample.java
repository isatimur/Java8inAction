package com.isatimur.chapter_7;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by tisachenko on 07.02.16.
 */
public class WordCounterExample {

    public static void main(String[] args) {
        String texxt = "In  Limbo  reside  the  unbaptized  and  the  virtuous  pagans  who  although  not  sinful  did  not  accept  Christ";

//        Stream<Character> stream = IntStream.range(0,texxt.length()).mapToObj(texxt::charAt);

        Stream<Character> stream = IntStream.range(0, texxt.length()).mapToObj(texxt::charAt);
        System.out.println(countWords(stream.parallel()));

        System.out.println("The solution within Spliterator implementation");
        Spliterator<Character> spliterator = new WordCounterSpliterator(texxt);
        Stream<Character> parallelStream = StreamSupport.stream(spliterator, true);
        System.out.println(countWords(parallelStream));

    }

    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }
}
