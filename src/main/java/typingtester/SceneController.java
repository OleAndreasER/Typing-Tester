package typingtester;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {

    private Scene progress;
    private Stage stage;

    public SceneController(Stage stage) throws IOException {
        this.stage = stage;
        progress = getProgress();
    }

    public void setTypingTest() throws IOException {
        stage.setScene(getTypingTest());
        stage.show();
    }

    public void setProgress() {
        stage.setScene(progress);
        stage.show();
    }

    private Scene getTypingTest() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TypingTest.fxml"));
        Parent root = loader.load();
        TypingTestController controller = loader.getController();
        Scene scene = new Scene(root);

        controller.setSceneController(this);

        scene.setOnKeyPressed(event -> {
            controller.handleKeyPress(event);
        });

        return scene;
    }

    private Scene getProgress() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Progress.fxml"));
        Parent root = loader.load();
        ProgressController controller = loader.getController();
        controller.setSceneController(this);
        return new Scene(root);
    }
}
