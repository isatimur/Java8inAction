package com.isatimur.old.presentation.sergey.kuksenko;

import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

/**
 * Created by tisachenko on 01.02.2016.
 */
public class MonteCarlo {
    public static final long SIZE = 1_000_000_000;

    public double getPiOldSchool() {
        long cnt = 0;
        for (long i = 0; i < SIZE; i++) {
            double x1 = ThreadLocalRandom.current().nextDouble();
            double x2 = ThreadLocalRandom.current().nextDouble();
            if (x1 * x1 + x2 * x2 < 1.0) {
                cnt++;
            }
        }


        return 4.0 * cnt / SIZE;
    }

    public double getPiZipBoxed() {
        DoubleStream as = new SplittableRandom().doubles(SIZE);
        DoubleStream bs = new SplittableRandom().doubles(SIZE);
        long cnt = Zipper.zip(as.boxed(), bs.boxed(), (x, y) -> x * x + y * y).filter(x -> x < 1.0).count();
        return 4.0 * cnt / SIZE;
    }

    public double getPiZipDouble() {
        DoubleStream as = new SplittableRandom().doubles(SIZE);
        DoubleStream bs = new SplittableRandom().doubles(SIZE);
        long cnt = Zipper.zip(as, bs, (x, y) -> (double)x * (double)x + (double)y * (double)y).filter(x -> x < 1.0).count();
        return 4.0 * cnt / SIZE;
    }


    public static void main(String[] args) {
        MonteCarlo carlo = new MonteCarlo();
//        System.out.println("Get Pi by using Old School!");
//        System.out.println(carlo.getPiOldSchool());
        System.out.println("Get Pi by using Zip!");
        System.out.println(carlo.getPiZipBoxed());
        System.out.println("Get Pi by using DoubleStreamZip!");
        System.out.println(carlo.getPiZipDouble());
    }

}
