package com.caucasus.optimization.algos;

import java.util.function.Function;

abstract public class AbstractParaboloidMinFinder extends AbstractMinFinder implements ParaboloidMinFinder {
    final private ParaboloidSolution paraboloidSolution;

    public AbstractParaboloidMinFinder(Function<Double, Double> function, double leftBorder, double rightBorder, double eps, double delta) {
        this(function, new Interval(leftBorder, rightBorder), eps, delta);
    }

    public AbstractParaboloidMinFinder(Function<Double, Double> function, Interval domain, double eps, double delta) {
        super(function, domain, eps, delta);
        this.paraboloidSolution = calculateParaboloidSolution();
    }

    abstract ParaboloidSolution calculateParaboloidSolution();

    @Override
    public ParaboloidSolution getParaboloidSolution() {
        return paraboloidSolution;
    }
}
