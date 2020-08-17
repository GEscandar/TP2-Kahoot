package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.engine.questions.GroupChoiceQuestion;
import edu.fiuba.algo3.model.GameOption;
import edu.fiuba.algo3.model.OptionGroup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.stream.Collectors;

public class GroupChoiceQuestionController extends GenericQuestionController{
	
	@FXML
    public VBox vBox;
	
	public void changeAnswer(Event event) {
		if(allOptionsHaveAGroup()) {
			updateGroups();
			gameController.submitButton.setDisable(false);
		}
	}

	private void updateGroups() {
		List<HBox> paneList = (List) vBox.getChildren();
		selectedAnswers.clear();
		
		for(HBox pane : paneList) {
			if(pane.isVisible()) {
				List<AnchorPane> anchorPane  = (List) pane.getChildren();
	        	Label label =  (Label) ((List) anchorPane.get(0).getChildren()).get(0);
	        	GameOption option = new GameOption(label.getText());
	        	ComboBox comboBox =  (ComboBox) ((List) anchorPane.get(1).getChildren()).get(0);
	        	updateOptionGroup(new OptionGroup((String) comboBox.getValue()), option);
			}
		}
	}
	
	private void updateOptionGroup(OptionGroup selectedGroup, GameOption selectedOption) {
		boolean found = false;
		for(GameOption option : selectedAnswers) {
			if(option.getText().equals(selectedGroup.getText())) {
				OptionGroup optionGroup = (OptionGroup) option;
				optionGroup.addOption(selectedOption);
				found = true;
				break;
			}
		}
		if(!found) {
			selectedGroup.addOption(selectedOption);
			selectedAnswers.add(selectedGroup);
		}
	}
	
	private boolean allOptionsHaveAGroup() {
		List<HBox> paneList = (List) vBox.getChildren();
		boolean enableButton = true;
		for(HBox pane : paneList) {
			if(pane.isVisible()) {
				List<AnchorPane> anchorPane  = (List) pane.getChildren();
	        	ComboBox comboBox =  (ComboBox) ((List) anchorPane.get(1).getChildren()).get(0);
	        	if(comboBox.getValue() == null) {
	        		return false;
	        	}
			}
		}	
		return true;
	}

	
	private String getLabelValue(ComboBox source) {
		AnchorPane pane = (AnchorPane) source.getParent();
		Label label = (Label) pane.getChildren().get(0);
		return label.getText();
	}
	

	@Override
	public void setUpView() {
		List<HBox> paneList = (List) vBox.getChildren();
		GroupChoiceQuestion groupQuestion = (GroupChoiceQuestion) gameController.getCurrentQuestion();
		
        int i = 0;
        for (GameOption option : groupQuestion.getOptions()) {
        	HBox pane = paneList.get(i);
        	List<AnchorPane> anchorPane  = (List) pane.getChildren();
        	Label label =  (Label) ((List) anchorPane.get(0).getChildren()).get(0);
        	ComboBox comboBox =  (ComboBox) ((List) anchorPane.get(1).getChildren()).get(0);
        	label.setText(option.getText());
            pane.setVisible(true);
            ObservableList<String> groupList = FXCollections.observableArrayList();
            groupList.addAll(groupQuestion.getOptionGroupList().stream().map(OptionGroup::getText).collect(Collectors.toList()));
            comboBox.setItems(groupList);
            comboBox.setOnAction(this::changeAnswer);
            
            i++;
        }	
	}

  
}

