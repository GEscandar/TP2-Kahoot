package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.model.GameOption;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


import java.util.List;


public class MultipleChoiceQuestionController extends GenericQuestionController{

    @FXML
    public VBox vBox;

    public void setUpView(){
        List<CheckBox> buttonList = (List) vBox.getChildren();

        int i = 0;
        for (GameOption option : (gameController.getCurrentQuestion().getOptions())) {
            CheckBox button = buttonList.get(i);
            button.setText(option.getText());
            button.setOnAction(this::addAnswer);
            button.setVisible(true);
            i++;
        }
    }
}
