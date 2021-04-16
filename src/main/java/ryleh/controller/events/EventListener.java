package ryleh.controller.events;

public interface EventListener {
    /**
     * Notify the happening of an Event.
     * 
     * @param e Event to be notified
     */
    void notifyEvent(Event e);
}
