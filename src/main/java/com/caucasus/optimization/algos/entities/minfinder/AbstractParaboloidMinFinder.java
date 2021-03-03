package com.caucasus.optimization.algos.entities.minfinder;

import com.caucasus.optimization.algos.entities.util.Interval;
import com.caucasus.optimization.algos.entities.util.ParaboloidSolution;
import com.caucasus.optimization.algos.interfaces.ParaboloidMinFinder;

import java.util.function.Function;

abstract public class AbstractParaboloidMinFinder extends AbstractMinFinder implements ParaboloidMinFinder {
    private ParaboloidSolution paraboloidSolution;

    public AbstractParaboloidMinFinder(Function<Double, Double> function, double leftBorder, double rightBorder, double eps) {
        this(function, new Interval(leftBorder, rightBorder), eps);
    }

    public AbstractParaboloidMinFinder(Function<Double, Double> function, Interval domain, double eps) {
        super(function, domain, eps);
    }

    abstract ParaboloidSolution calculateParaboloidSolution();

    @Override
    public ParaboloidSolution getParaboloidSolution() {
        if (paraboloidSolution == null) {
            paraboloidSolution = calculateParaboloidSolution();
        }
        return paraboloidSolution;
    }
}
