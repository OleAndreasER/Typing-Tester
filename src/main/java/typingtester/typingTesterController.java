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
    private Label result, test;

    public void handleKeyPress(String c) {
        test.setText(new TypingTest().getWords());
        
        startTestTimer();
    }

    private void startTestTimer() {
        EventScheduler finishTestTimer = new EventScheduler(4) {            
            @Override
            public void event() {
                finishTest();
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
