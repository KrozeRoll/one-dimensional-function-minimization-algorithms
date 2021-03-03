package com.caucasus.optimization.algos;

import java.util.ArrayList;
import java.util.function.Function;

public class Fibonacci extends AbstractIntervalMinFinder {
    public Fibonacci(Function<Double, Double> function, double leftBorder, double rightBorder, double eps) {
        super(function, leftBorder, rightBorder, eps);
    }

    public Fibonacci(Function<Double, Double> function, Interval domain, double eps) {
        super(function, domain, eps);
    }

    @Override
    Solution calculateSolution() {
        double leftBorder = getLeftBorder();
        double rightBorder = getRightBorder();
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(leftBorder, rightBorder));
        final int numberOfIterations = FibonacciGenerator.findIndexOfGreater((long) ((rightBorder - leftBorder) / getEps()));
        double x1 = leftBorder + calcRatioOfNthFibonacci(numberOfIterations, numberOfIterations + 2) * (rightBorder - leftBorder);
        double x2 = leftBorder + calcRatioOfNthFibonacci(numberOfIterations + 1, numberOfIterations + 2) * (rightBorder - leftBorder);
        for (int i = 0; i < numberOfIterations - 2; i++) {
            if (compare(getFunction().apply(x1), getFunction().apply(x2)) <= 0) {
                rightBorder = x2;
                x2 = x1;
                x1 = leftBorder + calcRatioOfNthFibonacci(numberOfIterations - i - 3, numberOfIterations - i - 1) * (rightBorder - leftBorder);
            } else {
                leftBorder = x1;
                x1 = x2;
                x2 = leftBorder + calcRatioOfNthFibonacci(numberOfIterations - i - 2, numberOfIterations - i - 1) * (rightBorder - leftBorder);
            }
            intervals.add(new Interval(leftBorder, rightBorder));
        }
        x2 = x1 + getEps();
        if (compare(getFunction().apply(x1), getFunction().apply(x2)) == 0) {
            leftBorder = x1;
        } else {
            rightBorder = x2;
        }
        intervals.add(new Interval(leftBorder, rightBorder));
        final double endPoint = (leftBorder + rightBorder) * 0.5;
        return new Solution(intervals, endPoint);
    }

    private double calcRatioOfNthFibonacci(int numeratorSerial, int denominatorSerial) {
        return ((double) FibonacciGenerator.getNth(numeratorSerial) / (double) FibonacciGenerator.getNth(denominatorSerial));
    }

    static class FibonacciGenerator {
        // TODO implement getNth

        /**
         * @param n serial number
         * @return nth Fibonacci number
         */
        static public long getNth(int n) {
            return 0;
        }

        // TODO implement indexOfGreater
        static public int findIndexOfGreater(long num) {
            return 0;
        }
    }
}
