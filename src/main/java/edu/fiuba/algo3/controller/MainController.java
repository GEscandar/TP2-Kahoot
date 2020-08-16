package edu.fiuba.algo3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.constants.Views;
import edu.fiuba.algo3.exceptions.ViewLoadingException;
import edu.fiuba.algo3.loaders.SceneLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@FXML
	public VBox mainWindow;
	
	@FXML
	public Button StartButton;
	
	@FXML
	public Button AboutButton;

	public void exitEvent(ActionEvent event) {
		Platform.exit();
	}
	
	public void aboutEvent(ActionEvent event) {
		try {
			SceneLoader.loadModalAndShow(mainWindow, Views.ABOUT_VIEW);
		} catch (ViewLoadingException e) {
			logger.error("View not loaded", e);
			SceneLoader.loadErrorPage();
		}
	}

	public void startEvent(ActionEvent event)  {
		try {

			SceneLoader.loadScene(App.getMainStage(),Views.PLAYER_NAME_VIEW);
		} catch (ViewLoadingException e) {
			logger.error("View not loaded", e);
			SceneLoader.loadErrorPage();
		}
	}
	
}
