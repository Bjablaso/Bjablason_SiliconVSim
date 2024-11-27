import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vsim.core.StartUp;
import vsim.core.TechGiant;
import vsim.types.Quarter;
import vsim.types.StartUpGrade;
import vsim.types.StartupLevel;
import vsim.types.StartupType;
import vsim.util.Battle;

import vsim.util.Event;

import static org.junit.jupiter.api.Assertions.*;

public class TestBattleGround {
    private TechGiant techGiantOne;
    private TechGiant techGiantTwo;
    private Event eventNotifier;

    /**
     * Sets up the test environment with two Tech Giants and their startups.
     */
    @BeforeEach
    void setup() {
        techGiantOne = new TechGiant("Tech Giant One", 1000000);
        techGiantTwo = new TechGiant("Tech Giant Two", 1000000);

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
     * Tests that the `Battle` class receives correct startup details.
     */
    @Test
    @DisplayName("Test Correct StartUp Data Passing to Battle")
    void testCorrectStartupDataPassing() {
        eventNotifier.updateEvent(Quarter.FourthQuarter);
        eventNotifier.notifyObservers();

        Battle<TechGiant> battle = new Battle<>(techGiantOne, techGiantTwo);
        StartUp startupOne = techGiantOne.getBattlePick();
        StartUp startupTwo = techGiantTwo.getBattlePick();

        assertNotNull(startupOne, "Startup from Tech Giant One should not be null.");
        assertNotNull(startupTwo, "Startup from Tech Giant Two should not be null.");
        assertEquals("Startup One", startupOne.getName(), "Startup One should match the expected name.");
        assertEquals("Startup Two", startupTwo.getName(), "Startup Two should match the expected name.");

        System.out.println("StartUp data passing test passed.");
    }

    /**
     * Tests that the event notification system updates Tech Giants correctly.
     */
    @Test
    @DisplayName("Test Event Notification System")
    void testEventNotificationSystem() {
        eventNotifier.updateEvent(Quarter.ThirdQuarter);
        eventNotifier.notifyObservers();

        assertEquals(Quarter.ThirdQuarter, techGiantOne.getCurrentEvent(), "Tech Giant One should have the updated event.");
        assertEquals(Quarter.ThirdQuarter, techGiantTwo.getCurrentEvent(), "Tech Giant Two should have the updated event.");

        System.out.println("Event notification system test passed.");
    }

    /**
     * Tests that a wildcard startup is correctly passed to the `Battle` class.
     */
    @Test
    @DisplayName("Test Wildcard Startup Passing to Battle")
    void testWildcardStartupPassing() {
        StartUp wildcardStartup = new StartUp("Wildcard Startup", StartupType.REAL_ESTATE, 8000,
                40000, 10.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP);

        Battle<Object> battle = new Battle<>(techGiantOne, wildcardStartup);

        assertNotNull(battle, "Battle instance should not be null.");
        assertEquals("Wildcard Startup", wildcardStartup.getName(), "Wildcard Startup name should match the expected value.");
        assertEquals(10.0, wildcardStartup.getMarketShare(), "Wildcard Startup market share should be correctly initialized.");

        System.out.println("Wildcard startup passing test passed.");
    }

    /**
     * Tests that post-battle state integrity is maintained after data passing.
     */
    @Test
    @DisplayName("Test Post-Battle State Integrity")
    void testPostBattleStateIntegrity() {
        eventNotifier.updateEvent(Quarter.FourthQuarter);
        eventNotifier.notifyObservers();

        Battle<TechGiant> battle = new Battle<>(techGiantOne, techGiantTwo);
        battle.battle(techGiantOne.getCurrentEvent());

        StartUp startupOne = techGiantOne.getBattlePick();
        StartUp startupTwo = techGiantTwo.getBattlePick();

        assertNotNull(startupOne, "Startup from Tech Giant One should not be null after battle.");
        assertNotNull(startupTwo, "Startup from Tech Giant Two should not be null after battle.");

        System.out.println("Post-battle state integrity test passed.");
    }


}
