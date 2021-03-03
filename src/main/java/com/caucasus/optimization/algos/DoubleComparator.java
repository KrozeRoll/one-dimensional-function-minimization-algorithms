package com.caucasus.optimization.algos;

import java.util.Comparator;

public class DoubleComparator implements Comparator<Double> {
    private final double eps;

    public DoubleComparator(double eps) {
        this.eps = eps;
    }

    @Override
    public int compare(Double lhs, Double rhs) {
        return Double.compare(lhs, rhs);
    }
}
