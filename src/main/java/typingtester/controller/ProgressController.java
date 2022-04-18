package typingtester.controller;


import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import typingtester.SceneController;
import typingtester.filehandling.FileHandling;
import typingtester.filehandling.MinimalStatFormat;
import typingtester.model.Progress;

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
        List<MinimalStatFormat> tests = FileHandling.loadTests();
        Progress progress = new Progress(tests);
        String record = String.valueOf(progress.getWpmRecord());
        
        wpmRecord.setText("PB: "+record);
    }

}
