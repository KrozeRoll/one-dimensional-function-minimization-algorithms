package com.caucasus.optimization.algos;

import java.util.function.Function;

public class Brent extends AbstractIntervalMinFinder{
    public Brent(Function<Double, Double> function, double leftBorder, double rightBorder, double eps) {
        super(function, leftBorder, rightBorder, eps);
    }

    public Brent(Function<Double, Double> function, Interval domain, double eps) {
        super(function, domain, eps);
    }

    @Override
    Solution calculateSolution() {
        return null;
    }
}
