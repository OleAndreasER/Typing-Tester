package typingtester;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import typingtester.model.TypingTest;

public class TypingTestTest {

    private void typeMultiple(String string, TypingTest typingTest) {
        for (char c : string.toCharArray())
            typingTest.type(c+"");
    }

    @Test
    public void testCorrectWords() { //Different standards from TypingTestStats
        TypingTest typingTest = new TypingTest(60,
            "hello world "
        );

        typeMultiple("hi wo", typingTest);
        String correct = typingTest.getCorrectWordsDisplay();
        String incorrect = typingTest.getIncorrectWordsDisplay();

        Assertions.assertTrue(correct.contains("wo"));
        Assertions.assertFalse(incorrect.contains("wo"));

        Assertions.assertTrue(incorrect.contains("hi"));
        Assertions.assertFalse(correct.contains("hi"));

        typingTest.type(" ");
        String correct2 = typingTest.getCorrectWordsDisplay();
        String incorrect2 = typingTest.getIncorrectWordsDisplay();
        Assertions.assertFalse(correct2.contains("wo"));
        Assertions.assertTrue(incorrect2.contains("wo"));

    }
    
}
