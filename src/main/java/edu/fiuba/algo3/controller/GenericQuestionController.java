package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.model.GameOption;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;

import java.util.*;

public abstract class GenericQuestionController {

	protected GameController gameController;
	protected List<GameOption> selectedAnswers = new ArrayList<>();

	public abstract void setUpView();

	public void addAnswer(ActionEvent event) {
		CheckBox source = (CheckBox) event.getSource();
		GameOption option = new GameOption(source.getText());

		selectedAnswers.add(option);
		source.setOnAction((e) -> undoAnswer(event));
		gameController.submitButton.setVisible(true);
	}

	public void undoAnswer(ActionEvent event) {
		CheckBox source = (CheckBox) event.getSource();
		GameOption option = new GameOption(source.getText());
		selectedAnswers.remove(option);
		source.setOnAction((e) -> addAnswer(event));
		if(selectedAnswers.isEmpty()) {
			gameController.submitButton.setVisible(false);
		}
	}

	public List<GameOption> getSelectedAnswers() {
		return selectedAnswers;
	}

	public void initialize(GameController controller) {
		selectedAnswers = new ArrayList<>();
		this.gameController = controller;
		gameController.submitButton.setVisible(false);

		setUpView();
	}
}
