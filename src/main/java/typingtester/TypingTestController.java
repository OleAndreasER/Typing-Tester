package typingtester;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TypingTestController implements TestTimerListener {
    private TypingTest typingTest;
    private SceneController sceneController;
    private TestTimer testTimer;

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    @FXML
    private Label result, whiteWords, blackWords, timeleft;

    public void handleKeyPress(KeyEvent event) {
        
        if (!testTimerIsOn()) {
            newTestTimer();
        }

        if (event.getCode() == KeyCode.BACK_SPACE) {
            typingTest.eraseLetter();
        }
        else if (event.getCode() == KeyCode.TAB)
            resetTest();
        else if (event.getCode() == KeyCode.ENTER);
        else if (event.getCode() == KeyCode.ESCAPE);
        else
            typingTest.type(event.getText());

        displayTest();
    }

    private void displayTest() {
        whiteWords.setText(typingTest.getIncorrectWordsDisplay()
                         + typingTest.getWordsDisplay());
        blackWords.setText(typingTest.getCorrectWordsDisplay());
    }

    private void newTestTimer() {
        testTimer = new TestTimer(60, this);
        testTimer.start();
    }

    private void endTimer() {
        testTimer.stop();
        testTimer = null;
        timeleft.setText("");
    }

    //Creates new test, but does not start it.
    private void newTest() {
        typingTest = new TypingTest(60);
        displayTest();
    }

    private void resetTest() {
        newTest();
        endTimer();
    }

    @FXML
    private void enterStats() {
        sceneController.setStats();
    }

    @FXML
    public void initialize() {
        newTest();
    }

    //Timer listening
    @Override
    public void onSecond(int elapsedSeconds) {
        timeleft.setText(String.valueOf(60 - elapsedSeconds));
    }

    @Override
    public void onCompletion() {
        TypingTestStats stats = typingTest.getStats();
        String accuracy = String.format("%.2f", stats.getAccuracy());
        result.setText(
            "WPM: "+String.valueOf(stats.getWPM())+"\n"+
            "Raw: "+String.valueOf(stats.getRawWPM())+"\n"+
            "Accuracy: "+accuracy+"%\n"+
            "Correct/Incorrect: "+String.valueOf(stats.getCorrectWords())+
            "/"+String.valueOf(stats.getIncorrectWords())
        );

        FileHandling.saveTest(new MinimalStatFormat(stats));

        resetTest();
    }
    //

    private boolean testTimerIsOn() {
        return testTimer != null;
    }

}
