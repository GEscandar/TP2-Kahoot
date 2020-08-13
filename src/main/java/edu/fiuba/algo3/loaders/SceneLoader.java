package edu.fiuba.algo3.loaders;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.constants.Views;
import edu.fiuba.algo3.exceptions.FatalError;
import edu.fiuba.algo3.exceptions.ViewLoadingException;
import edu.fiuba.algo3.resources.ViewFinder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SceneLoader {
	
	private SceneLoader() {}
	
	private static final Logger logger = LoggerFactory.getLogger(SceneLoader.class);

	private static FXMLLoader loader;

	private static Object loadScene(String viewName) throws ViewLoadingException {
		loader = new FXMLLoader(ViewFinder.findView(viewName));		
		try {
			return loader.load();
		} catch (IOException e) {
			logger.error("View not found", e);
			throw new ViewLoadingException("View: " + viewName + " couldn't be loaded by an IOException: " + e.getMessage());
		}
	}
	
	public static void loadScene(Stage stage, String viewName) throws ViewLoadingException {		
		Parent root = (Parent) loadScene(viewName);
		stage.setScene(new Scene(root));
		stage.setMinWidth(root.minWidth(-1));
		stage.setMinHeight(root.minHeight(-1));
	}
	
	public static void loadInsidePane(Pane parent, String viewName) throws ViewLoadingException {	
		Pane pane = (Pane) loadScene(viewName);		
		parent.getChildren().setAll(pane);
	}

	public static void loadModalAndShow(Parent rootPane, String viewName) throws ViewLoadingException {
		Stage dialog = new Stage();
		loadScene(dialog, viewName);

		dialog.initOwner(rootPane.getScene().getWindow());
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.showAndWait();
	}
	
	public static void loadErrorPage() {
		try {
			loadScene(App.getMainStage(), Views.ERROR_VIEW);
		} catch (ViewLoadingException e) {
			logger.error("View not loaded", e);
			throw new FatalError();
		}
	}

	public static <T> T getCurrentSceneController(){
		return loader.getController();
	}

	public static Scene getCurrentLoadedScene(){
		return ((Parent)loader.getRoot()).getScene();
	}
}
