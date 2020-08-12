package edu.fiuba.algo3.controller;


import edu.fiuba.algo3.constants.Views;
import edu.fiuba.algo3.engine.score.augmenters.NoMultiplier;
import edu.fiuba.algo3.exceptions.ViewLoadingException;
import edu.fiuba.algo3.loaders.SceneLoader;
import edu.fiuba.algo3.model.Game;
import edu.fiuba.algo3.model.Player;
import edu.fiuba.algo3.model.Question;
import edu.fiuba.algo3.resources.QuestionViewRouter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static edu.fiuba.algo3.constants.Views.*;

public class GameController {

    @FXML
    public Label questionText;
    @FXML
    public Pane questionPane;
    @FXML
    public Pane playerPane;
    @FXML
    public Pane augmenterPane;
    @FXML
    public Pane exclusivityPane;
    @FXML
    public Button submitButton;

    private Stage stage;
    private Game game;
    private String augmenterString;
    private GenericQuestionController currentQuestionController;
    private AugmenterPaneController currentAugController;
    private ExclusivityPaneController currentExclController;

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    public void addAugmenter(MouseEvent event){
        Label source = (Label) event.getSource();
        augmenterString = source.getText();
    }
    
    public void play(Game game, Stage stage){
        this.game = game;
        this.stage = stage;

        game.start();
        updatePanes();
    }

    private void setPlayerPane(){
        PlayerPaneController currentPlayerController;
        try{
            SceneLoader.loadInsidePane(playerPane, PLAYER_PANE);
            currentPlayerController = SceneLoader.getCurrentSceneController();
            currentPlayerController.initialize(this);
        } catch (ViewLoadingException e) {
            logger.error("View not loaded", e);
            SceneLoader.loadErrorPage();
        }
    }

    private void setQuestionPane(){
        try{
        	String viewName = QuestionViewRouter.getViewByQuestionType(getCurrentQuestion().getType());
        	SceneLoader.loadInsidePane(questionPane, viewName);
        	currentQuestionController = SceneLoader.getCurrentSceneController();
        	currentQuestionController.initialize(this);
        	questionText.setText(getCurrentQuestion().getText());
        } catch (ViewLoadingException e) {
        	logger.error("View not loaded", e);
            SceneLoader.loadErrorPage();
        }        
    }

    private void setAugmentersPane(){
        try{
            SceneLoader.loadInsidePane(augmenterPane, AUGMENTER_PANE);
            currentAugController = SceneLoader.getCurrentSceneController();
            currentAugController.initialize(this);
        } catch (ViewLoadingException e) {
            logger.error("View not loaded", e);
            SceneLoader.loadErrorPage();
        }
    }

    private void setExclusivityPane(){
        try{
            SceneLoader.loadInsidePane(exclusivityPane, EXCLUSIVITY_PANE);
            currentExclController = SceneLoader.getCurrentSceneController();
            currentExclController.initialize(this);
        } catch (ViewLoadingException e) {
            logger.error("View not loaded", e);
            SceneLoader.loadErrorPage();
        }
    }

    private void updatePanes(){
        setPlayerPane();
        setQuestionPane();
        setAugmentersPane();
        setExclusivityPane();
    }

    public Player getCurrentPlayer(){
        return this.game.getCurrentPlayer();
    }

    public Question getCurrentQuestion(){
        return this.game.getCurrentQuestion();
    }

    public void doNext(){
    	 game.nextTurn(currentQuestionController.getSelectedAnswers(), new NoMultiplier());
         updatePanes();
         if(game.isOver()) endGame();
    }
    
    private void endGame(){
        try{
            SceneLoader.loadScene(stage, Views.RESULTS_VIEW);
        } catch (ViewLoadingException e) {
        	logger.error("View not loaded", e);
        	SceneLoader.loadErrorPage();
        }

        ResultsViewController controller = SceneLoader.getCurrentSceneController();
        controller.initialize(game);
    }

}
