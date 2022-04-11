package typingtester;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TypingTestController {

    private boolean testTimerIsOn = false;
    private TypingTest typingTest;

    private SceneController sceneController;

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    @FXML
    private Label result, test, timeleft, written;

    public void handleKeyPress(String c) {
        
        if (!testTimerIsOn) {
            startTestTimer();
            testTimerIsOn = true;
        }

        typingTest.type(c);

        written.setText(typingTest.getTyped());
        test.setText(typingTest.getWords());
    }

    private void startTestTimer() {
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
        result.setText("FINISHED");
        testTimerIsOn = false;
    }
    
    private void startTest() {
        typingTest = new TypingTest(60);
        test.setText(typingTest.getWords());
    }

    @FXML
    private void enterStats() {
        sceneController.setStats();
    }

    @FXML
    public void initialize() {
        startTest();
    }
}
