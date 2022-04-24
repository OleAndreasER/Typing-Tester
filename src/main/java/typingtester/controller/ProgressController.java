package typingtester.controller;


import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import typingtester.filehandling.MinimalFileHandler;
import typingtester.model.MinimalTestStats;
import typingtester.model.Progress;

public class ProgressController extends PageController {

    @FXML
    Label progressStats;

    @FXML 
    LineChart<Integer, Double> progressChart;

    Progress progress;

    @FXML
    public void initialize() {

        MinimalFileHandler fileHandler = new MinimalFileHandler();
        progress = fileHandler.loadTests();
        
        plotWPMSeries();

        //Stats text
        if (progress.getTestCount() == 0) {
            progressStats.setText("No tests!");
            return;
        }
        MinimalTestStats record = progress.getWPMRecord();
        String testCount = String.valueOf(progress.getTestCount());
        Progress latest = progress.getLatestTests(10);
        latest.reverse();
        String averageWPM = String.format("%.2f", latest.getAverageWPM());

        progressStats.setText(statSegment("PB", record.asDisplayed())
                             +statSegment("Tests completed", testCount)
                             +statSegment("Last 10 tests", latest.toString())
                             +statSegment("Average of 10", averageWPM));
    }

    private void plotWPMSeries() {
        List<Double> WPMList = progress.getWPMList();

        XYChart.Series<Integer, Double> series = new XYChart.Series<>();

        for (int i = 0; i < WPMList.size(); i++)
            series.getData().add(new XYChart.Data<>(i+1, WPMList.get(i))); //i+1 adds space before first point on the chart

        progressChart.getData().add(series);
    }

    private static String statSegment(String headline, String data) {
        return headline+"\n"+data+"\n\n";
    }

    public void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB)
            newTypingTest();
    }

    @FXML
    private void newTypingTest() {
        try {
            sceneController.setTypingTest();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
