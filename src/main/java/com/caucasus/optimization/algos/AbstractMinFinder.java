package com.caucasus.optimization.algos;

import java.util.Comparator;
import java.util.function.Function;

public abstract class AbstractMinFinder implements MinFinder{
    private final Function<Double, Double> function;
    private final Interval domain;
    private final double eps;
    private final double delta;
    private final Comparator<Double> comparator;

    public AbstractMinFinder(Function<Double, Double> function, Interval domain, double eps, double delta) {
        this.function = function;
        this.domain = domain;
        this.eps = eps;
        this.delta = delta;
        this.comparator = new DoubleComparator(eps);
    }

    protected int compare(Double lhs, Double rhs) {
        return comparator.compare(lhs, rhs);
    }

    @Override
    public Function<Double, Double> getFunction() {
        return function;
    }

    @Override
    public double getLeftBorder() {
        return domain.getLeftBorder();
    }

    @Override
    public double getRightBorder() {
        return domain.getRightBorder();
    }

    @Override
    public double getEps() {
        return eps;
    }

    @Override
    public double getDelta() {
        return delta;
    }
}
