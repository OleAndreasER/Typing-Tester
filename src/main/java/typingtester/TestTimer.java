package typingtester;

import java.util.List;

import javafx.animation.AnimationTimer;

//Calls event method after specified number of seconds, and onSecond on every second.

public class TestTimer extends AnimationTimer {
    private TestTimerListener listener;

    private long startTime;
    private boolean startTimeIsSet = false;
    private long duration;
    private int elapsedSeconds = -1; 

    public TestTimer(long seconds, TestTimerListener listener) {
        duration = (long) (seconds*1e9);
        this.listener = listener;
    }

    @Override
    public void handle(long now) {
        if (!startTimeIsSet) {
            startTime = now;
            startTimeIsSet = true;
        }

        long timeElapsed = now - startTime;
        if (timeElapsed >= (elapsedSeconds + 1)*1e9) {
            elapsedSeconds++;
            listener.onSecond(elapsedSeconds);
        }
        
        if (timeElapsed >= duration) {
            listener.onCompletion();
            stop();
        }
    }
    
}
