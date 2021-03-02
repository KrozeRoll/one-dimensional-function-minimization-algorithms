package com.caucasus.optimization.ui;

import com.caucasus.optimization.algos.Dichotomy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private Label methodName;

    @FXML
    private void clickCalculate(ActionEvent event) {
        methodName.setText("Calc");
    }

    @FXML
    private void clickDichotomy(ActionEvent event) {
        methodName.setText("Dichotomy method");
    }

    @FXML
    private void clickGoldenRatio(ActionEvent event) {
        methodName.setText("Golden ratio method");
    }

    @FXML
    private void clickFibonacci(ActionEvent event) {
        methodName.setText("Fibonacci method");
    }

    @FXML
    private void clickParabola(ActionEvent event) {
        methodName.setText("Parabola method");
    }

    @FXML
    private void clickBrent(ActionEvent event) {
        methodName.setText("Combined Brent method");
    }


}
