package typingtester.controller;


import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import typingtester.SceneController;
import typingtester.filehandling.FileHandling;
import typingtester.filehandling.MinimalStatFormat;
import typingtester.model.Progress;

public class ProgressController {
    private SceneController sceneController;

    @FXML
    Label progressStats;

    @FXML 
    LineChart<Integer, Double> chart;

    Progress progress;

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }


    @FXML
    private void enterTypingTest() throws IOException {
        sceneController.setTypingTest();
    }

    @FXML
    public void initialize() {
        List<MinimalStatFormat> tests = FileHandling.loadTests();
        progress = new Progress(tests);
        

        MinimalStatFormat record = progress.getWpmRecord();
        String testCount = String.valueOf(progress.getTestCount());
        List<MinimalStatFormat> latest = progress.getLatestTests(10);
        String averageWPM = String.format("%.2f", Progress.getAverageWPM(latest));

        progressStats.setText(statSegment("PB", record.asDisplayed())
                             +statSegment("Tests completed", testCount)
                             +statSegment("Last 10 tests", Progress.toString(latest))
                             +statSegment("Average of 10", averageWPM));

        drawChart();
    }

    private String statSegment(String headline, String data) {
        return headline+"\n"+data+"\n\n";
    }

    public void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB) {
            try {
                enterTypingTest();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //line chart
    private void drawChart() {
        Axis<Double> yAxis = chart.getYAxis();
        yAxis.setLabel("WPM");

        chart.getData().add(wpmSeries());
    }

    private XYChart.Series<Integer, Double> wpmSeries() {
        XYChart.Series<Integer, Double> series = new XYChart.Series<>();

        List<Double> WPMList = progress.getWPMList();

        for (int i = 0; i < WPMList.size(); i++)
            series.getData().add(new XYChart.Data<>(i+1, WPMList.get(i)));

        return series;
    }

}
