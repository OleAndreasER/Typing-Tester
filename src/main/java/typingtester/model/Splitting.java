package typingtester.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Splitting {
    
    // ("hi hi ", ' ') -> {"hi", "hi", ""}
    //         instead of {"hi", "hi"}, as s.split(' ') does.
    public static String[] split(String s, char separator) {
        List<String> splitList = new ArrayList<>();
        String currentWord = "";
        for (char c : s.toCharArray()) {
            if (c == separator) {
                splitList.add(currentWord);
                currentWord = "";
            }
            else {
                currentWord += c;
            }
        }
        splitList.add(currentWord);

        return toArray(splitList); 
    }

    // ("hello world", ' ') -> {"hello ", "world"}
    public static String[] splitAfter(String s, char separator) {
        List<String> splitList = new ArrayList<>();
        String currentWord = "";
        for (char c : s.toCharArray()) {
            currentWord += c;
            if (c == separator) {
                splitList.add(currentWord);
                currentWord = "";
            }
        }
        if (currentWord.length() > 0)
            splitList.add(currentWord);

        return toArray(splitList);
    }

    private static String[] toArray(List<String> list) {
        String[] arr = new String[list.size()];
        return list.toArray(arr);
    }
}
