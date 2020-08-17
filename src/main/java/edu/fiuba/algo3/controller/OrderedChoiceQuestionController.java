package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.model.GameOption;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.*;


public class OrderedChoiceQuestionController extends GenericQuestionController{

	@FXML
    public VBox vBox;
	
	@Override
	public void addAnswer(ActionEvent event) {
		CheckBox source = (CheckBox) event.getSource();		
		GameOption option = new GameOption(source.getText());

		selectedAnswers.add(option);
		updateIndex();	
		source.setOnAction((e) -> undoAnswer(event));
		gameController.submitButton.setVisible(true);
	}

	@Override
	public void undoAnswer(ActionEvent event) {
		CheckBox source = (CheckBox) event.getSource();		
		
		GameOption option = new GameOption(source.getText());
		selectedAnswers.remove(option);
		updateIndex();
		source.setOnAction((e) -> addAnswer(event));
		if(selectedAnswers.isEmpty()) {
			gameController.submitButton.setVisible(false);
		}
	}
	
	private void updateIndex() {
		List<SplitPane> paneList = (List) vBox.getChildren();
		for(SplitPane pane : paneList) {
			if(pane.isVisible()) {
				List<AnchorPane> anchorPaneList = (List) pane.getItems();
				Label label = (Label) anchorPaneList.get(0).getChildren().get(0);
				CheckBox button = (CheckBox) anchorPaneList.get(1).getChildren().get(0);
				GameOption option = new GameOption(button.getText());
				if(selectedAnswers.contains(option)) {
					int index = selectedAnswers.indexOf(option);
					label.setText(String.valueOf(index + 1) + "°");
				}else {
					label.setText("");
				}
			}
		}
	}

    public void setUpView(){
        List<SplitPane> paneList = (List) vBox.getChildren();

        int i = 0;
        for (GameOption option : (gameController.getCurrentQuestion().getOptions())) {
        	SplitPane pane = paneList.get(i);
        	List<AnchorPane> anchorPane  = (List) pane.getItems();
        	CheckBox button =  (CheckBox) ((List) anchorPane.get(1).getChildren()).get(0);
            button.setText(option.getText());
            button.setOnAction(this::addAnswer);
            pane.setVisible(true);
            i++;
        }
    }

}
