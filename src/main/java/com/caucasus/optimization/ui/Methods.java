package com.caucasus.optimization.ui;

public enum Methods {
    DICHOTOMY("Dichotomy method", false),
    GOLDEN_SECTION("Golden section method", false),
    FIBONACCI("Fibonacci method", false),
    PARABOLOID("Paraboloid method", true),
    BRENT("Combined Brent method", false);

    private String labelString;
    boolean needPlot;

    Methods(String labelString, boolean needPlot) {
        this.labelString = labelString;
        this.needPlot = needPlot;
    }

    public String getLabelString() {
        return labelString;
    }
}
