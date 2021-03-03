package com.caucasus.optimization.algos.entities.minfinder;

import com.caucasus.optimization.algos.entities.util.Interval;
import com.caucasus.optimization.algos.entities.util.Solution;

import java.util.function.Function;

public class Brent extends AbstractIntervalMinFinder {
    public Brent(Function<Double, Double> function, double leftBorder, double rightBorder, double eps) {
        super(function, leftBorder, rightBorder, eps);
    }

    public Brent(Function<Double, Double> function, Interval domain, double eps) {
        super(function, domain, eps);
    }

    @Override
    Solution calculateSolution() {
        double leftBorder = getLeftBorder();
        double rightBorder = getRightBorder();
        ArrayList<Interval> intervals = new ArrayList<>();
        ArrayList<Double> approximatelyMinimums = new ArrayList<>();
        approximatelyMinimums.add((leftBorder + rightBorder) * 0.5);
        intervals.add(new Interval(leftBorder, rightBorder));
        if (compare(getFunction().apply(leftBorder * getFunction().apply(rightBorder)), 0.) >= 0) {
            throw new IllegalArgumentException("Root is no bracketed");
        }
        if (compare(Math.abs(getFunction().apply(leftBorder)), Math.abs(getFunction().apply(rightBorder))) < 0) {
            double temp = leftBorder;
            leftBorder = rightBorder;
            rightBorder = temp;
        }
        double s;
        double c = leftBorder;
        boolean mFlag = true;
        double d = 0;
        while (compare(getFunction().apply(rightBorder), 0.) != 0 && compare(Math.abs(rightBorder - leftBorder), getEps()) > 0) {
            final double fl = getFunction().apply(leftBorder);
            final double fr = getFunction().apply(rightBorder);
            final double fc = getFunction().apply(c);
            if (compare(fl, fc) != 0 && compare(fr, fc) != 0) {
                s = interpolation(leftBorder, fr, fc, fl) + interpolation(rightBorder, fl, fc, fr) + interpolation(c, fl, fr, fc);
            } else {
                s = rightBorder - fr * (rightBorder - leftBorder) / (fr - fl);
            }

            if (!isBetween(s, (3 * leftBorder + rightBorder) * 0.25, rightBorder)
                    || mFlag && compare(Math.abs(s - rightBorder), Math.abs(rightBorder - c) * 0.5) >= 0
                    || !mFlag && compare(Math.abs(s - rightBorder), Math.abs(c - d) * 0.5) >= 0
                    || mFlag && compare(Math.abs(rightBorder - c), getEps()) < 0
                    || !mFlag && compare(Math.abs(c - d), getEps()) < 0) {
                s = (leftBorder + rightBorder) * 0.5;
                mFlag = true;
            } else {
                mFlag = false;
            }
            final double fs = getFunction().apply(s);
            d = c;
            if (compare(fl * fs, 0.) < 0) {
                rightBorder = s;
            } else {
                leftBorder = s;
            }
            if (compare(Math.abs(getFunction().apply(leftBorder)), Math.abs(getFunction().apply(rightBorder))) < 0) {
                double temp = leftBorder;
                leftBorder = rightBorder;
                rightBorder = temp;
            }
            approximatelyMinimums.add(s);
            intervals.add(new Interval(leftBorder, rightBorder));
        }
        return new Solution(intervals, approximatelyMinimums);
    }

    /**
     * @param x finding value
     * @param l left border
     * @param r right border
     * @return true if x greater than l and less than r, else false
     */
    private boolean isBetween(double x, double l, double r) {
        return compare(x, l) == 1 && compare(x, r) == -1;
    }


    private double interpolation(double var1, double var2, double var3, double var4) {
        return (var1 * var2 * var3) / ((var4 - var2) * (var4 - var3));
    }
}
