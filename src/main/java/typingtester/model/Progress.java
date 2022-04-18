package typingtester.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import typingtester.filehandling.MinimalStatFormat;

public class Progress {

    private List<Double> wpms = new ArrayList<>();
    private List<Double> accuracies = new ArrayList<>();

    public Progress(List<MinimalStatFormat> tests) {
        setTests(tests);
    }

    public void setTests(List<MinimalStatFormat> tests) {
        for (MinimalStatFormat test : tests) {
            wpms.add(test.getWPM());
            accuracies.add(test.getAccuracy());
        }
    }

    public double getWpmRecord() {
        return Collections.max(wpms);
    }
}
