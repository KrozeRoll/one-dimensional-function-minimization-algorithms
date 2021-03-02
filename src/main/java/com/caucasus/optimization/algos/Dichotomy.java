package com.caucasus.optimization.algos;

import java.util.ArrayList;
import java.util.function.Function;

public class Dichotomy extends AbstractIntervalMinFinder {

    public Dichotomy(Function<Double, Double> function, double leftBorder, double rightBorder, double eps, double delta) {
        super(function, leftBorder, rightBorder, eps, delta);
    }

    public Dichotomy(Function<Double, Double> function, Interval domain, double eps, double delta) {
        super(function, domain, eps, delta);
    }

    @Override
    Solution calculateSolution() {
        double left = getLeftBorder();
        double right = getRightBorder();
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(left, right));
        while (!validateAccuracy(left, right)) {
            final double x1 = (left + right - delta) * 0.5;
            final double x2 = (left + right + delta) * 0.5;
            if (compare(function.apply(x1), function.apply(x2)) <= 0) {
                right = x2;
            } else {
                left = x1;
            }
            intervals.add(new Interval(left, right));
        }
        double endPoint = (left + right) * 0.5;

        return new Solution(intervals, endPoint);
    }

    private double calcNthEps(double lb, double rb) {
        return (rb - lb) * 0.5;
    }

    private boolean validateAccuracy(double lb, double rb) {
        return compare(calcNthEps(lb, rb), eps) <= 0;
    }
}
