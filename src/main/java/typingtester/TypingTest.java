package typingtester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypingTest {

    //The typing test is split on the caret. 
    //Right side:
    private String words;
    //Left side:
    private String typed = "";
    private final int typedLength = 28;

    private int caretIndex; //caret's index on words

    private int seconds;


    public TypingTest(int seconds) {
        words = WordGenerator.getRandomWords(10); //More words get added as you type.
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

        if (c.equals(" ")) {
            addNewWord();
            caretIndex = words.indexOf(" ", caretIndex) + 1; //Jump to next word
        }
            
        else if (currentWordLengthDifference() <= 0) //caret shouldn't move if you overtype a word.
            caretIndex++;
    }

    public void eraseLetter() {
        if (typed.length() == 0) return;

        //If the word is "hello", and you type "he" then " ", you would skip "llo".
        //This code under places the caret back like this: "he|llo" if you erase the " ".
        if (typed.charAt(typed.length()-1) == ' ')
            caretIndex += Math.min(currentWordLengthDifference()-1, -1); 

        else if (currentWordLengthDifference() <= 0)
            caretIndex--;

        typed = typed.substring(0, typed.length()-1);
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

    private String getWordsDisplay() {
        return words.substring(caretIndex);
    }

    private String getTypedDisplay() {
        int missingChars = typedLength - typed.length();
        
        String typedCorrected = getTypedCorrectly();

        if (missingChars <= 0)
            return typedCorrected.substring(typedCorrected.length() - typedLength);
        
        String whitespace = "";
        for (int i = 0; i < missingChars; i++)
            whitespace += " ";

        return whitespace + typedCorrected;
    }

    private String getIncorrectTypedDisplay() {
        int missingChars = typedLength - typed.length();
        
        String typedIncorrect = getTypedIncorrectly();

        if (missingChars <= 0)
            return typedIncorrect.substring(typedIncorrect.length() - typedLength);
        
        String whitespace = "";
        for (int i = 0; i < missingChars; i++)
            whitespace += " ";

        return whitespace + typedIncorrect;
    }
    
    public String toString() {
        return getTypedDisplay() + getWordsDisplay();
    }

    public String incorrectWords() {
        return getIncorrectTypedDisplay();
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

    private void addNewWord() {
        words += WordGenerator.getRandomWords(1);
    }

    private List<String> splitAfter(String s, char separator) {
        if (s.length() == 0) return new ArrayList<String>();

        String[] splitted = s.split(separator+"");
        List<String> splittedList = new ArrayList<>(Arrays.asList(splitted));
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < splittedList.size()-1; i++) {
            ret.add(splittedList.get(i) + separator);
        }
        String lastElement = splittedList.get(splittedList.size()-1);
        char lastCharOfS = s.charAt(s.length()-1);

        ret.add(lastElement + (lastCharOfS == separator ? separator : ""));
        return ret;
    }

    private String getTypedIncorrectly() {
        List<String> typedList = splitAfter(typed, ' ');
        List<String> wordsList = splitAfter(words, ' ');

        String ret = "";
        for (int i = 0; i < typedList.size(); i++) {
            String typedWord = typedList.get(i);
            ret += hideIf(typedWord, isCorrectSoFar(typedWord, wordsList.get(i)));
        }
        return ret;
    }

    private String getTypedCorrectly() {
        List<String> typedList = splitAfter(typed, ' ');
        List<String> wordsList = splitAfter(words, ' ');

        String ret = "";
        for (int i = 0; i < typedList.size(); i++) {
            String typedWord = typedList.get(i);
            ret += hideIf(typedWord, !isCorrectSoFar(typedWord, wordsList.get(i)));
        }
        return ret;
    }

    private String hideIf(String s, boolean condition) {
        if (!condition) return s;
        String hidden = "";
        for (int i = 0; i < s.length(); i++)
            hidden += " ";
        return hidden;
    }

    private boolean isCorrectSoFar(String word, String solution) {
        for (int i = 0; i < word.length(); i++)
            if (word.charAt(i) != solution.charAt(i))
                return false;
        
        return true;
    }
}
