package com.caucasus.optimization.algos;

import java.util.ArrayList;
import java.util.function.Function;

public class Dichotomy extends AbstractIntervalMinFinder {

    public Dichotomy(Function<Double, Double> function, double leftBorder, double rightBorder, double eps) {
        super(function, leftBorder, rightBorder, eps);
    }

    public Dichotomy(Function<Double, Double> function, Interval domain, double eps) {
        super(function, domain, eps);
    }

    @Override
    Solution calculateSolution() {
        double leftBorder = getLeftBorder();
        double rightBorder = getRightBorder();
        ArrayList<Interval> intervals = new ArrayList<>();
        ArrayList<Double> approximatelyMinimums = new ArrayList<>();
        approximatelyMinimums.add((leftBorder + rightBorder) * 0.5);
        intervals.add(new Interval(leftBorder, rightBorder));
        while (!validateAccuracy(leftBorder, rightBorder)) {
            final double x1 = (leftBorder + rightBorder - getDelta()) * 0.5;
            final double x2 = (leftBorder + rightBorder + getDelta()) * 0.5;
            if (compare(getFunction().apply(x1), getFunction().apply(x2)) <= 0) {
                rightBorder = x2;
            } else {
                leftBorder = x1;
            }
            intervals.add(new Interval(leftBorder, rightBorder));
            approximatelyMinimums.add((leftBorder + rightBorder) * 0.5);
        }
        return new Solution(intervals, approximatelyMinimums);
    }

    private double getDelta() {
        return getEps() * 0.5;
    }

    private double calcNthEps(double lb, double rb) {
        return (rb - lb) * 0.5;
    }

    private boolean validateAccuracy(double lb, double rb) {
        return compare(calcNthEps(lb, rb), getEps()) <= 0;
    }
}
