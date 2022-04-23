package typingtester.filehandling;

import typingtester.model.TypingTestStats;

public interface StatFormat {

    //Set from TypingTestStats
    public void setStats(TypingTestStats typingTestStats);

    //As file string.
    public String toString(); 

    //Set from file string.
    public void setStats(String stats);
}
