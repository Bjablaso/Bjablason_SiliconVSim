import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vsim.core.TechGiant;
import vsim.types.Quarter;


import vsim.util.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the QuarterCycle and Event classes.
 * Verifies the functionality of the quarter cycle and observer notification mechanism.
 */
@DisplayName("Test Event Cycle and Observer Pattern")
public class TestEventCycle {
    private QuarterCycle quarterCycle;
    private Event eventNotifier;
    private List<TechGiant> techGiants;

    /**
     * Sets up the test environment with a QuarterCycle instance, an Event notifier,
     * and a list of Tech Giant observers.
     */
    @BeforeEach
    void setup() {
        // Initialize the quarter cycle
        quarterCycle = QuarterCycle.getSinglequarterInstance();

        // Initialize the event notifier with the first quarter
        eventNotifier = new Event(Quarter.FirstQuarter);

        // Initialize Tech Giants and add them as observers
        techGiants = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            TechGiant techGiant = new TechGiant("Tech Giant " + i, 500000);
            techGiants.add(techGiant);
            eventNotifier.addObserver(techGiant);
        }
    }

    /**
     * Tests that the QuarterCycle iterates through all quarters in the correct order
     * and restarts from the first quarter after completing a cycle.
     */
    @Test
    @DisplayName("Test Quarter Cycle Iteration")
    void testQuarterCycleIteration() {
        // Check all quarters in the cycle
        Quarter[] quarters = Quarter.values();
        for (Quarter quarter : quarters) {
            Quarter currentQuarter = quarterCycle.getcurrentquaterCycle();
            assertEquals(quarter, currentQuarter, "Quarter cycle should iterate through all quarters in order.");
        }

        // Ensure the cycle restarts
        Quarter restartQuarter = quarterCycle.getcurrentquaterCycle();
        assertEquals(Quarter.FirstQuarter, restartQuarter, "Quarter cycle should restart from the first quarter.");
    }

    /**
     * Tests that QuarterCycle adheres to the Singleton design pattern
     * by verifying that multiple instances point to the same object.
     */
    @Test
    @DisplayName("Test Singleton Property of QuarterCycle")
    void testSingletonQuarterCycle() {
        QuarterCycle anotherInstance = QuarterCycle.getSinglequarterInstance();
        assertSame(quarterCycle, anotherInstance, "QuarterCycle should follow the Singleton design pattern.");
    }

    /**
     * Tests that the Event class initializes with the correct quarter.
     */
    @Test
    @DisplayName("Test Initial Event Setup")
    void testInitialEventSetup() {
        assertEquals(Quarter.FirstQuarter, eventNotifier.getCurrentEvent(), "Initial event should match the first quarter.");
    }

    /**
     * Tests that updating the Event notifies all observers and updates their state
     * to reflect the new event.
     */
    @Test
    @DisplayName("Test Update Event and Notify Observers")
    void testUpdateEventAndNotifyObservers() {
        // Update event to SecondQuarter
        Quarter newQuarter = Quarter.SecondQuarter;
        eventNotifier.updateEvent(newQuarter);
        eventNotifier.notifyObservers();

        // Validate that all observers have updated their current event
        for (TechGiant techGiant : techGiants) {
            assertEquals(newQuarter, techGiant.getCurrentEvent(), techGiant.getName() + " should have updated to " + newQuarter);
        }
    }

    /**
     * Tests adding new observers to the Event notifier and removing existing ones.
     * Validates that added observers receive updates and removed observers do not.
     */
    @Test
    @DisplayName("Test Adding and Removing Observers")
    void testAddAndRemoveObservers() {
        TechGiant newTechGiant = new TechGiant("New Tech Giant", 300000);
        eventNotifier.addObserver(newTechGiant);

        // Update the event and notify
        Quarter updatedQuarter = Quarter.ThirdQuarter;
        eventNotifier.updateEvent(updatedQuarter);
        eventNotifier.notifyObservers();

        // Verify the new Tech Giant receives the update
        assertEquals(updatedQuarter, newTechGiant.getCurrentEvent(), "Newly added Tech Giant should receive event updates.");

        // Remove the Tech Giant and verify no further updates
        eventNotifier.removeObserver(newTechGiant);
        eventNotifier.updateEvent(Quarter.FourthQuarter);
        eventNotifier.notifyObservers();
        assertEquals(updatedQuarter, newTechGiant.getCurrentEvent(), "Removed Tech Giant should not receive further updates.");
    }
}
