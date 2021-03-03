package com.caucasus.optimization.algos;


import java.util.function.Function;

abstract public class AbstractIntervalMinFinder extends AbstractMinFinder implements IntervalMinFinder {
    private final Solution solution;

    public AbstractIntervalMinFinder(Function<Double, Double> function, double leftBorder, double rightBorder, double eps) {
        this(function, new Interval(leftBorder, rightBorder), eps);
    }

    public AbstractIntervalMinFinder(Function<Double, Double> function, Interval domain, double eps) {
        super(function, domain, eps);
        this.solution = calculateSolution();
    }

    @Override
    public Solution getSolution() {
        return solution;
    }

    abstract Solution calculateSolution();
}
