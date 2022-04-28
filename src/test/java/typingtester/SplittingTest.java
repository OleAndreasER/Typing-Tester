package typingtester;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import typingtester.model.Splitting;

public class SplittingTest {

    @Test
    public void testsplit() {

        String[] expected = {"hi", "hi", ""};

        Assertions.assertTrue(Arrays.equals(
            expected,
            Splitting.split("hi hi ", ' ')
        ));


        String[] expected2 = {"hi"};

        Assertions.assertTrue(Arrays.equals(
            expected2,
            Splitting.split("hi", ' ')
        ));


        String[] expected3 = {""};

        Assertions.assertTrue(Arrays.equals(
            expected3,
            Splitting.split("", ' ')
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

        Assertions.assertTrue(Arrays.equals(
            expected4,
            Splitting.splitAfter(" ", ' ')
        ));
    } 
}
