package typingtester.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Splitting {
    
    // ("hi hi ", ' ') -> {"hi", "hi", ""} (instead of {"hi", "hi"})
    public static String[] split2(String s, char separator) {
        if (s.length() == 0) return new String[] {""};
        
        List<String> splitList = new ArrayList<>(Arrays.asList(s.split(separator+"")));
        if (s.charAt(s.length()-1) == separator)
            splitList.add("");

        String[] splittedArray = new String[splitList.size()];
        return splitList.toArray(splittedArray); 
    }

    // ("hello world", ' ') -> {"hello ", "world"}
    public static String[] splitAfter(String s, char separator) {
        if (s.length() == 0) return new String[0];

        String[] splitted = s.split(separator+"");
        List<String> splittedList = new ArrayList<>();
        for (int i = 0; i < splitted.length-1; i++) {
            splittedList.add(splitted[i] + separator);
        }
        String lastElement = splitted[splitted.length-1];
        char lastCharOfS = s.charAt(s.length()-1);

        splittedList.add(lastElement + (lastCharOfS == separator ? separator : ""));

        String[] splittedArray = new String[splittedList.size()];
        return splittedList.toArray(splittedArray);
    }
}
