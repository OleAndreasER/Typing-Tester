package typingtester;

import javafx.animation.AnimationTimer;

//Calls event method after specified number of seconds.

public abstract class EventScheduler extends AnimationTimer {
    private long startTime;
    private boolean startTimeIsSet = false;
    private long duration;

    public EventScheduler(long seconds) {
        duration = (long) (seconds*1e9);
    }

    public abstract void event();

    @Override
    public void handle(long now) {
        if (!startTimeIsSet) {
            startTime = now;
            startTimeIsSet = true;
        }

        long timeActive = now - startTime;
        if (timeActive >= duration) {
            event();
            stop();
        }
    }
    
}
