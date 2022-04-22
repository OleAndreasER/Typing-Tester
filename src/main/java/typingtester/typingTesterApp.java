package typingtester;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class TypingTesterApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("");

        SceneController sceneController = new SceneController(stage);
        sceneController.setTypingTest();
    }

}
