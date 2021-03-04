package com.caucasus.optimization.algos.entities.minfinder;

import com.caucasus.optimization.algos.entities.util.Interval;
import com.caucasus.optimization.algos.entities.util.ParaboloidSolution;
import com.caucasus.optimization.algos.interfaces.ParaboloidMinFinder;

import java.util.function.Function;

/**
 * This class provides a skeletal implementation of the ParaboloidMinFinder interface to minimize the effort required to implement this interface.
 *
 * @see ParaboloidMinFinder
 */
abstract public class AbstractParaboloidMinFinder extends AbstractMinFinder implements ParaboloidMinFinder {
    private ParaboloidSolution paraboloidSolution;

    /**
     * Sole constructor
     *
     * @param function    function on which to search
     * @param leftBorder  left border of domain of function definition
     * @param rightBorder right border of domain of function definition
     * @param eps         epsilon which is used to calculate
     */
    public AbstractParaboloidMinFinder(Function<Double, Double> function, double leftBorder, double rightBorder, double eps) {
        this(function, new Interval(leftBorder, rightBorder), eps);
    }

    /**
     * Sole constructor
     *
     * @param function function on which to search
     * @param domain   domain of function definition
     * @param eps      epsilon which is used to calculate
     */
    public AbstractParaboloidMinFinder(Function<Double, Double> function, Interval domain, double eps) {
        super(function, domain, eps);
    }

    /**
     * This method provides solution by method implementation
     *
     * @return paraboloid method solution
     */
    abstract ParaboloidSolution calculateParaboloidSolution();

    @Override
    public ParaboloidSolution getParaboloidSolution() {
        if (paraboloidSolution == null) {
            paraboloidSolution = calculateParaboloidSolution();
        }
        return paraboloidSolution;
    }
}
