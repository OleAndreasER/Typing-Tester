package typingtester;

import java.util.ArrayList;
import java.util.List;

public class TypingTestStats {
    private String words;
    private String typed;
    private int seconds;
    private int correctKeyPresses;
    private int incorrectKeyPresses;

    private float WPM;
    private float rawWPM;
    private float accuracy;
    private List<String> correctWords;
    private List<String> incorrectWords;

    public TypingTestStats(String words, String typed, int seconds, int correctKeyPresses, int incorrectKeyPresses) {
        this.words = words;
        this.typed = typed;
        this.seconds = seconds;
        this.correctKeyPresses = correctKeyPresses;
        this.incorrectKeyPresses = incorrectKeyPresses;
        setCorrectAndIncorrectWords();
        WPM = calculateWPM();
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
    
    private float calculateWPM() {
        //WPM counts 5 letters as one word. Including spaces before correct words.
        float correctLetters = String.join(" ", correctWords).length();
        return (correctLetters / 5) / ((float)(seconds)/60);
    }

    public float getWPM() {
        return WPM;
    }

    public int getIncorrectWords() {
        return incorrectWords.size();
    }

    public int getCorrectWords() {
        return correctWords.size();
    }

    //TODO
    public float getRawWPM() {
        return 0;
    }

    public float getAccuracy() {
        return (float)(correctKeyPresses) / (float)(correctKeyPresses+incorrectKeyPresses) * 100;
    }
}
