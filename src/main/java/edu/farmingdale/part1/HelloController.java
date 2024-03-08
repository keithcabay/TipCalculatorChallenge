package edu.farmingdale.part1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class HelloController {
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();

    private static final NumberFormat percent = NumberFormat.getPercentInstance();

    private BigDecimal tipPercentage = new BigDecimal("0.15");

    @FXML
    private TextField amountTextField;

    @FXML
    private Label tipPercentageLabel;

    @FXML
    private Slider tipPercentageSlider;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;


    public void initialize(){
        currency.setRoundingMode(RoundingMode.HALF_UP);

        tipPercentageSlider.valueProperty().addListener(
                (observableValue, oldVal, newVal) -> {
                    tipPercentage = BigDecimal.valueOf(newVal.intValue() / 100.0);
                    tipPercentageLabel.setText(percent.format(tipPercentage));
                    updateFields();
                }
        );

        amountTextField.textProperty().addListener((observableValue, oldVal, newVal) -> updateFields());

    }

    private void updateFields(){
        try{
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);

            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        }catch(NumberFormatException e){
            tipTextField.clear();
            totalTextField.clear();
        }
    }
}