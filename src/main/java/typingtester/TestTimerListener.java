package typingtester;

public interface TestTimerListener {
    public void onSecond(int elapsedSeconds);
    public void onCompletion();
}
