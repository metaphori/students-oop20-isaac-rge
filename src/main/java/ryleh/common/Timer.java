package ryleh.common;

public interface Timer {

    /**
     * Checks if the timer is elapsed.
     * @return True if the time is elapsed, false otherwise
     */
    boolean isElapsed();

    /**
     * Starts a new timer.
     */
    void startTimer();

}