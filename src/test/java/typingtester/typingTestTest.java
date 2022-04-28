package typingtester;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import typingtester.model.TypingTest;

public class TypingTestTest {

    @Test
    public void testTyping() {
        TypingTest typingTest = new TypingTest(60,
            "hello world "
        );

        typingTest.type("h");
        typingTest.type("e");

        Assertions.assertTrue(typingTest.getCorrectWordsDisplay().contains("he"));

        String expectedWords = "llo world ";

        Assertions.assertEquals(
            expectedWords,
            typingTest.getWordsDisplay().substring(0, expectedWords.length())
        );

        Assertions.assertThrows(IllegalArgumentException.class, () -> typingTest.type("ll"));
    }

    private void typeMultiple(String string, TypingTest typingTest) {
        for (char c : string.toCharArray())
            typingTest.type(c+"");
    }

    @Test
    public void testErasing() {
        TypingTest typingTest = new TypingTest(60,
            "hello world "
        );

        typeMultiple("hellos", typingTest);
        typingTest.eraseLetter();
        typingTest.eraseLetter();


        Assertions.assertTrue(typingTest.getCorrectWordsDisplay().contains("hell"));

        String expectedWords = "o world";

        Assertions.assertEquals(
            expectedWords,
            typingTest.getWordsDisplay().substring(0, expectedWords.length())
        );
    }

    @Test
    //Different standards from TypingTestStats. The words are supposed to be correct so far.
    //This is the standard for determining the color of typed letters as they are typed. 
    public void testWordCorrectness() { 
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
    
    @Test
    public void testWordSkip() {
        TypingTest typingTest = new TypingTest(60,
            "hello world "
        );

        typingTest.type("h");
        typingTest.type(" ");

        Assertions.assertEquals(
            'w',
            typingTest.getWordsDisplay().charAt(0)
        );

        //Undoing wordskip
        typingTest.type("w");  // h w|orld
        typingTest.eraseLetter(); //  h |world
        typingTest.eraseLetter(); //   h|ello

        Assertions.assertEquals(
            'e',    
            typingTest.getWordsDisplay().charAt(0)
        );

    }

    @Test
    public void testOvershooting() {
        TypingTest typingTest = new TypingTest(60,
            "hi aaa "
        );

        typingTest.type("h");
        typingTest.type("i");
        typingTest.type("i");
        typingTest.type("i"); // hiii| aaa

        Assertions.assertEquals(
            ' ',
            typingTest.getWordsDisplay().charAt(0)
        );
    }

}
