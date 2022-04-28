package typingtester;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import typingtester.model.MinimalTestStats;
import typingtester.model.Progress;

public class ProgressTest {
    Progress progress = new Progress(new ArrayList<MinimalTestStats>(Arrays.asList(
        new MinimalTestStats(8, 100),
        new MinimalTestStats(4, 100),
        new MinimalTestStats(6, 100)
    )));

    @Test
    public void testWPMRecord() {
        Assertions.assertEquals(
            8.0,
            progress.getWPMRecord().getWPM()
        );

        Assertions.assertTrue(progress.WPMRecordIs(8.0));
    }

    @Test
    public void testAverage() {
        Assertions.assertEquals(
            6.0,
            progress.getAverageWPM()
        );
    }

    @Test
    public void testToString() {
        Assertions.assertEquals(
            "8.00 WPM - 100.00% accuracy\n"
           +"4.00 WPM - 100.00% accuracy\n"
           +"6.00 WPM - 100.00% accuracy",

           progress.toString()
        );
    }

    @Test
    public void testGetLatestTests() {
        Assertions.assertEquals(
            "4.00 WPM - 100.00% accuracy\n"
           +"6.00 WPM - 100.00% accuracy",

           progress.getLatestTests(2).toString()
        );
    }

    @Test
    public void testReverse() {
        Progress latest = progress.getLatestTests(2);
        latest.reverse();
        Assertions.assertEquals(
            "6.00 WPM - 100.00% accuracy\n"
           +"4.00 WPM - 100.00% accuracy",
            
           latest.toString()
        );
    }

    @Test
    public void testCount() {
        Assertions.assertEquals(3, progress.getTestCount());
    }
}
