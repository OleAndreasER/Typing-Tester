package typingtester;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHandling {
    private static final Path filePath = Paths.get("tests.txt");
        

    public static void saveTest(StatFormat statFormat) {
        try {
            String previousContent = fileContent();
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString()));
            writer.write(previousContent + statFormat.toString()+"\n");
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String fileContent() throws IOException {
        return Files.readString(filePath);
    }

    public static List<MinimalStatFormat> loadTests(StatFormat statFormat) {
        try {
            String[] testsStr = fileContent().split("\n");
            List<MinimalStatFormat> tests = new ArrayList<>();
            for (String testStr : testsStr) {
                tests.add(new MinimalStatFormat(testStr));
            return tests;
        }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
