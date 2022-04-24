package typingtester.filehandling;

import typingtester.model.Progress;
import typingtester.model.TypingTestStats;

public interface StatsFileHandler {

    public void saveTest(TypingTestStats stats);

    public Progress loadTests();

}
