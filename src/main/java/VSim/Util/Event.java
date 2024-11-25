package VSim.Util;

import VSim.Core.TechGiant;
import VSim.SimInterface.EventObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * This class Notify all observer in the list of change in quarterly events
 */
public class Event {
    private String currentEvent;
    private List<EventObserver> observers;

    /**
     * Initialize current quarterly event
     * @param currentEvent
     */
    public Event(String currentEvent) {
        this.currentEvent = currentEvent;
        observers = new ArrayList<EventObserver>();
    }

    /**
     * Add tech giant to the observer list
     * @param techGiant
     */
    public void addObserver(TechGiant techGiant) {
        observers.add(techGiant);
    }

    /**
     * Remove tech giant from the observer list
     * @param observer
     */
    public void removeObserver(EventObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notified all observer in the list of change in the current event
     */
    public void notifyObservers() {
        for (EventObserver observer : observers) {
            observer.update(currentEvent);
        }
    }
}
