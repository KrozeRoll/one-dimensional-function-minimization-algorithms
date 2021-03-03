package com.caucasus.optimization.algos;

import java.util.ArrayList;
import java.util.function.Function;

public class Paraboloid extends AbstractParaboloidMinFinder {
    public Paraboloid(Function<Double, Double> function, double leftBorder, double rightBorder, double eps) {
        super(function, leftBorder, rightBorder, eps);
    }

    public Paraboloid(Function<Double, Double> function, Interval domain, double eps) {
        super(function, domain, eps);
    }

    @Override
    ParaboloidSolution calculateParaboloidSolution() {
        double leftBorder = getLeftBorder();
        double rightBorder = getRightBorder();
        ArrayList<Interval> intervals = new ArrayList<>();
        ArrayList<Function<Double, Double>> functions = new ArrayList<>();
        ArrayList<Double> approximatelyMinimums = new ArrayList<>();
        intervals.add(new Interval(leftBorder, rightBorder));
        // FIXME
        double x1 = 0;
        double x2 = 0;
        double x3 = 0;
        Parabola parabola = new Parabola(x1, x2, x3);
        approximatelyMinimums.add(parabola.getPointOfMin());
        functions.add(parabola.getFunction());
        while (!validateAccuracy(leftBorder, rightBorder)) {
            if (compare(x1, x2) < 0);
        }

        return null;
    }

    private double calcNthEps(double lb, double rb) {
        return (rb - lb) * 0.5;
    }

    private boolean validateAccuracy(double lb, double rb) {
        return compare(calcNthEps(lb, rb), getEps()) <= 0;
    }

    private class Parabola {
        private final Function<Double, Double> function;
        private final double pointOfMin;

        public Parabola(double x1, double x2, double x3) {
            final double f1 = getFunction().apply(x1);
            final double f2 = getFunction().apply(x2);
            final double f3 = getFunction().apply(x3);
            final double a1 = (f2 - f1) / (x2 - x1);
            final double a2 = ((f3 - f1) / (x3 - x1) - (f2 - f1) / (x2 - x1)) / (x3 - x2);
            this.function =  x -> f1 + a1 * (x - x1) + a2 * (x - x1) * (x - x2);
            this.pointOfMin = (x1 + x2 + a1 / a2) * 0.5;
        }

        public Function<Double, Double> getFunction() {
            return function;
        }

        public double getPointOfMin() {
            return pointOfMin;
        }
    }


}
