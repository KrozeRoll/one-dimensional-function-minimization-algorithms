package com.caucasus.optimization.algos;

import java.util.ArrayList;
import java.util.function.Function;

public class Dichotomy extends AbstractMinFinder{

    public Dichotomy(Function<Double, Double> function, double leftBorder, double rightBorder, double eps, double delta) {
        super(function, leftBorder, rightBorder, eps, delta);
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
            if (function.apply(x1) <= function.apply(x2)) {
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

// FiXME write right approach to compare doubles
    private boolean validateAccuracy(double lb, double rb) {
        return calcNthEps(lb, rb) <= eps;
    }
}
