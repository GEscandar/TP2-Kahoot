package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.model.GameOption;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.*;


public class OrderedChoiceQuestionController extends GenericQuestionController{

    @FXML
    public ListView<GameOption> listView;

    public ObservableList<GameOption>optionsList;
    public ObservableList<String>positionsList;
    public Map<String, List<GameOption>> hashAnswers;

    public void setUpView(){
        optionsList = FXCollections.observableArrayList();
        positionsList = FXCollections.observableArrayList();
        optionsList.addAll(gameController.getCurrentQuestion().getOptions());
        hashAnswers = new HashMap<>();

        for (int i = 1; i <= optionsList.size(); i++){
            positionsList.add(String.valueOf(i));
        }

        listView.setEditable(true);
        listView.setItems(optionsList);
        listView.setCellFactory(param -> new OrderedOptionCell(this));
    }

    public List<String> getPositions(){
        return positionsList;
    }

    public void processAnswer(String newValue, String oldValue, GameOption option){
        if(oldValue != null){
            List<GameOption> list = (hashAnswers.get(oldValue));
            list.remove(option);
        }

        if (hashAnswers.containsKey(newValue)){
            List<GameOption> list = (hashAnswers.get(newValue));
            list.add(option);
        }else{
            List<GameOption> list = new ArrayList<>();
            list.add(option);
            hashAnswers.put(newValue, list);
        }

        ArrayList<String> sortedKeys = new ArrayList<>(hashAnswers.keySet());

        selectedAnswers.clear();
        Collections.sort(sortedKeys);
        for (String x : sortedKeys){
            selectedAnswers.add(hashAnswers.get(x).get(0));
        }
    }

    public Button getSubmitButton(){
        return this.gameController.getSubmitButton();
    }
}
