package typingtester;

public class TypingTest {
    private String words;
    private String typed = "";

    private final int typedLength = 27;

    public TypingTest(int wordCount) {
        words = WordGenerator.getRandomWords(wordCount);
    }

    public void type(String c) {
        typed += c;
    }

    public void eraseLetter() {
        if (typed.length() == 0) return;
        typed = typed.substring(0, typed.length()-1);
    }

    public String getTyped() {
        return typed;
    }

    public String getWords() { 
        return words;
    }

    public String getTypedAsDisplayed() {
        int missingChars = typedLength - typed.length();
        
        if (missingChars <= 0)
            return typed.substring(typed.length() - typedLength) + "|";
        
        String whitespace = "";
        for (int i = 0; i < missingChars; i++)
            whitespace += " ";

        return whitespace + typed + "|";
    }

}
