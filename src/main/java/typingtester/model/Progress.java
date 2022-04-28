package typingtester.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Progress {

    private List<MinimalTestStats> tests;

    public Progress(List<MinimalTestStats> tests) {
        this.tests = tests;
    }

    public MinimalTestStats getWPMRecord() {
        double currentHighest = 0;
        MinimalTestStats record = null;
        for (MinimalTestStats test : tests) {
            if (test.getWPM() > currentHighest) {
                currentHighest = test.getWPM();
                record = test;
            }
        }
        return record;
    }

    public boolean WPMRecordIs(double WPM) {
        return WPM >= getWPMRecord().getWPM();
    }

    public List<Double> getWPMList() {
        List<Double> list = new ArrayList<>();
        for (MinimalTestStats test : tests)
            list.add(test.getWPM());

        return list;
    }

    public int getTestCount() {
        return tests.size();
    }
    
    public Progress getLatestTests(int n) {
        int size = tests.size();
        int amountOfTests = Math.min(n, size);
        List<MinimalTestStats> lastElements = new ArrayList<>(tests.subList(size-amountOfTests, size));
        return new Progress(lastElements);
    }

    public void reverse() {
        Collections.reverse(tests);
    }

    public String toString() {
        if (tests.isEmpty()) return "";
        String str = "";
        for (MinimalTestStats test : tests) {
            str += test.asDisplayed()+"\n";
        }

        return str.substring(0, str.length()-1); //removing last \n
    }

    public double getAverageWPM() {
        double total = 0;
        for (MinimalTestStats test : tests)
            total += test.getWPM();
        
        return total/tests.size();
    }
}
