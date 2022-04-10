package typingtester;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {

    private Scene typingTest;
    private Scene stats;
    private Stage stage;

    public SceneController(Stage stage) throws IOException {
        this.stage = stage;
        typingTest = getTypingTest();
        stats = getStats();
    }

    public void setTypingTest() {
        stage.setScene(typingTest);
        stage.show();
    }

    public void setStats() {
        stage.setScene(stats);
        stage.show();
    }

    private Scene getTypingTest() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TypingTest.fxml"));
        Parent root = loader.load();
        TypingTesterController controller = loader.getController();
        Scene scene = new Scene(root);

        controller.setSceneController(this);

        scene.setOnKeyTyped(event -> {
            controller.handleKeyPress(event.getCharacter());
        });

        return scene;
    }

    private Scene getStats() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Stats.fxml"));
        Parent root = loader.load();
        TypingTesterController controller = loader.getController();
        controller.setSceneController(this);
        return new Scene(root);
    }
}
