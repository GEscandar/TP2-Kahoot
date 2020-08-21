package edu.fiuba.algo3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PlayerPaneController {
    @FXML
    public Label playerName;
    @FXML
    public Label playerScore;
    @FXML
    public Label player2Name;
    @FXML
    public Label player2Score;


    public void initialize(GameController gamecontroller){
        playerName.setText(gamecontroller.getCurrentPlayer().getName());
        playerScore.setText(String.valueOf(gamecontroller.getCurrentPlayer().getScore().getValue()));

        player2Name.setText(gamecontroller.getCurrentOpponent().getName());
        player2Score.setText(String.valueOf(gamecontroller.getCurrentOpponent().getScore().getValue()));
    }

}
