package typingtester;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TypingTesterApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Type tester");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
        Parent root = loader.load();
        TypingTesterController controller = loader.getController();
        Scene scene = new Scene(root);

        scene.setOnKeyTyped(event -> {
            controller.handleKeyPress(event.getCharacter());
        });

        stage.setScene(scene);
        stage.show();
    }

}
