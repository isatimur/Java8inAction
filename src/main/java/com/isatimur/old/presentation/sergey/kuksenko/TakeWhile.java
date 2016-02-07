package com.isatimur.old.presentation.sergey.kuksenko;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by tisachenko on 30.01.2016.
 */
public class TakeWhile {

    public static <T> Stream<T> stream(Stream<? extends T> source, Predicate<T> predicate) {

        Spliterator<? extends T> spliterator = source.spliterator();
        Iterator<T> iterator = Spliterators.iterator(spliterator);
        TakeWhileIterator<T> takeWhileIterator = new TakeWhileIterator<>(iterator, predicate);
        Spliterator<T> s = Spliterators.spliteratorUnknownSize(takeWhileIterator, spliterator.characteristics() | Spliterator.ORDERED);
        return StreamSupport.stream(s, false);
    }


    private static class TakeWhileIterator<T> implements Iterator<T> {

        private final Predicate<T> predicate;
        private final Iterator<T> iterator;
        private T element;
        private boolean hasNext = true;
        private boolean nextChecked = false;

        private TakeWhileIterator(Iterator<T> iterator, Predicate<T> predicate) {
            this.predicate = predicate;
            this.iterator = iterator;

        }

        @Override
        public boolean hasNext() {
            if (hasNext) {
                if (!nextChecked) {
                    nextChecked = true;
                    if (iterator.hasNext()) {
                        T e = iterator.next();
                        if (predicate.test(e)) {
                            element = e;
                        } else {
                            hasNext = false;
                        }
                    } else {
                        hasNext = false;
                    }
                }
                return hasNext;

            } else {
                return false;
            }
        }

        @Override
        public T next() {
            if (hasNext()) {
                T e = element;
                nextChecked = false;
                element = null;
                return e;
            }
            throw new NoSuchElementException();
        }
    }
}
