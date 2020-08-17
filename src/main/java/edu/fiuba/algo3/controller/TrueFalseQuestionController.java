package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.model.GameOption;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;

import java.util.List;


public class TrueFalseQuestionController extends GenericQuestionController {

    @FXML
    public RadioButton radioButton1;
    @FXML
    public RadioButton radioButton2;


    public void setUpView() {

        radioButton1.setText(gameController.getCurrentQuestion().getOptions().get(0).getText());
        radioButton2.setText(gameController.getCurrentQuestion().getOptions().get(1).getText());
    }

    public void choiceOption(ActionEvent event){
        selectedAnswers.clear();
        RadioButton source = (RadioButton) event.getSource();
        GameOption option = new GameOption(source.getText());

        selectedAnswers.add(option);
        gameController.submitButton.setDisable(false);
    }
}