package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.engine.questions.GroupChoiceQuestion;
import edu.fiuba.algo3.model.GameOption;
import edu.fiuba.algo3.model.OptionGroup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupChoiceQuestionController extends GenericQuestionController{

    @FXML
    public ListView<GameOption> listView;

    public ObservableList<GameOption> optionsList = FXCollections.observableArrayList();
    public ObservableList<String> groupsOptionsList = FXCollections.observableArrayList();
    public GroupChoiceQuestion question;
    List<OptionGroup> copyGroupList;

    public void setUpView(){
        question = (GroupChoiceQuestion) gameController.getCurrentQuestion();
        copyGroupList = question.getOptionGroupList();
        optionsList.addAll(gameController.getCurrentQuestion().getOptions());

        for(OptionGroup option : question.getOptionGroupList()){
            groupsOptionsList.add(option.getText());
        }

        listView.setEditable(true);
        listView.setItems(optionsList);
        listView.setCellFactory(param -> new GroupOptionCell(this));
    }

    public void processAnswer(String value, String oldValue, GameOption option){
        for(OptionGroup optionGroup : copyGroupList) {
            if (optionGroup.getText().equals(oldValue)) {
                optionGroup.removeOption(option);
            }
        }

        for(OptionGroup optionGroup : copyGroupList) {
            if(optionGroup.getText().equals(value)){
                if(optionGroup.getOptions() == null){
                    optionGroup.setOptions(new ArrayList<GameOption>());
                }
                optionGroup.addOption(option);
            }
        }

        selectedAnswers.clear();
        selectedAnswers.addAll(copyGroupList);
    }

    public ObservableList<String> getOptionsGroups(){
        return groupsOptionsList;
    }

    public Button getSubmitButton(){
        return this.gameController.getSubmitButton();
    }
}

