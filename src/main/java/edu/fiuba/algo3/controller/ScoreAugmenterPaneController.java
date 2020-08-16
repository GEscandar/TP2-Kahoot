package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.engine.score.augmenters.ExclusivityMultiplier;
import edu.fiuba.algo3.engine.score.augmenters.ThreeMultiplier;
import edu.fiuba.algo3.engine.score.augmenters.TwoMultiplier;
import edu.fiuba.algo3.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;


public class ScoreAugmenterPaneController {

    @FXML
    public RadioButton augmenterx2;
    @FXML
    public RadioButton augmenterx3;
    @FXML
    public RadioButton exclusivity;

    private GameController controller;

    public void initialize(GameController gameController, Game game){
        controller = gameController;
        if (game.isAugmenterAvailable(new TwoMultiplier())){
            augmenterx2.setDisable(false);
        }
        if (game.isAugmenterAvailable(new ThreeMultiplier())){
            augmenterx3.setDisable(false);
        }
        if (game.isAugmenterAvailable(new ExclusivityMultiplier())){
            exclusivity.setDisable(false);
        }
    }

    public void addAugmenter(MouseEvent event){
        controller.addAugmenter(event);

    }
}
