package VSim.Util;

import VSim.Core.TechGiant;
import VSim.SimInterface.EventObserver;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private String currentEvent;
    private List<EventObserver> observers;

    public Event(String currentEvent) {
        this.currentEvent = currentEvent;
        observers = new ArrayList<EventObserver>();
    }

    public void addObserver(EventObserver techGiant) {
        observers.add(techGiant);
    }

    public void removeObserver(EventObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (EventObserver observer : observers) {
            observer.update(currentEvent);
        }
    }
}
