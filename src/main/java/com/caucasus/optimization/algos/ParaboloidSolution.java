package com.caucasus.optimization.algos;

import java.util.ArrayList;
import java.util.function.Function;

public class ParaboloidSolution extends Solution{
    private final ArrayList<Function<Double, Double>> parabolas;

    public ParaboloidSolution(ArrayList<Interval> intervals, double endPoint, ArrayList<Function<Double, Double>> parabolas) {
        super(intervals, endPoint);
        this.parabolas = parabolas;
    }

    public ArrayList<Function<Double, Double>> getParabolas() {
        return parabolas;
    }
}
