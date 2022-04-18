package typingtester.controller;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import typingtester.SceneController;
import typingtester.filehandling.FileHandling;
import typingtester.filehandling.MinimalStatFormat;
import typingtester.model.TestTimer;
import typingtester.model.TypingTest;
import typingtester.model.TypingTestStats;

public class TypingTestController implements TestTimerListener {
    private TypingTest typingTest;
    private SceneController sceneController;
    private TestTimer testTimer;

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    @FXML
    private Label whiteWords, blackWords, timeleft;

    public void handleKeyPress(KeyEvent event) {
        
        if (!testTimerIsOn()) {
            newTestTimer();
        }

        if (event.getCode() == KeyCode.BACK_SPACE) {
            typingTest.eraseLetter();
        }
        else if (event.getCode() == KeyCode.TAB)
            resetTest();
        else if (event.getCode() == KeyCode.ENTER);
        else if (event.getCode() == KeyCode.ESCAPE);
        else
            typingTest.type(event.getText());

        displayTest();
    }

    private void displayTest() {
        whiteWords.setText(typingTest.getIncorrectWordsDisplay()
                         + typingTest.getWordsDisplay());
        blackWords.setText(typingTest.getCorrectWordsDisplay());
    }

    private void newTestTimer() {
        testTimer = new TestTimer(typingTest.getSeconds(), this);
        testTimer.start();
    }

    private void endTimer() {
        testTimer.stop();
        testTimer = null;
        timeleft.setText("");
    }

    //Creates new test, but does not start it.
    private void newTest() {
        typingTest = new TypingTest(60);
        displayTest();
    }

    private void resetTest() {
        newTest();
        endTimer();
    }

    @FXML
    private void enterProgress() {
        try {
            sceneController.setProgress();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        newTest();
    }

    //Timer listening
    @Override
    public void onSecond(int elapsedSeconds) {
        timeleft.setText(String.valueOf(60 - elapsedSeconds));
    }

    @Override
    public void onCompletion() {
        TypingTestStats stats = typingTest.getStats();
        
        FileHandling.saveTest(new MinimalStatFormat(stats));

        resetTest();

        try {
            sceneController.setResults(stats);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //

    private boolean testTimerIsOn() {
        return testTimer != null;
    }

}
