package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.model.GameOption;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.*;


public class OrderedChoiceQuestionController extends GenericQuestionController{

	@FXML
    public VBox vBox;
	
	public int optionsRequired = 0;
	
	@Override
	public void addAnswer(ActionEvent event) {
		CheckBox source = (CheckBox) event.getSource();		
		GameOption option = new GameOption(source.getText());

		selectedAnswers.add(option);
		updateIndex();	
		source.setOnAction((e) -> undoAnswer(event));
		checkNextButton();
	}

	@Override
	public void undoAnswer(ActionEvent event) {
		CheckBox source = (CheckBox) event.getSource();		
		
		GameOption option = new GameOption(source.getText());
		selectedAnswers.remove(option);
		updateIndex();
		source.setOnAction((e) -> addAnswer(event));
		checkNextButton();
	}
	
	private void updateIndex() {
		List<HBox> paneList = (List) vBox.getChildren();
		for(HBox pane : paneList) {
			if(pane.isVisible()) {
				List<AnchorPane> anchorPaneList = (List) pane.getChildren();
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
	
	private void checkNextButton() {
		boolean disableButton = selectedAnswers.size() != optionsRequired;
		gameController.submitButton.setDisable(disableButton);
	}

    public void setUpView(){
        List<HBox> paneList = (List) vBox.getChildren();

        int i = 0;
        for (GameOption option : (gameController.getCurrentQuestion().getOptions())) {
        	HBox pane = paneList.get(i);
        	List<AnchorPane> anchorPane  = (List) pane.getChildren();
        	CheckBox button =  (CheckBox) ((List) anchorPane.get(1).getChildren()).get(0);
            button.setText(option.getText());
            button.setOnAction(this::addAnswer);
            pane.setVisible(true);
            i++;
        }
        optionsRequired = i;
    }

}
