package com.caucasus.optimization.ui;

import com.caucasus.optimization.algos.entities.minfinder.*;
import com.caucasus.optimization.algos.entities.util.Interval;
import com.caucasus.optimization.algos.entities.util.ParaboloidSolution;
import com.caucasus.optimization.algos.entities.util.Solution;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.util.function.Function;

public class MainController {

    @FXML
    private Label methodName;
    @FXML
    private TextField epsTextField;
    @FXML
    private Slider iterationSlider;
    @FXML
    private Label iterationNumberLabel;
    @FXML
    private Label leftLabel;
    @FXML
    private Label approxLabel;
    @FXML
    private Label rightLabel;
    @FXML
    private LineChart<Double, Double> lineChart;

    final Function<Double, Double> function = x -> Math.exp(3.0D * x) + 5 * Math.exp(-2.0D * x);
    final Interval interval = new Interval(0, 1);
    final Double DEFAULT_EPS = 0.00001;
    final private XYChart.Series<Double, Double> functionSeries = plotLineSeries(function, interval, 100);

    private Solution dichotomySolution, goldenSectionSolution, fibonacciSolution, brentSolution;
    private ParaboloidSolution paraboloidSolution;

    private Methods currentMethod = Methods.DICHOTOMY;

    private int iterationNumber;


    public void initialize() {
        iterationSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            iterationNumber = newValue.intValue();
            updateWindow();
        });

        calculateSolutions(DEFAULT_EPS);
        setupMethod(currentMethod);
        lineChart.setCreateSymbols(false);
        lineChart.setLegendVisible(false);
        updateWindow();
    }

    private void updateWindow() {
        iterationNumberLabel.setText(Integer.toString(iterationNumber));
        double left = getCurrentSolution().getIntervals().get(iterationNumber).getLeftBorder();
        double right = getCurrentSolution().getIntervals().get(iterationNumber).getRightBorder();
        double approx = getCurrentSolution().getApproximatelyMinimums().get(iterationNumber);
        leftLabel.setText(String.format("%.5f", left));
        rightLabel.setText(String.format("%.5f", right));
        approxLabel.setText(String.format("%.5f", approx));

        clearChart();
        lineChart.getData().add(functionSeries);
        if (currentMethod.needPlot) {
            //TODO
        } else {
            drawBorderPoints(left, right, approx);
        }
    }

    private void drawBorderPoints(Double left, Double right, Double approx) {
        addPointToChart(left, function.apply(left));
        addPointToChart(right, function.apply(right));
        addPointToChart(approx, function.apply(approx));
    }

    private void addPointToChart(final double x, final double y) {
        XYChart.Series<Double, Double> series = new XYChart.Series<Double, Double>();
        plotPoint(x, y, series);
        lineChart.getData().add(series);
    }

    private XYChart.Series<Double, Double> plotLineSeries(
            final Function<Double, Double> function, final Interval interval, final int stepCount) {
        double step = (interval.getRightBorder() - interval.getLeftBorder()) / stepCount;

        final XYChart.Series<Double, Double> series = new XYChart.Series<Double, Double>();
        for (double x = interval.getLeftBorder(); x < interval.getRightBorder(); x += step) {
            plotPoint(x, function.apply(x), series);
        }
        plotPoint(interval.getRightBorder(), function.apply(interval.getRightBorder()), series);

        return series;
    }

    private void plotPoint(final double x, final double y,
                           final XYChart.Series<Double, Double> series) {
        series.getData().add(new XYChart.Data<Double, Double>(x, y));
    }

    public void clearChart() {
        lineChart.getData().clear();
    }

    private void calculateSolutions(Double eps) {
        dichotomySolution = new Dichotomy(function, interval, eps).getSolution();
        goldenSectionSolution = new GoldenSection(function, interval, eps).getSolution();
        fibonacciSolution = new Fibonacci(function, interval, eps).getSolution();
        brentSolution = new Brent(function, interval, eps).getSolution();
        paraboloidSolution = new Paraboloid(function, interval, eps).getParaboloidSolution();
    }

    @FXML
    private void clickCalculate(ActionEvent event) {
        double eps;
        try {
            eps = Double.parseDouble(epsTextField.getText());
        } catch (NumberFormatException e) {
            epsTextField.setText("Invalid argument");
            return;
        }
        calculateSolutions(eps);
        setupMethod(currentMethod);
        clearChart();
    }

    private Solution getCurrentSolution() {
        Solution solution;
        switch (currentMethod) {
            case DICHOTOMY: solution = dichotomySolution; break;
            case GOLDEN_SECTION: solution = goldenSectionSolution; break;
            case FIBONACCI: solution = fibonacciSolution; break;
            case PARABOLOID: solution = paraboloidSolution; break;
            case BRENT: solution = brentSolution; break;
            default:
                throw new IllegalStateException("Unexpected value: " + currentMethod);
        }
        return solution;
    }

    private void setupMethod(Methods choosedMethod) {
        currentMethod = choosedMethod;
        iterationSlider.setValue(0);
        iterationSlider.setMax(getCurrentSolution().getIntervals().size() - 1);
        iterationSlider.setMinorTickCount(4);
        iterationSlider.setMajorTickUnit(5);
        methodName.setText(currentMethod.getLabelString());
    }

    @FXML
    private void clickDichotomy(ActionEvent event) {
        setupMethod(Methods.DICHOTOMY);
    }

    @FXML
    private void clickGoldenSection(ActionEvent event) {
        setupMethod(Methods.GOLDEN_SECTION);
    }

    @FXML
    private void clickFibonacci(ActionEvent event) {
        setupMethod(Methods.FIBONACCI);
    }

    @FXML
    private void clickParaboloid(ActionEvent event) {
        setupMethod(Methods.PARABOLOID);
    }

    @FXML
    private void clickBrent(ActionEvent event) {
        setupMethod(Methods.BRENT);
    }


}
