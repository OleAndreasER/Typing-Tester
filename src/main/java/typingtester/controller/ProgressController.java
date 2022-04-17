package typingtester.controller;


import java.io.IOException;

import javafx.fxml.FXML;
import typingtester.SceneController;

public class ProgressController {
    private SceneController sceneController;

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    @FXML
    private void enterTypingTest() throws IOException {
        sceneController.setTypingTest();
    }

}
