package typingtester;

public class TypingTest {
    private String words;
    private String typed = "";
    private int caretIndex;

    private final int typedLength = 28;

    public TypingTest(int wordCount) {
        words = WordGenerator.getRandomWords(wordCount);
    }

    public void type(String c) {
        typed += c;
        //"ole and|reas|land"
        //"ole and|"

        if (c.equals(" ")) {
            caretIndex = words.indexOf(" ", caretIndex) + 1; //Jump to next word
        }
        else {
            caretIndex++;
        }
    }

    public void eraseLetter() {
        if (typed.length() == 0) return;
        caretIndex--;
        typed = typed.substring(0, typed.length()-1);
    }

    public String getTyped() {
        return typed;
    }

    public String getWords() { 
        return words;
    }

    public String getWordsAsDisplayed() {
        return "|"+words.substring(caretIndex);
    }

    public String getTypedAsDisplayed() {
        int missingChars = typedLength - typed.length();
        
        if (missingChars <= 0)
            return typed.substring(typed.length() - typedLength);
        
        String whitespace = "";
        for (int i = 0; i < missingChars; i++)
            whitespace += " ";

        return whitespace + typed;
    }

}
