package com.caucasus.optimization.algos.entities.minfinder;

import com.caucasus.optimization.algos.entities.util.Interval;
import com.caucasus.optimization.algos.entities.util.Solution;

import java.util.ArrayList;
import java.util.function.Function;

public class Brent extends AbstractIntervalMinFinder {
    private static final double K = (3 - Math.sqrt(5)) / 2;

    public Brent(Function<Double, Double> function, double leftBorder, double rightBorder, double eps) {
        super(function, leftBorder, rightBorder, eps);
    }

    public Brent(Function<Double, Double> function, Interval domain, double eps) {
        super(function, domain, eps);
    }

    @Override
    public Solution calculateSolution() {
        double leftBorder, rightBorder, x, w, v, fx, fw, fv, d, e, g, u, fu;
        leftBorder = getLeftBorder();
        rightBorder = getRightBorder();
        ArrayList<Interval> intervals = new ArrayList<>();
        ArrayList<Double> approximatelyMinimums = new ArrayList<>();
        x = w = v = leftBorder + K * (rightBorder - leftBorder);
        intervals.add(new Interval(leftBorder, rightBorder));
        approximatelyMinimums.add(x);
        fx = fw = fv = getFunction().apply(x);
        d = e = rightBorder - leftBorder;
        double tol;
        while (compare(Math.abs(rightBorder - leftBorder), getEps()) > 0) {
            g = e;
            e = d;
            tol = e * Math.abs(x) + getEps() / 10;
            if (compare(Math.abs(x - (leftBorder + rightBorder) * 0.5) + (rightBorder - leftBorder) * 0.5, 2 * tol) <= 0) {
                break;
            }
            if (areDifferent(x, w, v) && areDifferent(fx, fw, fv)) {
                u = getParabolaMin(x, w, v, fx, fw, fv);
                if (compare(u, leftBorder) >= 0 && compare(u, rightBorder) <= 0 && compare(Math.abs(u - x), g * 0.5) < 0) {
                    if (compare((u - leftBorder), 2 * tol) < 0 || compare((rightBorder - u), 2 * tol) < 0) {
                        u = x - Math.signum(x - (leftBorder + rightBorder) * 0.5) * tol;
                    }
                }
            } else {
                if (compare(x, (leftBorder + rightBorder) * 0.5) < 0) {
                    u = x + K * (rightBorder - x);
                    e = rightBorder - x;
                } else {
                    u = x - K * (x - leftBorder);
                    e = x - leftBorder;
                }
            }
            if (compare(Math.abs(u - x), tol) < 0) {
                u = x + Math.signum(u - x) * tol;
            }
            d = Math.abs(u - x);
            fu = getFunction().apply(u);
            if (compare(fu, fx) <= 0) {
                if (compare(u, x) >= 0) {
                    leftBorder = x;
                } else {
                    rightBorder = x;
                }
                v = w;
                w = x;
                x = u;
                fv = fw;
                fw = fx;
                fx = fu;
            } else {
                if (compare(u, x) >= 0) {
                    rightBorder = u;
                } else {
                    leftBorder = u;
                }
                if (compare(fu, fw) <= 0 || compare(w, x) == 0) {
                    v = w;
                    w = u;
                    fv = fw;
                    fw = fu;
                } else if (compare(fu, fv) <= 0 || compare(v, x) == 0 || compare(v, w) == 0) {
                    v = u;
                    fv = fu;
                }
            }
            intervals.add(new Interval(leftBorder, rightBorder));
            approximatelyMinimums.add(x);
        }

        return new Solution(intervals, approximatelyMinimums);
    }

    private boolean notEqual(double lhs, double rhs) {
        return compare(lhs, rhs) != 0;
    }

    private boolean areDifferent(double a, double b, double c) {
        return notEqual(a, b) && notEqual(b, c) && notEqual(a, c);
    }


    private double getParabolaMin(double x1, double x2, double x3, double f1, double f2, double f3) {
        return x2 - 0.5 * (Math.pow(x2 - x1, 2) * (f2 - f3) - Math.pow(x2 - x3, 2) * (f2 - f1)) /
                ((x2 - x1) * (f2 - f3) - (x2 - x3) * (f2 - f1));
    }

}
