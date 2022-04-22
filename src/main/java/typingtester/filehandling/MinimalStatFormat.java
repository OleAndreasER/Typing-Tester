package typingtester.filehandling;

import typingtester.model.TypingTestStats;

public class MinimalStatFormat implements StatFormat{

    private double WPM;
    private double accuracy;

    public MinimalStatFormat(TypingTestStats typingTestStats) {
        saveInFormat(typingTestStats);
    }

    public MinimalStatFormat(String stats) {
        saveInFormat(stats);
    }

    public MinimalStatFormat() {

    }

    public double getWPM() {
        return WPM;
    }

    public double getAccuracy() {
        return accuracy;
    }

    @Override
    public void saveInFormat(TypingTestStats typingTestStats) {
        WPM = typingTestStats.getWPM();
        accuracy = typingTestStats.getAccuracy();
    }

    @Override
    public void saveInFormat(String stats) {
        String[] values = stats.split(";");
        if (values.length != 2)
            throw new IllegalArgumentException("Wrong format.");
        
        WPM = Double.parseDouble(values[0]);
        accuracy = Double.parseDouble(values[1]);
    }

    @Override
    public String toString() {
        return String.format(
            "%.2f;%.2f",
            WPM,
            accuracy
        );
    }

    public String asDisplayed() {
        return String.format(
            "%.2f WPM - %.2f%% ACC",
            WPM,
            accuracy
        );       
    }
}
