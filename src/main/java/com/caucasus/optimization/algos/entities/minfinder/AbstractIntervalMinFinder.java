package com.caucasus.optimization.algos.entities.minfinder;


import com.caucasus.optimization.algos.entities.util.Interval;
import com.caucasus.optimization.algos.entities.util.Solution;
import com.caucasus.optimization.algos.interfaces.IntervalMinFinder;

import java.util.function.Function;

abstract public class AbstractIntervalMinFinder extends AbstractMinFinder implements IntervalMinFinder {
    private Solution solution;

    public AbstractIntervalMinFinder(Function<Double, Double> function, double leftBorder, double rightBorder, double eps) {
        this(function, new Interval(leftBorder, rightBorder), eps);
    }

    public AbstractIntervalMinFinder(Function<Double, Double> function, Interval domain, double eps) {
        super(function, domain, eps);
    }

    @Override
    public Solution getSolution() {
        if (solution == null) {
            solution = calculateSolution();
        }
        return solution;
    }

    protected abstract Solution calculateSolution();
}
