package typingtester;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import typingtester.model.Splitting;

public class SplittingTest {

    @Test
    public void testSplit2() {

        String[] expected = {"hi", "hi", ""};

        Assertions.assertTrue(Arrays.equals(
            expected,
            Splitting.split2("hi hi ", ' ')
        ));


        String[] expected2 = {"hi"};

        Assertions.assertTrue(Arrays.equals(
            expected2,
            Splitting.split2("hi", ' ')
        ));


        String[] expected3 = {"", ""};

        Assertions.assertTrue(Arrays.equals(
            expected3,
            Splitting.split2("", ' ')
        ));
    }

    @Test
    public void testSplitAfter() {


        String[] expected = {"hello ", "world"};

        Assertions.assertTrue(Arrays.equals(
            expected,
            Splitting.splitAfter("hello world", ' ')
        ));
        
        String[] expected2 = {"hello ", "world "};

        Assertions.assertTrue(Arrays.equals(
            expected2,
            Splitting.splitAfter("hello world ", ' ')
        ));

        String[] expected3 = {};

        Assertions.assertTrue(Arrays.equals(
            expected3,
            Splitting.splitAfter("", ' ')
        ));

        String[] expected4 = {" "};

        System.out.println('i'+"".split(" ")[0]+'i');
        System.out.println(" ".split(" ").length+"' '");

        Assertions.assertTrue(Arrays.equals(
            expected4,
            Splitting.splitAfter(" ", ' ')
        ));
    } 
}
