package typingtester;

import java.util.ArrayList;
import java.util.List;

public class TypingTestStats {
    private String words;
    private String typed;
    private int seconds;

    private float WPM;
    private float rawWPM;
    private float accuracy;
    private int correctWords;
    private int incorrectWords;

    public TypingTestStats(String words, String typed, int seconds) {
        this.words = words;
        this.typed = typed;
        this.seconds = seconds;
        WPM = setWPM();
    }    
    
    private float setWPM() {
        String[] typedArr = typed.split(" ");
        String[] wordsArr = words.split(" ");

        List<String> correctWords = new ArrayList<>();
        for (int i = 0; i < typedArr.length; i++)
            if (typedArr[i].equals(wordsArr[i]))
                correctWords.add(typedArr[i]);

        float correctLetters = String.join(" ", correctWords).length();
        return (correctLetters / 5) / ((float)(seconds)/60);

    }

    public float getWPM() {
        return WPM;
    }
}
