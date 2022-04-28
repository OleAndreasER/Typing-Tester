package typingtester;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import typingtester.model.MinimalTestStats;

public class MinimalTestStatsTest {
    
    @Test
    public void testAsDisplayed() {
        MinimalTestStats stats = new MinimalTestStats(50.0061234, 95.0);
        Assertions.assertEquals(
            "50.01 WPM - 95.00% accuracy",
            stats.asDisplayed()
        );
    }
}
