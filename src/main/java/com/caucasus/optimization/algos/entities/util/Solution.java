package com.caucasus.optimization.algos.entities.util;

import com.caucasus.optimization.algos.entities.util.Interval;

import java.util.ArrayList;

public class Solution {
    private final ArrayList<Interval> intervals;
    private final ArrayList<Double> approximatelyMinimums;

    public Solution(ArrayList<Interval> intervals, ArrayList<Double> approximatelyMin) {
        this.intervals = intervals;
        this.approximatelyMinimums = approximatelyMin;
    }

    public ArrayList<Interval> getIntervals() {
        return intervals;
    }

    public double getEndPoint() {
       return approximatelyMinimums.get(approximatelyMinimums.size() - 1);
    }

    public ArrayList<Double> getApproximatelyMinimums() {
        return approximatelyMinimums;
    }
}
