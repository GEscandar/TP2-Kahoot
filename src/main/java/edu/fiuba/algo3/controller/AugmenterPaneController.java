package edu.fiuba.algo3.controller;

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

    public void initialize(GameController gameController, Game game){
        controller = gameController;
        if (game.isAugmenterAvailable(augmenterx2.getId())){
            augmenterx2.setVisible(true);
        }
        if (game.isAugmenterAvailable(augmenterx3.getId())){
            augmenterx3.setVisible(true);
        }
    }

    public void addAugmenter(MouseEvent event){
        controller.addAugmenter(event);

    }
}
