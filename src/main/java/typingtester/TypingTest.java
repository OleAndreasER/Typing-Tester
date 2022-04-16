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

    private static final int typedDisplayLength = 28;

    private int caretIndex; //caret's index on words

    private int seconds;

    //For accuracy calculations
    private int correctKeyPresses;
    private int incorrectKeyPresses;


    public TypingTest(int seconds) {
        words = WordGenerator.getRandomWords(10); //More words get added as you type.
        this.seconds = seconds;
    }

    public TypingTest(int seconds, String words) {
        this.words = words;
        this.seconds = seconds;
    }


    public String getWordsDisplay() {
        return words.substring(caretIndex);
    }

    public String getCorrectWordsDisplay() {
        return getTypedDisplay(true);
    }

    public String getIncorrectWordsDisplay() {
        return getTypedDisplay(false);
    }

    public TypingTestStats getStats() {
        return new TypingTestStats(words, typed, seconds, correctKeyPresses, incorrectKeyPresses);
    }


    // --Determining correct/incorrect words
    // Correct means correct so far
    private String getTypedDisplay(boolean isCorrectWords) {
        int missingChars = typedDisplayLength - typed.length();
        
        String typedMasked = getTypedMasked(isCorrectWords);

        if (missingChars <= 0)
            return typedMasked.substring(typedMasked.length() - typedDisplayLength);
        
        String whitespace = "";
        for (int i = 0; i < missingChars; i++)
            whitespace += " ";

        return whitespace + typedMasked;
    }

    private String getTypedMasked(boolean isCorrectWords) { // masks with " "
        String[] typedList = splitAfter(typed, ' ');
        String[] wordsList = splitAfter(words, ' ');

        String ret = "";
        for (int i = 0; i < typedList.length; i++) {
            String typedWord = typedList[i];
            boolean isCorrectWord = isCorrectSoFar(typedWord, wordsList[i]);
            
            ret += maskIf(typedWord, isCorrectWords ? !isCorrectWord : isCorrectWord);
        }
        return ret;
    }

    // ("hello world", ' ') -> {"hello ", "world"}
    private String[] splitAfter(String s, char separator) {

        if (s.length() == 0) return new String[0];

        String[] splitted = s.split(separator+"");
        List<String> splittedList = new ArrayList<>();
        for (int i = 0; i < splitted.length-1; i++) {
            splittedList.add(splitted[i] + separator);
        }
        String lastElement = splitted[splitted.length-1];
        char lastCharOfS = s.charAt(s.length()-1);

        splittedList.add(lastElement + (lastCharOfS == separator ? separator : ""));

        String[] ret = new String[splittedList.size()];
        return splittedList.toArray(ret);
    }

    private boolean isCorrectSoFar(String word, String solution) {
        for (int i = 0; i < word.length(); i++)
            if (word.charAt(i) != solution.charAt(i))
                return false;
        
        return true;
    }

    private String maskIf(String s, boolean condition) {
        if (!condition) return s;
        String masked = "";
        for (int i = 0; i < s.length(); i++)
            masked += " ";
        return masked;
    }


    // --Controlling the test
    public void type(String c) {
        if (c.length() > 1)
            throw new IllegalArgumentException("Only one character.");


        if (isCorrectKeyPress(c)) correctKeyPresses++;
        else incorrectKeyPresses++;

        typed += c;

        if (c.equals(" ")) {
            addNewWord();
            caretIndex = words.indexOf(" ", caretIndex) + 1; //Jumps to next word
        }
            
        else if (currentWordLengthDifference() <= 0) //caret shouldn't move if you overtype a word.
            caretIndex++;
    }

    private boolean isCorrectKeyPress(String c) {
        String[] typedArr = split2(typed, ' ');
        String[] wordsArr = split2(words, ' ');
        String lastTyped = typedArr[typedArr.length - 1];
        String correspondingWord = wordsArr[typedArr.length -1];

        if (lastTyped.length() >= correspondingWord.length())
            return c.equals(" ");

        char nextChar = correspondingWord.charAt(lastTyped.length());
        return c.equals(nextChar+"");
    }

    private void addNewWord() {
        words += WordGenerator.getRandomWords(1);
    }

    public void eraseLetter() {
        if (typed.length() == 0) return;

        //Reverses word jump
        if (typed.charAt(typed.length()-1) == ' ')
            caretIndex += Math.min(currentWordLengthDifference()-1, -1); 

        else if (currentWordLengthDifference() <= 0)
            caretIndex--;

        typed = typed.substring(0, typed.length()-1);
    }

    private int currentWordLengthDifference() {
        // typed: ... hellowor
        // words: ... hello ...
        // difference: 3

        String[] typedArr = typed.split(" ");
        String[] wordsArr = words.split(" ");
        String lastTyped = typedArr[typedArr.length - 1];
        String correspondingWord = wordsArr[typedArr.length -1];

        return lastTyped.length() - correspondingWord.length();
    }

    // ("hi hi ", ' ') -> {"hi", "hi", ""} (instead of {"hi", "hi"})
    private String[] split2(String s, char separator) {
        if (s.length() == 0) return new String[] {""};
        
        List<String> splitList = new ArrayList<>(Arrays.asList(s.split(separator+"")));
        if (s.charAt(s.length()-1) == separator)
            splitList.add("");

        String[] ret = new String[splitList.size()];
        return splitList.toArray(ret); 
    }
}
