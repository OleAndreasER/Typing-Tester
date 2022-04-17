package typingtester;


import java.io.IOException;

import javafx.fxml.FXML;

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
