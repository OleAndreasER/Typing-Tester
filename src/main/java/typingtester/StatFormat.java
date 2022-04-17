package typingtester;

public interface StatFormat {

    //Converting stats to this stat format.
    public void saveInFormat(TypingTestStats typingTestStats);

    //As it is to be written to a file.
    public String toString(); 

    //Inverse of toString
    public void saveInFormat(String stats);
}
