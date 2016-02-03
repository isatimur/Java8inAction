package com.isatimur.old.presentation.sergey.kuksenko;

import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Тимакс on 01.02.2016.
 */
public class Zipper {


    public static <A, B, C> Stream<C> zip(Stream<? super A> a, Stream<? super B> b, BiFunction<? super A, ? super B, ? extends C> zipper) {
        Objects.requireNonNull(zipper);
        Spliterator<A> as = (Spliterator<A>) Objects.requireNonNull(a).spliterator();
        Spliterator<B> bs = (Spliterator<B>) Objects.requireNonNull(b).spliterator();

        int characteristics = as.characteristics() & bs.characteristics() & ~(Spliterator.DISTINCT | Spliterator.SORTED);
        long size = Math.min(as.estimateSize(), bs.estimateSize());

        Spliterator<C> cs = new ZipperSpliterator<>(as, bs, zipper, size, characteristics);
        return (a.isParallel() || b.isParallel()) ? StreamSupport.stream(cs, true) : StreamSupport.stream(cs, false);


    }

    public static DoubleStream zip(DoubleStream a, DoubleStream b, DoubleBinaryOperator zipper) {
        Objects.requireNonNull(zipper);
        Spliterator.OfDouble as = (Spliterator.OfDouble) Objects.requireNonNull(a).spliterator();
        Spliterator.OfDouble bs = (Spliterator.OfDouble) Objects.requireNonNull(b).spliterator();

        int characteristics = as.characteristics() & bs.characteristics() & ~(Spliterator.DISTINCT | Spliterator.SORTED);
        long size = Math.min(as.estimateSize(), bs.estimateSize());

        Spliterator.OfDouble cs = new DoubleZipperSpliterator(as, bs, zipper, size, characteristics);
        return (a.isParallel() || b.isParallel()) ? StreamSupport.doubleStream(cs, true) : StreamSupport.doubleStream(cs, false);


    }

    private static final class ExtractingConsumer<T> implements Consumer<T> {
        private T value;

        @Override
        public void accept(T t) {
            this.value = t;
        }

        public T get() {
            return value;
        }
    }

    private static class ZipperSpliterator<A, B, C> extends Spliterators.AbstractSpliterator<C> {

        final Spliterator<A> as;
        final Spliterator<B> bs;
        final BiFunction<? super A, ? super B, ? extends C> zipper;
        final ExtractingConsumer<A> aExtractingConsumer;
        final ExtractingConsumer<B> bExtractingConsumer;


        ZipperSpliterator(Spliterator<A> as, Spliterator<B> bs, BiFunction<? super A, ? super B, ? extends C> zipper, long size, int additionalCharacteristics) {
            super(size, additionalCharacteristics);
            this.as = as;
            this.bs = bs;
            this.zipper = zipper;
            this.aExtractingConsumer = new ExtractingConsumer<>();
            this.bExtractingConsumer = new ExtractingConsumer<>();
        }

        /**
         * Creates a spliterator reporting the given estimated size and
         * additionalCharacteristics.
         */
//        protected ZipperListarator() {
////            super(Stream as, Stream bs, est, additionalCharacteristics);
//        }
        @Override
        public boolean tryAdvance(Consumer<? super C> action) {
            if (as.tryAdvance(aExtractingConsumer) && bs.tryAdvance(bExtractingConsumer)) {

                //action.accept(zipper.apply(aExtractingConsumer.get(),bExtractingConsumer.get()));
                return true;
            }
            return false;
        }
    }

    private static class DoubleZipperSpliterator implements Spliterator.OfDouble {

        final Spliterator.OfDouble as;
        final Spliterator.OfDouble bs;
        final DoubleBinaryOperator zipper;
        final DoubleExtractingConsumer aExtractingConsumer;
        final DoubleExtractingConsumer bExtractingConsumer;


        DoubleZipperSpliterator(Spliterator.OfDouble as, Spliterator.OfDouble bs, DoubleBinaryOperator zipper, long size, int additionalCharacteristics) {
            super();
            this.as = as;
            this.bs = bs;
            this.zipper = zipper;
            this.aExtractingConsumer = new DoubleExtractingConsumer();
            this.bExtractingConsumer = new DoubleExtractingConsumer();
        }

        /**
         * Creates a spliterator reporting the given estimated size and
         * additionalCharacteristics.
         */
//        protected ZipperListarator() {
////            super(Stream as, Stream bs, est, additionalCharacteristics);
//        }
        @Override
        public boolean tryAdvance(DoubleConsumer action) {
            if (as.tryAdvance(aExtractingConsumer) && bs.tryAdvance(bExtractingConsumer)) {
                action.accept((Double) zipper.applyAsDouble(aExtractingConsumer.get(), bExtractingConsumer.get()));
                return true;
            }
            return false;
        }

        @Override
        public OfDouble trySplit() {
            return null;
        }

        @Override
        public long estimateSize() {
            return 0;
        }

        @Override
        public int characteristics() {
            return 0;
        }


    }

    private static class DoubleExtractingConsumer implements DoubleConsumer {
        private double value;

        @Override
        public void accept(double t) {
            this.value = t;
        }

        public double get() {
            return value;
        }

    }
}
