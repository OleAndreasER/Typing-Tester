package typingtester;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TypingTestController {

    private boolean testTimerIsOn = false;
    private TypingTest typingTest;

    private SceneController sceneController;

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    @FXML
    private Label result, test, timeleft, written;

    public void handleKeyPress(KeyEvent event) {
        
        if (!testTimerIsOn) {
            newTestTimer();
            testTimerIsOn = true;
        }

        if (event.getCode() == KeyCode.BACK_SPACE) {
            typingTest.eraseLetter();
        }
        else if (event.getCode() == KeyCode.TAB)
            newTest();
        else if (event.getCode() == KeyCode.ENTER);
        else
            typingTest.type(event.getText());

        written.setText(typingTest.getTypedAsDisplayed());
        test.setText(typingTest.getWordsAsDisplayed());
    }

    private void newTestTimer() {
        EventScheduler finishTestTimer = new EventScheduler(60) {            
            @Override
            public void event() {
                finishTest();
            }

            public void onSecond(int elapsedSeconds) {
                timeleft.setText(String.valueOf(60 - elapsedSeconds));
            }
        };

        finishTestTimer.start();
    }

    private void finishTest() {
        result.setText(String.valueOf(typingTest.getWPM()));
        newTest();
        testTimerIsOn = false;
    }
    
    private void newTest() {
        typingTest = new TypingTest(60);
        test.setText(typingTest.getWordsAsDisplayed());
        written.setText("");
    }

    @FXML
    private void enterStats() {
        sceneController.setStats();
    }

    @FXML
    public void initialize() {
        newTest();
    }
}
