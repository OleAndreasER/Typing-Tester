package typingtester;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinimalStatFormatTest {
    
    @Test
    public void testToString() {

        TypingTestStats stats = new TypingTestStats(
            "hello world ",
            "hi world ",
            60,
            7,
            2)
        ;

        MinimalStatFormat minimalStatFormat = new MinimalStatFormat(stats);

        String wpm = String.valueOf(stats.getWPM());
        String accuracy = String.valueOf(stats.getAccuracy());

        //System.out.println(wpm+";"+accuracy);
        //System.out.println(minimalStatFormat.toString());

        Assertions.assertTrue(
            minimalStatFormat.toString().equals(
                wpm+";"+accuracy
            )
        );
    }

    @Test
    public void testSaveInFormat() {
        String fromFile = "55.0;100.0";
        MinimalStatFormat minimalStatFormat = new MinimalStatFormat(fromFile);

        Assertions.assertTrue(
            minimalStatFormat.toString().equals(fromFile)
        );
    }
}
