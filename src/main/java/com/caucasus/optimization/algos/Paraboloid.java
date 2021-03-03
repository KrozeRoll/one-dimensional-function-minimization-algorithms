package com.caucasus.optimization.algos;

import java.util.function.Function;

public class Paraboloid extends AbstractParaboloidMinFinder{
    public Paraboloid(Function<Double, Double> function, double leftBorder, double rightBorder, double eps) {
        super(function, leftBorder, rightBorder, eps);
    }

    public Paraboloid(Function<Double, Double> function, Interval domain, double eps) {
        super(function, domain, eps);
    }

    @Override
    ParaboloidSolution calculateParaboloidSolution() {
        return null;
    }
}
