package typingtester;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import typingtester.model.TypingTest;
import typingtester.model.TypingTestStats;

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
            (double)(0+0+5)/5,
            stats1.getWPM()
        );

        TypingTestStats stats2 = statsAfterTyping("hello world ", "hello world ");
        Assertions.assertEquals(
            (double)(5+1+5)/5, 
            stats2.getWPM()    
        );

        TypingTestStats stats3 = statsAfterTyping("hello   wor ld ", "he ll o wor ld ");
        Assertions.assertEquals(
            (double)(0+0+3+1+2)/5, 
            stats3.getWPM()    
        );
    }

    @Test
    public void testCorrectWords() { //Different standards from TypingTest
        TypingTestStats stats = statsAfterTyping(
            "x o x o x x",
            "x x x x x x x "
        );

        Assertions.assertEquals(
            4,
            stats.getCorrectWords()
        );

        Assertions.assertEquals(
            2,
            stats.getIncorrectWords()
        );
    }
    
    @Test
    public void testAccuracy() {
        TypingTestStats stats1 = statsAfterTyping("hello world", "hello world ");
        Assertions.assertEquals(
            100.0, 
            stats1.getAccuracy()
        );

        TypingTest typingTest = new TypingTest(60, "abc ");
        typingTest.type("a"); 
        typingTest.type("c"); //mistake
        typingTest.eraseLetter();
        typingTest.type("b");
        typingTest.type(" "); //mistake
        typingTest.eraseLetter();
        typingTest.type("c");
        typingTest.type("d"); //mistake
        typingTest.type(" ");

        TypingTestStats stats2 = typingTest.getStats();
        double keyPresses = 7;
        double correct = 4; 
        Assertions.assertEquals(
            (correct/keyPresses) * 100, 
            stats2.getAccuracy()
        );
    }

    @Test
    public void testRawWPM() {
        TypingTestStats stats = statsAfterTyping(
            "x o x o x x",
            "x x x x x x x "
        );

        Assertions.assertEquals(
            11.0/5.0,
            stats.getRawWPM());
    }
}
