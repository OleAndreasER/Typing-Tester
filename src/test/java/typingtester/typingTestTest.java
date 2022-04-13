package typingtester;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class typingTestTest {

    private void typeMultiple(String string, TypingTest typingTest) {
        for (char c : string.toCharArray())
            typingTest.type(c+"");
    }

    @Test
    public void testWPM() {
        TypingTest typingTest1 = new TypingTest(60,
            "word1 word2 word3 wo" 
        );
        
        typeMultiple("word1 word2 wo", typingTest1);

        //"word1 word2 wo"
        Assertions.assertEquals(
            (float)(5+1+5) /5,
            typingTest1.getWPM()
        );


        TypingTest typingTest2 = new TypingTest(60,
            "word1 word2 word3 wo" 
        );

        typeMultiple("word1 word2 word3 wo", typingTest2);
        //"word1 word2 word3 wo"
        Assertions.assertEquals(
            (float)(5+1+5+1+5+1+2) /5,
            typingTest2.getWPM()
        );
        
        typingTest2.type("p");
        //"word1 word2 word3 wop"
        Assertions.assertEquals(
            (float)(5+1+5+1+5) /5,
            typingTest2.getWPM()
        );

        typingTest2.eraseLetter();
        typingTest2.eraseLetter();
        //"word1 word2 word3 w"
        Assertions.assertEquals(
            (float)(5+1+5+1+5) /5,
            typingTest2.getWPM()
        );

        typingTest2.eraseLetter();
        //"word1 word2 word3 "
        Assertions.assertEquals(
            (float)(5+1+5+1+5) /5,
            typingTest2.getWPM()
        );

        typingTest2.eraseLetter();
        typingTest2.eraseLetter();
        typingTest2.type("4");
        typingTest2.type(" ");
        //"word1 word2 word4 "
        Assertions.assertEquals(
            (float)(5+1+5) /5,
            typingTest2.getWPM()
        );
    }
    
}
