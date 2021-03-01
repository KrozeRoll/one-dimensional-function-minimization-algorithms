package com.caucasus.optimization.algos;

import java.util.function.Function;

public abstract class AbstractMinFinder implements MinFinder{
    protected final Solution solution;
    protected final Function<Double, Double> function;
    protected final Interval domain;
    protected final double eps;
    protected final double delta;

    public AbstractMinFinder(Function<Double, Double> function, double leftBorder, double rightBorder, double eps, double delta) {
        this(function, new Interval(leftBorder, rightBorder), eps, delta);
    }

    public AbstractMinFinder(Function<Double, Double> function, Interval domain, double eps, double delta) {
        this.function = function;
        this.domain = domain;
        this.eps = eps;
        this.delta = delta;
        this.solution = calculateSolution();
    }

    abstract Solution calculateSolution();

    @Override
    public Solution getSolution() {
        return solution;
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
