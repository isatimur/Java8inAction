package com.isatimur.old.presentation.sergey.kuksenko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Тимакс on 30.01.2016.
 */
public class StreamCollectors {
    public static void main(String[] args) {
        String a[] = new String[]{"a", "b", "c"};
        String result = Arrays.stream(a).collect(Collectors.joining(","));
        System.out.println(result);


        Stream<String> s = Stream.of("Hello", " ", "WORLD!");
        // this is short description of next implementation
        List<String> list = s.collect(Collectors.toList());
        // this is a whole implementation how function is applies to elements in collector <Supplier<R> supplier,BiConsumer<R,T> accumulator,BiConsumer<R,T> combiner>
        s = Stream.of("Hello", " ", "WORLD!");
        s.collect(() -> new ArrayList<>(), (listt, t) -> listt.add(t), (l1, l2) -> l1.addAll(l2));

        // and the third implementation of the same toList() method but using method's references
        s = Stream.of("Hello", " ", "WORLD!");
        s.collect(ArrayList::new, List::add, List::addAll);

    }
}
