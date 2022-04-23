package typingtester.model;

import java.util.ArrayList;
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

    public MinimalStatFormat getWpmRecord() {
        double currentHighest = 0;
        MinimalStatFormat ret = null;
        for (MinimalStatFormat test : tests)
            if (test.getWPM() > currentHighest) {
                currentHighest = test.getWPM();
                ret = test;
            }

        return ret;
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
    
    public List<MinimalStatFormat> getLatestTests(int n) {
        int size = tests.size();
        int amountOfTests = Math.min(n, size);
        List<MinimalStatFormat> lastElements = new ArrayList<>(tests.subList(size-amountOfTests, size));
        Collections.reverse(lastElements);
        return lastElements;
    }

    public static String toString(List<MinimalStatFormat> tests) {
        String ret = "";
        for (MinimalStatFormat test : tests) {
            ret += test.asDisplayed()+"\n";
        }

        return ret.substring(0,ret.length()-1); //removing last \n
    }

    public static double getAverageWPM(List<MinimalStatFormat> tests) {
        double total = 0;
        for (MinimalStatFormat test : tests)
            total += test.getWPM();
        
        return total/tests.size();
    }
}
