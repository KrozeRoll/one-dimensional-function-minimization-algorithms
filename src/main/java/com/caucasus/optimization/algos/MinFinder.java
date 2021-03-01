package com.caucasus.optimization.algos;

import java.util.function.Function;

public interface MinFinder {
    Solution getSolution();

    Function<Double, Double> getFunction();

    double getLeftBorder();

    double getRightBorder();

    double getEps();

    double getDelta();

}
