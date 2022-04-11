package typingtester;

import javafx.animation.AnimationTimer;

//Calls event method after specified number of seconds, and onSecond on every second.

public abstract class EventScheduler extends AnimationTimer {
    private long startTime;
    private boolean startTimeIsSet = false;
    private long duration;
    private int elapsedSeconds;

    public EventScheduler(long seconds) {
        duration = (long) (seconds*1e9);
    }

    public abstract void event();
    public abstract void onSecond(int elapsedSeconds);

    @Override
    public void handle(long now) {
        if (!startTimeIsSet) {
            startTime = now;
            startTimeIsSet = true;
        }

        long timeElapsed = now - startTime;
        if (timeElapsed >= (elapsedSeconds + 1)*1e9) {
            elapsedSeconds++;
            onSecond(elapsedSeconds);
        }

        
        if (timeElapsed >= duration) {
            event();
            stop();
        }
    }
    
}
