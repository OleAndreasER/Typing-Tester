package typingtester;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class typingTesterController {
    @FXML
    private TextField firstNumber, secondNumber, operator;

    @FXML
    private Label result, test;

    @FXML
    private void handleButtonClick() {
        try {
            int result = 5;
            this.test.setText("Hello World");

            this.result.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            result.setText("Et eller begge tallene er ugyldige");
        }
    }

}
