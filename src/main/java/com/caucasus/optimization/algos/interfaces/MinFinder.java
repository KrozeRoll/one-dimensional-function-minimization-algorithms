package com.caucasus.optimization.algos.interfaces;

import java.util.function.Function;

public interface MinFinder {

    Function<Double, Double> getFunction();

    double getLeftBorder();

    double getRightBorder();

    double getEps();

}
