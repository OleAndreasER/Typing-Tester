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

        String wpm = "1.00";
        String accuracy = "77.78";

        Assertions.assertTrue(
            minimalStatFormat.toString().equals(
                wpm+";"+accuracy
            )
        );
    }

    @Test
    public void testSaveInFormat() {
        String fromFile = "55.00;100.00";
        MinimalStatFormat minimalStatFormat = new MinimalStatFormat(fromFile);

        Assertions.assertTrue(
            minimalStatFormat.toString().equals(fromFile)
        );
    }
}
