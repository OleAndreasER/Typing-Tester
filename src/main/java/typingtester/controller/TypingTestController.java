package typingtester.controller;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import typingtester.filehandling.MinimalFileHandler;
import typingtester.model.TypingTest;
import typingtester.model.TypingTestStats;
import typingtester.timing.TestTimer;
import typingtester.timing.TestTimerListener;

public class TypingTestController extends PageController implements TestTimerListener {
    private TypingTest typingTest;
    private TestTimer testTimer;
    private static final int testTime = 60;

    @FXML
    private Label whiteWords, blackWords, timeLeft;

    public void handleKeyPress(KeyEvent event) {

        if (event.getCode() == KeyCode.BACK_SPACE) {
            typingTest.eraseLetter();
        }
        else if (event.getCode() == KeyCode.TAB) {
            resetTest();
            return;
        }
        else if (event.getCode() == KeyCode.ENTER) return; // "\n" is one char, but it should not be allowed
        
        else if (event.getText().length() == 1)
            typingTest.type(event.getText());

        else return;

        if (!testTimerIsOn()) startNewTestTimer();

        displayTest();
    }

    private void displayTest() {
        whiteWords.setText(typingTest.getIncorrectWordsDisplay()
                         + typingTest.getWordsDisplay());
        blackWords.setText(typingTest.getCorrectWordsDisplay());
    }

    private void startNewTestTimer() {
        testTimer = new TestTimer(typingTest.getSeconds(), this);
        testTimer.start();
    }

    private void endTimer() {
        testTimer.stop();
        testTimer = null;
        timeLeft.setText("");
    }

    //Creates new test, but does not start it.
    private void newTest() {
        typingTest = new TypingTest(testTime);
        displayTest();
    }

    private void resetTest() {
        newTest();
        if (testTimerIsOn()) endTimer();
    }

    @FXML
    private void enterProgress() {
        resetTest();
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
        timeLeft.setText(String.valueOf(testTime - elapsedSeconds));
    }

    @Override
    public void onCompletion() {
        TypingTestStats stats = typingTest.getStats();
        
        MinimalFileHandler fileHandler = new MinimalFileHandler();
        fileHandler.saveTest(stats);

        resetTest();

        try {
            sceneController.setResults(stats);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    //

    private boolean testTimerIsOn() {
        return testTimer != null;
    }
}
