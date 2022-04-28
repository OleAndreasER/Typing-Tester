package typingtester.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Splitting {
    
    // ("hi hi ", ' ') -> {"hi", "hi", ""} (instead of {"hi", "hi"})
    public static String[] split2(String s, char separator) {
        if (s.length() == 0) return new String[] {""};
        
        if (s.equals(separator+""))
            return new String[] {"", ""};

        List<String> splitList = new ArrayList<>(Arrays.asList(s.split(separator+"")));

        if (s.charAt(s.length()-1) == separator)
            splitList.add("");

        return toArray(splitList); 
    }

    // ("hello world", ' ') -> {"hello ", "world"}
    public static String[] splitAfter(String s, char separator) {
        if (s.length() == 0) return new String[0];

        if (s.equals(separator+""))
            return new String[] {separator+""};

        String[] splitted = s.split(separator+"");
        List<String> splittedList = new ArrayList<>();
        for (int i = 0; i < splitted.length-1; i++) {
            splittedList.add(splitted[i] + separator);
        }

        String lastElement = splitted[splitted.length-1];
        char lastCharOfS = s.charAt(s.length()-1);

        splittedList.add(lastElement + (lastCharOfS == separator ? separator : ""));

        return toArray(splittedList);
    }

    private static String[] toArray(List<String> list) {
        String[] arr = new String[list.size()];
        return list.toArray(arr);
    }
}
