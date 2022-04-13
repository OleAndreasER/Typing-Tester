package typingtester;

import java.util.ArrayList;
import java.util.List;

public class TypingTest {
    private String words;
    private String typed = "";
    private int caretIndex;
    private int seconds;

    private final int typedLength = 28;

    public TypingTest(int seconds) {
        words = WordGenerator.getRandomWords(300);
        this.seconds = seconds;
    }

    public TypingTest(int seconds, String words) {
        this.words = words;
        this.seconds = seconds;
    }

    public void type(String c) {
        if (c.length() > 1)
            throw new IllegalArgumentException("Only one character.");

        typed += c;

        if (c.equals(" "))
            caretIndex = words.indexOf(" ", caretIndex) + 1; //Jump to next word
            
        else if (currentWordLengthDifference() <= 0) //caret shouldn't move over the next word if you overtype a word.
            caretIndex++;
    }

    public void eraseLetter() {
        if (typed.length() == 0) return;

        //If the word is "hello", and you type "he" then " ", you would skip "llo". This makes it so you can go back to
        //the end of "he" if you erase the " ".
        if (typed.charAt(typed.length()-1) == ' ')
            caretIndex += Math.min(currentWordLengthDifference()-1, -1); 

        else if (currentWordLengthDifference() <= 0)
            caretIndex--;

        typed = typed.substring(0, typed.length()-1);
    }

    public String getTyped() {
        return typed;
    }

    public String getWords() { 
        return words;
    }

    public float getWPM() {
        String[] typedArr = typed.split(" ");
        String[] wordsArr = words.split(" ");

        List<String> correctWords = new ArrayList<>();
        for (int i = 0; i < typedArr.length; i++)
            if (typedArr[i].equals(wordsArr[i]))
                correctWords.add(typedArr[i]);

        float correctLetters = String.join(" ", correctWords).length();
        return (correctLetters / 5) / ((float)(seconds)/60);

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

    private int currentWordLengthDifference() {
        // typed: hellowor
        // words: hel
        // difference: 5

        String[] typedArr = typed.split(" ");
        String[] wordsArr = words.split(" ");
        String lastTyped = typedArr[typedArr.length - 1];
        String correspondingWord = wordsArr[typedArr.length -1];

        return lastTyped.length() - correspondingWord.length();
    }

}
