package typingtester;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TypingTesterController {

    private SceneController sceneController;

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    @FXML
    private TextField firstNumber, secondNumber, operator;

    @FXML
    private Label result, test, timeleft;

    public void handleKeyPress(String c) {
        test.setText(new TypingTest().getWords());
        
        startTestTimer();
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
    }
    
    @FXML
    private void enterStats() {
        sceneController.setStats();
    }

    @FXML
    private void enterTypingTest() {
        sceneController.setTypingTest();
    }

}
