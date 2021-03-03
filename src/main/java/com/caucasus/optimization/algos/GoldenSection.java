package com.caucasus.optimization.algos;

import java.util.ArrayList;
import java.util.function.Function;

public class GoldenSection extends AbstractIntervalMinFinder {
    private static final double TAU = (Math.sqrt(5) - 1) * 0.5;

    public GoldenSection(Function<Double, Double> function, double leftBorder, double rightBorder, double eps) {
        super(function, leftBorder, rightBorder, eps);
    }

    public GoldenSection(Function<Double, Double> function, Interval domain, double eps) {
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
        double x1 = leftBorder + (1 - TAU) * (rightBorder - leftBorder);
        double x2 = leftBorder + TAU * (rightBorder - leftBorder);
        double nthEps = (rightBorder - leftBorder) * 0.5;
        do {
            if (compare(getFunction().apply(x1), getFunction().apply(x2)) <= 0) {
                rightBorder = x2;
                x2 = x1;
                x1 = rightBorder - TAU * (rightBorder - leftBorder);
            } else {
                leftBorder = x1;
                x1 = x2;
                x2 = leftBorder + TAU * (rightBorder - leftBorder);
            }
            nthEps *= TAU;
            intervals.add(new Interval(leftBorder, rightBorder));
            approximatelyMinimums.add((leftBorder + rightBorder) * 0.5);
        } while (compare(nthEps, getEps()) == 1);

        return new Solution(intervals, approximatelyMinimums);
    }

}
