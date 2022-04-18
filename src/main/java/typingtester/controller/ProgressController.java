package typingtester.controller;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import typingtester.SceneController;

public class ProgressController {
    private SceneController sceneController;

    @FXML
    Label wpmRecord;

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    @FXML
    private void enterTypingTest() throws IOException {
        sceneController.setTypingTest();
    }

    @FXML
    public void initialize() {
        wpmRecord.setText("hello");
    }

}
