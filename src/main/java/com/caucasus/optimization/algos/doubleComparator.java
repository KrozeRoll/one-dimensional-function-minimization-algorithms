package com.caucasus.optimization.algos;

import java.util.Comparator;

public class doubleComparator implements Comparator<Double> {
    private final double eps;

    public doubleComparator(double eps) {
        this.eps = eps;
    }

    @Override
    public int compare(Double lhs, Double rhs) {
        return 0;
    }
}
