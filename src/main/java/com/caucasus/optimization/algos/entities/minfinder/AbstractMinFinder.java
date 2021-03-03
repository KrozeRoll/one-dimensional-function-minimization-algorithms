package com.caucasus.optimization.algos.entities.minfinder;

import com.caucasus.optimization.algos.entities.util.DoubleComparator;
import com.caucasus.optimization.algos.entities.util.Interval;
import com.caucasus.optimization.algos.interfaces.MinFinder;

import java.util.Comparator;
import java.util.function.Function;

public abstract class AbstractMinFinder implements MinFinder {
    private final Function<Double, Double> function;
    private final Interval domain;
    private final double eps;
    private final Comparator<Double> comparator;

    public AbstractMinFinder(Function<Double, Double> function, Interval domain, double eps) {
        this.function = function;
        this.domain = domain;
        this.eps = eps;
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
}
