package typingtester;

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
        return String.valueOf(WPM)+";"+String.valueOf(accuracy);
    }
    
}
