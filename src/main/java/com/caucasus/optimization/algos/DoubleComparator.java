package com.caucasus.optimization.algos;

import java.util.Comparator;

public class DoubleComparator implements Comparator<Double> {
    private final double eps;

    public DoubleComparator(double eps) {
        this.eps = eps;
    }

    @Override
    public int compare(Double lhs, Double rhs) {
        if (Math.abs(rhs - lhs) <= eps) {
            return 0;
        }
        if (rhs - lhs > eps) {
            return 1;
        }
        return -1;
    }
}
