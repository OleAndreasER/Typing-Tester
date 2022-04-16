package typingtester;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileHandling {
    private static final String fileLocation = "tests.txt";

    public static void saveTest(StatFormat statFormat) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation));

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<StatFormat> loadTests() {
        return null;
    }
}
