package edu.fiuba.algo3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.fiuba.algo3.constants.Views;
import edu.fiuba.algo3.exceptions.ViewLoadingException;
import edu.fiuba.algo3.loaders.SceneLoader;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

	private static Stage stage;
	
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
    @Override
    public void start(Stage stage) {        
		try {			
			App.stage = stage;
			SceneLoader.loadScene(stage, Views.MAIN_VIEW);
	        stage.setTitle("TP2 Kahoot");

			stage.getIcons().add(new Image(App.class.getResourceAsStream("/images/icon.png")));

	        stage.show();
		} catch (ViewLoadingException e) {
			logger.error("View not found", e);
			SceneLoader.loadErrorPage();
			stage.show();
		}
    }

    public static void main(String[] args) {
        launch();
    }

	public static Stage getMainStage() {
		return stage;
	}
	
}