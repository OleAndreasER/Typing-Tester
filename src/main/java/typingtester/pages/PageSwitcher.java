package typingtester.pages;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import typingtester.controller.PageController;
import typingtester.controller.ResultsController;
import typingtester.model.TypingTestStats;

public class PageSwitcher {

    private Stage stage;

    public PageSwitcher(Stage stage) throws IOException {
        this.stage = stage;
    }

    public void setTypingTest() throws IOException {
        displayPage(createPage("TypingTest.fxml"));
    }

    public void setProgress() throws IOException {
        displayPage(createPage("Progress.fxml")); 
    }

    public void setResults(TypingTestStats stats) throws IOException {
        displayPage(createResultsPage(stats));
    }

    private void displayPage(Page page) {
        stage.setScene(page.scene());
        stage.show();
    }

    private Page createResultsPage(TypingTestStats stats) throws IOException {
        Page results = createPage("Results.fxml");
        ResultsController controller = (ResultsController) results.controller();
        controller.setStats(stats);
        controller.displayStats();
        return results;
    }

    private Page createPage(String fxmlFilePath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
        Parent root = loader.load();

        PageController controller = loader.getController();
        Scene scene = new Scene(root);

        controller.setPageSwitcher(this);

        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        scene.setOnKeyPressed(event -> {
            controller.handleKeyPress(event);
        });

        return new Page(scene, controller);
    }
}
