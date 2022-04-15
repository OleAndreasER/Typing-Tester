package typingtester;

import java.util.ArrayList;
import java.util.List;

public class TypingTestStats {
    private String words;
    private String typed;
    private int seconds;
    private int correctKeyPresses;
    private int incorrectKeyPresses;

    private List<String> correctWords;
    private List<String> incorrectWords;

    public TypingTestStats(String words, String typed, int seconds, int correctKeyPresses, int incorrectKeyPresses) {
        this.words = words;
        this.typed = typed;
        this.seconds = seconds;
        this.correctKeyPresses = correctKeyPresses;
        this.incorrectKeyPresses = incorrectKeyPresses;
        setCorrectAndIncorrectWords();
    }    

    private void setCorrectAndIncorrectWords() {
        String[] typedArr = typed.split(" ");
        String[] wordsArr = words.split(" ");

        correctWords = new ArrayList<>();
        incorrectWords = new ArrayList<>();
        for (int i = 0; i < typedArr.length; i++)
            if (typedArr[i].equals(wordsArr[i]))
                correctWords.add(typedArr[i]);
            else
                incorrectWords.add(typedArr[i]);
    }
    
    public double getWPM() {
        //WPM counts 5 letters as one word. Including spaces before correct words.
        double correctLetters = String.join(" ", correctWords).length();
        return (correctLetters / 5) / ((double)(seconds)/60);
    }

    public int getIncorrectWords() {
        return incorrectWords.size();
    }

    public int getCorrectWords() {
        return correctWords.size();
    }

    public double getRawWPM() {
        double letters = typed.length();
        return (letters / 5) / ((double)(seconds)/60);
    }

    public double getAccuracy() {
        return (double)(correctKeyPresses) / (double)(correctKeyPresses+incorrectKeyPresses) * 100;
    }
}
