package typingtester.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import typingtester.SceneController;
import typingtester.model.TypingTestStats;

public class ResultsController {
    private SceneController sceneController;
    private TypingTestStats stats;

    @FXML
    Label wpmAndAccuracy, otherStats;

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    public void setTypingTestStats(TypingTestStats stats) {
        this.stats = stats;
        displayResults();
    }

    public void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB)
            newTypingTest();
    }

    @FXML
    private void newTypingTest() {
        try {
            sceneController.setTypingTest();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayResults() {
        wpmAndAccuracy.setText(String.format(
            "%.2f WPM\n%.2f%% accuracy",
            stats.getWPM(),
            stats.getAccuracy())
        );

        otherStats.setText(String.format(
            "%.2f raw WPM\n"
           +"%d correct word"+maybePlural(stats.getCorrectWords())+"\n"
           +"%d incorrect word"+maybePlural(stats.getIncorrectWords()),
            stats.getRawWPM(),
            stats.getCorrectWords(),
            stats.getIncorrectWords()
            )
        );
    }

    private String maybePlural(int n) {
        return (n == 1 ? "" : "s");
    }

}
