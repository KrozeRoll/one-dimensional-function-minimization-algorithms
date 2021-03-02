package com.caucasus.optimization.algos;

import java.util.ArrayList;

public class Solution {
    private final ArrayList<Interval> intervals;
    private final double endPoint;

    public Solution(ArrayList<Interval> intervals, double endPoint) {
        this.intervals = intervals;
        this.endPoint = endPoint;
    }

    public ArrayList<Interval> getIntervals() {
        return intervals;
    }

    public double getEndPoint() {
        return endPoint;
    }
}
