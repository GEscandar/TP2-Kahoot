package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.model.GameOption;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;


public class GroupOptionCell extends ListCell<GameOption> {

    HBox hBox;
    Pane pane;
    Label label;
    ComboBox<String> comboBox;
    GroupChoiceQuestionController controller;
    String oldValue;

    public GroupOptionCell(GroupChoiceQuestionController orderedController) {
        super();
        hBox = new HBox();
        pane = new Pane();
        label = new Label();
        comboBox = new ComboBox<>();
        controller = orderedController;

        comboBox.getItems().addAll(controller.getOptionsGroups());
        comboBox.setPromptText("grupo");
        comboBox.setOnAction((event) -> actionCombo());
        oldValue = null;

        hBox.getChildren().addAll(label, pane, comboBox);
        label.setMaxWidth(Double.MAX_VALUE);
        hBox.setHgrow(pane, Priority.ALWAYS);
        hBox.setSpacing(50);
        hBox.setAlignment(Pos.CENTER);
    }

    @FXML
    public void actionCombo (){
        String value = comboBox.getValue();
        controller.processAnswer(value, oldValue, this.getItem());
        controller.getSubmitButton().setVisible(true);
        oldValue = value;
    }

    //@Override
    protected void updateItem(GameOption item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);
        if (!(empty || item == null)){
            label.setText(item.getText());
            setGraphic(hBox);
        }
    }
}

