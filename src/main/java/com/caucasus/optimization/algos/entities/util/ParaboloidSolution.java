package com.caucasus.optimization.algos.entities.util;

import com.caucasus.optimization.algos.entities.util.Interval;
import com.caucasus.optimization.algos.entities.util.Solution;

import java.util.ArrayList;
import java.util.function.Function;

public class ParaboloidSolution extends Solution {
    private final ArrayList<Function<Double, Double>> parabolas;

    public ParaboloidSolution(ArrayList<Interval> intervals, ArrayList<Double> approximatelyMinimums, ArrayList<Function<Double, Double>> parabolas) {
        super(intervals, approximatelyMinimums);
        this.parabolas = parabolas;
    }

    public ArrayList<Function<Double, Double>> getParabolas() {
        return parabolas;
    }
}
