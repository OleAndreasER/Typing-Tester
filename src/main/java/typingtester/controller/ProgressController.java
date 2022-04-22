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
    Label wpmRecord, latestTests;

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
        
        String record = String.valueOf(progress.getWpmRecord());
        
        List<MinimalStatFormat> latest = progress.getLatestTests(10);

        latestTests.setText("Last 10 tests:\n"+Progress.toString(latest));

        drawChart();
        wpmRecord.setText("PB: "+record);
    }

    public void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB) {
            try {
                enterTypingTest();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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