package typingtester.model;

import java.util.Collections;
import java.util.List;
import typingtester.filehandling.MinimalStatFormat;

public class Progress {

    private List<MinimalStatFormat> tests;

    public Progress(List<MinimalStatFormat> tests) {
        setTests(tests);
    }

    public void setTests(List<MinimalStatFormat> tests) {
        this.tests = tests;
    }

    public double getWpmRecord() {
        double currentHighest = 0;
        for (MinimalStatFormat test : tests)
            currentHighest = Math.max(currentHighest, test.getWPM());

        return currentHighest;
    }
    
    public List<MinimalStatFormat> getLatestTests(int n) {
        int size = tests.size();
        List<MinimalStatFormat> lastElements = tests.subList(size-n, size);
        Collections.reverse(lastElements);
        return lastElements;
    }

    public static String toString(List<MinimalStatFormat> tests) {
        String ret = "";
        for (MinimalStatFormat test : tests) {
            ret += test.asDisplayed()+"\n";
        }

        return ret;
    }

}
