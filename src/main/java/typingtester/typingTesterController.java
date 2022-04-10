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
