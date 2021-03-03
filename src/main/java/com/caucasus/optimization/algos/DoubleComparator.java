package com.caucasus.optimization.algos;

import java.util.Comparator;

public class DoubleComparator implements Comparator<Double> {
    private final double eps;

    public DoubleComparator(double eps) {
        this.eps = eps;
    }

    @Override
    public int compare(Double lhs, Double rhs) {
        final double v = (Math.max(Math.abs(lhs), Math.abs(rhs))) * eps;
        if (Math.abs(lhs - rhs) <= v) {
            return 0;
        }
        if ((lhs - rhs) > v) {
            return 1;
        }
        return -1;
    }
}
