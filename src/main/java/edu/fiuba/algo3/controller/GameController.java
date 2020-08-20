package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.constants.AugmenterType;
import edu.fiuba.algo3.constants.QuestionTypeLiteral;
import edu.fiuba.algo3.constants.Views;
import edu.fiuba.algo3.exceptions.ViewLoadingException;
import edu.fiuba.algo3.loaders.SceneLoader;
import edu.fiuba.algo3.model.Game;
import edu.fiuba.algo3.model.Player;
import edu.fiuba.algo3.model.Question;
import edu.fiuba.algo3.resources.QuestionViewRouter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static edu.fiuba.algo3.constants.Views.*;

import java.util.Timer;
import java.util.TimerTask;

public class GameController {

	@FXML
	public Label questionText;
	@FXML
	public Label questionType;
	@FXML
	public Label timeCounter;
	@FXML
	public Pane questionPane;
	@FXML
	public Pane playerPane;
	@FXML
	public Pane scoreAugmenterPane;
	@FXML
	public Button submitButton;

	private Stage stage;
	private Game game;
	private String augmenterString;
	private GenericQuestionController currentQuestionController;
	private ScoreAugmenterPaneController scoreAugmenterController;
	private int TIME_INTERVAL = 60;
	private int interval = TIME_INTERVAL;
	private Timer timer;
	

	private static final Logger logger = LoggerFactory.getLogger(GameController.class);

	public void addAugmenter(MouseEvent event) {
		RadioButton source = (RadioButton) event.getSource();
		augmenterString = source.getId();
	}

	public void play(Game game, Stage stage) {
		this.game = game;
		this.stage = stage;

		game.start();
		updatePanes();
		startTimeThread();
	}

	private void setPlayerPane() {
		PlayerPaneController currentPlayerController;
		try {
			SceneLoader.loadInsidePane(playerPane, PLAYER_PANE);
			currentPlayerController = SceneLoader.getCurrentSceneController();
			currentPlayerController.initialize(this);
		} catch (ViewLoadingException e) {
			logger.error("View not loaded", e);
			SceneLoader.loadErrorPage();
		}
	}

	private void setQuestionPane() {
		try {
			String viewName = QuestionViewRouter.getViewByQuestionType(getCurrentQuestion().getType());
			SceneLoader.loadInsidePane(questionPane, viewName);
			currentQuestionController = SceneLoader.getCurrentSceneController();
			currentQuestionController.initialize(this);
			questionText.setText(getCurrentQuestion().getText());
			questionType.setText(getQuestionTypeLiteralString());
			submitButton.setDisable(true);
		} catch (ViewLoadingException e) {
			logger.error("View not loaded", e);
			SceneLoader.loadErrorPage();
		}
	}

	private void setScoreAugmentersPane() {
		try {
			SceneLoader.loadInsidePane(scoreAugmenterPane, SCORE_AUGMENTER_PANE);
			scoreAugmenterController = SceneLoader.getCurrentSceneController();
			scoreAugmenterController.initialize(this, this.game);
		} catch (ViewLoadingException e) {
			logger.error("View not loaded", e);
			SceneLoader.loadErrorPage();
		}
	}

	private void updatePanes() {
		setPlayerPane();
		setQuestionPane();
		setScoreAugmentersPane();
	}

	public Player getCurrentPlayer() {
		return this.game.getCurrentPlayer();
	}

	public Question getCurrentQuestion() {
		return this.game.getCurrentQuestion();
	}

	public void doNext() {
		resetTimer();
		AugmenterType augmenterType = AugmenterType.getEnumByName(augmenterString);
		game.nextTurn(currentQuestionController.getSelectedAnswers(), augmenterType.getScoreAugmenter());
		updatePanes();
		submitButton.setDisable(true);
		augmenterString = null;
		if (game.isOver()) {
			endGame();
		}else {
			startTimeThread();
		}
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	private void endGame() {
		try {
			SceneLoader.loadScene(stage, Views.RESULTS_VIEW);
		} catch (ViewLoadingException e) {
			logger.error("View not loaded", e);
			SceneLoader.loadErrorPage();
		}

		ResultsViewController controller = SceneLoader.getCurrentSceneController();
		controller.initialize(game);
	}
	
	private void resetTimer() {
		timer.cancel();
		timeCounter.setText(String.valueOf(TIME_INTERVAL));
	}
	
	public Player getCurrentOpponent() {
		for(Player player : game.getPlayers()) {
			if(!player.equals(game.getCurrentPlayer())) {
				return player;
			}
		}
		return game.getCurrentPlayer();
	}
	
	private void startTimeThread() {
		interval = TIME_INTERVAL;
		timer = new Timer();
		
	    timer.scheduleAtFixedRate(new TimerTask() {
	        public void run() {
                Platform.runLater(() -> {
                	timeCounter.setText(String.valueOf(interval));
                	if(interval == 0) {                		             
                		doNext();
                	}else {                		
                		interval--;
                	}
                });         	
	        }
	    }, 1000,1000);
	}
	
	private String getQuestionTypeLiteralString() {
		switch(getCurrentQuestion().getType()) {
			case MULTIPLE_CHOICE:  
				return QuestionTypeLiteral.MULTIPLE_CHOICE;
			case MULTIPLE_CHOICE_WITH_PENALTY:
				return QuestionTypeLiteral.MULTIPLE_CHOICE_PENALTY;
			case MULTIPLE_CHOICE_PARTIAL:
				return QuestionTypeLiteral.MULTIPLE_CHOICE_PARTIAL;
			case TRUE_FALSE:
				return QuestionTypeLiteral.TRUE_FALSE;
			case TRUE_FALSE_WITH_PENALTY:
				return QuestionTypeLiteral.TRUE_FALSE_PENALTY;
			case ORDERED_QUESTION:
				return QuestionTypeLiteral.ORDERED_CHOICE;
			case GROUP_CHOICE:
				return QuestionTypeLiteral.GROUP_CHOICE;
			default:
				return "";
		}
	}
}
