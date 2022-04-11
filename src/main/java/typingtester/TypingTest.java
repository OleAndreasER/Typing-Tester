package typingtester;

public class TypingTest {
    private String words;
    private String typed = "";

    public TypingTest(int wordCount) {
        words = WordGenerator.getRandomWords(wordCount);
    }

    public void type(String c) {
        typed += c;
    }

    public void eraseLetter() {
        typed = typed.substring(0, typed.length()-1);
    }

    public String getTyped() {
        return typed;
    }

    public String getWords() { 
        return words;
    }

}
