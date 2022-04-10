package typingtester;

public class TypingTest {
    private String words;

    public TypingTest() {
        words = WordGenerator.getRandomWords(4);
    }

    public String getWords() { 
        return words;
    }

}
