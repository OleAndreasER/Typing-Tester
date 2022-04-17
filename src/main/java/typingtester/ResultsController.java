package typingtester;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ResultsController {
    private SceneController sceneController;
    private TypingTestStats stats;

    @FXML
    Label results;

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    public void setTypingTestStats(TypingTestStats stats) {
        this.stats = stats;
        displayResults();
    }

    public void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB)
            try {
                sceneController.setTypingTest();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private void displayResults() {
        String accuracy = String.format("%.2f", stats.getAccuracy());
        results.setText(
            "WPM: "+String.valueOf(stats.getWPM())+"\n"+
            "Raw: "+String.valueOf(stats.getRawWPM())+"\n"+
            "Accuracy: "+accuracy+"%\n"+
            "Correct/Incorrect: "+String.valueOf(stats.getCorrectWords())+
            "/"+String.valueOf(stats.getIncorrectWords())
        );
    }

}
