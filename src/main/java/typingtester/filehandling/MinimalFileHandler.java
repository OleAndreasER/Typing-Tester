package typingtester.filehandling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import typingtester.model.MinimalTestStats;
import typingtester.model.Progress;
import typingtester.model.TypingTestStats;

public class MinimalFileHandler implements StatsFileHandler {

    private final Path filePath = Paths.get("tests.txt");

    public void saveTest(TypingTestStats stats) {
        try {
            String previousContent = readFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString()));
            writer.write(previousContent + toFileFormat(stats)+"\n");
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFile() throws IOException {
        return Files.readString(filePath);
    }

    public Progress loadTests() {
        List<MinimalTestStats> tests = new ArrayList<>();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath.toString()));
            String line; 
            while ((line = reader.readLine()) != null)
                tests.add(toMinimalTestStats(line));

            reader.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new Progress(tests);
    }

    public MinimalTestStats toMinimalTestStats(String stats) {
        String[] values = stats.split(";");
        if (values.length != 2)
            throw new IllegalArgumentException("Wrong format.");
        
        double WPM = Double.parseDouble(values[0]);
        double accuracy = Double.parseDouble(values[1]);
        
        return new MinimalTestStats(WPM, accuracy);
    }

    public String toFileFormat(TypingTestStats stats) {
        return String.format(
            "%.2f;%.2f",
            stats.getWPM(),
            stats.getAccuracy()
        );
    }
}
