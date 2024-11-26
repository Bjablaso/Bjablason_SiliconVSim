package vsim.util;

import java.util.ArrayList;
import java.util.List;

import vsim.core.TechGiant;
import vsim.siminterface.EventObserver;
import vsim.types.Quarter;



/**
 * This class Notify all observer in the list of change in quarterly events.
 */
public class Event {
    private Quarter currentEvent;
    private List<EventObserver> observers;

    /**
     * Initialize current quarterly event.
     * @param currentEvent -> take in a reference Quarter object.
     */
    public Event(Quarter currentEvent) {
        this.currentEvent = currentEvent;
        observers = new ArrayList<EventObserver>();
    }

    /**
     * Add tech giant to the observer list.
     * @param techGiant -> Take in a reference to a Tech Giant object.
     */
    public void addObserver(TechGiant techGiant) {
        observers.add(techGiant);
    }

    /**
     * Remove tech giant from the observer list.
     * @param observer -> take in a reference to a Tech Giant Object.
     */
    public void removeObserver(TechGiant observer) {
        observers.remove(observer);
    }

    /**
     * Notified all observer in the list of change in the current event.
     */
    public void notifyObservers() {
        for (EventObserver observer : observers) {
            observer.update(currentEvent);
        }
    }

    public void updateEvent(Quarter quarter) {
        this.currentEvent = quarter;
    }

    public Quarter getCurrentEvent() {
        return currentEvent;
    }
}
