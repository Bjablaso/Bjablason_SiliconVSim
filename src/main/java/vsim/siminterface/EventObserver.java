package vsim.siminterface;

import vsim.types.Quarter;

/**
 * Represents an observer in the observer pattern that monitors and responds to events.
 */
public interface EventObserver {

    /**
     * Updates the observer with a new quarterly event.
     *
     * @param quarterlyEvent The {@link Quarter} event to notify the observer about,
     *                       allowing it to respond or adapt to the event.
     */
    void update(Quarter quarterlyEvent);
}
