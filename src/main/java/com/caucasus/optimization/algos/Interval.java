package com.caucasus.optimization.algos;

public class Interval {
    private final double leftBorder;
    private final double rightBorder;

    public Interval(double leftBorder, double rightBorder) {
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
    }

    public double getLeftBorder() {
        return leftBorder;
    }

    public double getRightBorder() {
        return rightBorder;
    }
}
