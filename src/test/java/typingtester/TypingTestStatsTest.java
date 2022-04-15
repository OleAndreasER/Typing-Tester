package typingtester;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TypingTestStatsTest {

    private void typeMultiple(String string, TypingTest typingTest) {
        for (char c : string.toCharArray())
            typingTest.type(c+"");
    }

    private TypingTestStats statsAfterTyping(String typed, String words) {
        TypingTest typingTest = new TypingTest(60, words);
        typeMultiple(typed, typingTest);
        return typingTest.getStats();
    }

    @Test
    public void testWPM() {
        TypingTestStats stats1 = statsAfterTyping("hey world", "hello world ");

        Assertions.assertEquals(
            (float)(0+0+5)/5,
            stats1.getWPM()
        );

        TypingTestStats stats2 = statsAfterTyping("hello world ", "hello world ");
        Assertions.assertEquals(
            (float)(5+1+5)/5, 
            stats2.getWPM()    
        );

        TypingTestStats stats3 = statsAfterTyping("hello   wor ld ", "he ll o wor ld ");
        Assertions.assertEquals(
            (float)(0+0+3+1+2)/5, 
            stats3.getWPM()    
        );

    }

    
}
