package typingtester.model;

public class MinimalTestStats {

    private final double WPM;
    private final double accuracy;

    public MinimalTestStats(double WPM, double accuracy) {
        this.WPM = WPM;
        this.accuracy = accuracy;
    }

    public double getWPM() {
        return WPM;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public String asDisplayed() {
        return String.format(
            "%.2f WPM - %.2f%% accuracy",
            WPM,
            accuracy
        );       
    }
}
