package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.model.GameOption;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.*;


public class OrderedChoiceQuestionController extends GenericQuestionController{

    @FXML
    public ListView<GameOption> listView;

    public void setUpView(){
        ObservableList<GameOption>optionsList = FXCollections.observableArrayList();
        ObservableList<String>positionsList = FXCollections.observableArrayList();

        optionsList.addAll(gameController.getCurrentQuestion().getOptions());
        for (int i = 1; i <= optionsList.size(); i++) {
            positionsList.add(String.valueOf(i));
        }

        listView.setEditable(true);
        listView.setItems(optionsList);
        listView.setCellFactory(param -> new OptionCell(positionsList));
    }


}
