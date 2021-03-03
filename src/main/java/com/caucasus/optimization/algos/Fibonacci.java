package com.caucasus.optimization.algos;

import java.util.function.Function;

public class Fibonacci extends AbstractIntervalMinFinder{
    public Fibonacci(Function<Double, Double> function, double leftBorder, double rightBorder, double eps, double delta) {
        super(function, leftBorder, rightBorder, eps, delta);
    }

    public Fibonacci(Function<Double, Double> function, Interval domain, double eps, double delta) {
        super(function, domain, eps, delta);
    }

    @Override
    Solution calculateSolution() {
        return null;
    }
}
