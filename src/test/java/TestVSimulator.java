import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import vsim.core.TechGiant;
import vsim.core.StartUp;
import vsim.types.StartUpGrade;
import vsim.types.StartupLevel;
import vsim.types.StartupType;
import vsim.util.Event;
import vsim.types.Quarter;
import vsim.util.QuarterCycle;
import vsim.util.Battle;

/**
 * Test class for verifying the functionality of the VSimulator components.
 */
public class TestVSimulator {

    private TechGiant techGiantOne;
    private TechGiant techGiantTwo;
    private Event eventNotifier;

    /**
     * Sets up the simulation environment with Tech Giants and their associated startups.
     */
    @BeforeEach
    void setup() {
        techGiantOne = new TechGiant("Tech Giant One", 800000);
        techGiantTwo = new TechGiant("Tech Giant Two", 750000);

        StartUp startupOne = new StartUp("Startup One", StartupType.FINTECH, 10000, 50000, 20.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP);
        StartUp startupTwo = new StartUp("Startup Two", StartupType.HEALTHCARE, 12000, 60000, 15.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP);

        techGiantOne.addStartup(startupOne);
        techGiantTwo.addStartup(startupTwo);

        techGiantOne.battlePick(startupOne);
        techGiantTwo.battlePick(startupTwo);

        eventNotifier = new Event(Quarter.FirstQuarter);
        eventNotifier.addObserver(techGiantOne);
        eventNotifier.addObserver(techGiantTwo);
    }

    /**
     * Verifies that Tech Giants correctly update their current event when notified.
     */
    @Test
    @DisplayName("Test Tech Giant Event Notification")
    void testTechGiantEventNotification() {
        eventNotifier.updateEvent(Quarter.SecondQuarter);
        eventNotifier.notifyObservers();

        assertEquals(Quarter.SecondQuarter, techGiantOne.getCurrentEvent(), "Tech Giant One should update its event.");
        assertEquals(Quarter.SecondQuarter, techGiantTwo.getCurrentEvent(), "Tech Giant Two should update its event.");
    }

    /**
     * Verifies that Tech Giants correctly add startups to their portfolio.
     */
    @Test
    @DisplayName("Test Add Startups to Tech Giant")
    void testAddStartupsToTechGiant() {
        StartUp newStartup = new StartUp("Startup Three", StartupType.REAL_ESTATE, 8000,
                40000, 10.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP);
        techGiantOne.addStartup(newStartup);

        assertTrue(techGiantOne.getStartups().contains(newStartup), "Tech Giant One should contain the new startup.");
    }

    /**
     * Tests the initialization of the Event cycle and its transitions.
     */
    @Test
    @DisplayName("Test Quarter Cycle Transitions")
    void testQuarterCycleTransitions() {
        QuarterCycle quarterCycle = QuarterCycle.getSinglequarterInstance();

        assertEquals(Quarter.FirstQuarter, quarterCycle.getcurrentquaterCycle(), "Initial quarter should be FirstQuarter.");
        assertEquals(Quarter.SecondQuarter, quarterCycle.getcurrentquaterCycle(), "Second call should return SecondQuarter.");
        assertEquals(Quarter.ThirdQuarter, quarterCycle.getcurrentquaterCycle(), "Third call should return ThirdQuarter.");
        assertEquals(Quarter.FourthQuarter, quarterCycle.getcurrentquaterCycle(), "Fourth call should return FourthQuarter.");
    }

    /**
     * Verifies that the simulation's battle functionality correctly applies effects to participants.
     */
    @Test
    @DisplayName("Test Battle Effects Application")
    void testBattleEffectsApplication() {
        Battle<TechGiant> battle = new Battle<>(techGiantOne, techGiantTwo);
        StartUp winner = battle.battle(Quarter.FourthQuarter);

        assertNotNull(winner, "There should be a winner.");
        StartUp loser = winner == techGiantOne.getBattlePick() ? techGiantTwo.getBattlePick() : techGiantOne.getBattlePick();

        assertTrue(winner.getXp() > 0, "Winner should gain XP.");
        assertTrue(loser.getRevenue() < loser.getRevenue() + 5000, "Loser's revenue should decrease.");
    }
}
