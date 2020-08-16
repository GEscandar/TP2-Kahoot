package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.model.GameOption;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class OptionCell extends ListCell<GameOption> {
    HBox hBox = new HBox();
    Pane pane = new Pane();
    Label label = new Label();
    ComboBox comboBox = new ComboBox();

    public OptionCell(ObservableList <String> positionsList){
        super();

        comboBox.getItems().addAll(positionsList);
        comboBox.setPromptText("posición");
        comboBox.setOnAction((event) -> actionCombo());
        // add a listener
        comboBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value)
            {

                // set the text for the label to the selected item
                l1.setText(st[new_value.intValue()] + " selected");
            }
        });
        hBox.getChildren().addAll( label, pane, comboBox);
        label.setMaxWidth(Double.MAX_VALUE);
        hBox.setHgrow(pane, Priority.ALWAYS);
        hBox.setSpacing(50);
        hBox.setAlignment(Pos.CENTER);
    }

    @FXML
    public void actionCombo (){

    }

    //@Override
    protected void updateItem(GameOption item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);
        if (empty || item == null){ //|| item.getText() == null) {
            //setText(null);
            //setGraphic(null);
        } else {
            label.setText(item.getText());
            setGraphic(hBox);
        }
    }
}

