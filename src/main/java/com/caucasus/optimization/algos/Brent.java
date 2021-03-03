package com.caucasus.optimization.algos;

import java.util.function.Function;

public class Brent extends AbstractIntervalMinFinder{
    public Brent(Function<Double, Double> function, double leftBorder, double rightBorder, double eps, double delta) {
        super(function, leftBorder, rightBorder, eps, delta);
    }

    public Brent(Function<Double, Double> function, Interval domain, double eps, double delta) {
        super(function, domain, eps, delta);
    }

    @Override
    Solution calculateSolution() {
        return null;
    }
}
