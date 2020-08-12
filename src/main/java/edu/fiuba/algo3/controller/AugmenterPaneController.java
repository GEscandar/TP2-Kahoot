package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.engine.score.augmenters.ThreeMultiplier;
import edu.fiuba.algo3.engine.score.augmenters.TwoMultiplier;
import edu.fiuba.algo3.model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;


public class AugmenterPaneController {
    @FXML
    public RadioButton augmenterx2;

    @FXML
    public RadioButton augmenterx3;

    private GameController controller;
    private TwoMultiplier x2Multiplier = new TwoMultiplier();
    private ThreeMultiplier x3Multiplier = new ThreeMultiplier();

    public void initialize(GameController gameController, Game game){
        controller = gameController;
        if (game.isAugmenterAvailable(new TwoMultiplier())){
            augmenterx2.setVisible(true);
        }
        if (game.isAugmenterAvailable(new ThreeMultiplier())){
            augmenterx3.setVisible(true);
        }
    }

    public void addAugmenter(MouseEvent event){
        controller.addAugmenter(event);

    }
}
