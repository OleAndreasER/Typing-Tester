package typingtester.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import typingtester.filehandling.MinimalStatFormat;

public class Progress {

    private List<MinimalStatFormat> tests;

    public Progress(List<MinimalStatFormat> tests) {
        this.tests = tests;
    }

    public MinimalStatFormat getWPMRecord() {
        double currentHighest = 0;
        MinimalStatFormat record = null;
        for (MinimalStatFormat test : tests) {
            if (test.getWPM() > currentHighest) {
                currentHighest = test.getWPM();
                record = test;
            }
        }
        return record;
    }

    public boolean isWPMRecord(double WPM) {
        return WPM >= getWPMRecord().getWPM();
    }

    public List<Double> getWPMList() {
        List<Double> list = new ArrayList<>();
        for (MinimalStatFormat test : tests)
            list.add(test.getWPM());

        return list;
    }

    public int getTestCount() {
        return tests.size();
    }
    
    public Progress getLatestTests(int n) {
        int size = tests.size();
        int amountOfTests = Math.min(n, size);
        List<MinimalStatFormat> lastElements = new ArrayList<>(tests.subList(size-amountOfTests, size));
        return new Progress(lastElements);
    }

    public void reverse() {
        Collections.reverse(tests);
    }

    public String toString() {
        if (tests.isEmpty()) return "";
        String str = "";
        for (MinimalStatFormat test : tests) {
            str += test.asDisplayed()+"\n";
        }

        return str.substring(0, str.length()-1); //removing last \n
    }

    public double getAverageWPM() {
        double total = 0;
        for (MinimalStatFormat test : tests)
            total += test.getWPM();
        
        return total/tests.size();
    }

}
