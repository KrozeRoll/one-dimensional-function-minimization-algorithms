package com.caucasus.optimization.algos;

import java.util.function.Function;

abstract public class AbstractParaboloidMinFinder extends AbstractMinFinder implements ParaboloidMinFinder {
    final private ParaboloidSolution paraboloidSolution;

    public AbstractParaboloidMinFinder(Function<Double, Double> function, double leftBorder, double rightBorder, double eps) {
        this(function, new Interval(leftBorder, rightBorder), eps);
    }

    public AbstractParaboloidMinFinder(Function<Double, Double> function, Interval domain, double eps) {
        super(function, domain, eps);
        this.paraboloidSolution = calculateParaboloidSolution();
    }

    abstract ParaboloidSolution calculateParaboloidSolution();

    @Override
    public ParaboloidSolution getParaboloidSolution() {
        return paraboloidSolution;
    }
}
