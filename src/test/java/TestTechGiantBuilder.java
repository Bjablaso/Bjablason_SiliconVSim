
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import vsim.core.*;
import vsim.types.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Test class for TechGiantBuilder and TechGiant classes.
 */
public class TestTechGiantBuilder {

    private TechGiantBuilder builder;
    private StartUp startup1;
    private StartUp startup2;

    /**
     * Sets up the test environment with a builder and example startups.
     */
    @BeforeEach
    void setup() {
        builder = new TechGiantBuilder();
        startup1 = new StartUp("FinTech Startup", StartupType.FINTECH,
                100000, 30000, 20.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP);
        startup2 = new StartUp("Healthcare Startup", StartupType.HEALTHCARE,
                100000, 40000, 15.0, StartUpGrade.Small, StartupLevel.UNICORN);
    }


    /**
     * Tests adding a startup to a TechGiant using the builder.
     */
    @Test
    @DisplayName("Test TechGiant Builder - Add Single Startup")
    void testAddStartupToTechGiant() {
        TechGiant techGiant = builder
                .setName("Tech Pioneers")
                .setFunds(300000)
                .addStartup(startup1)
                .build();

        assertEquals(1, techGiant.getStartups().size(), "TechGiant should have 1 startup.");
        assertEquals(startup1, techGiant.getStartups().get(0), "Startup1 should match the added startup.");
    }

    /**
     * Tests the functionality of adding and removing startups in TechGiant.
     */
    @Test
    @DisplayName("Test TechGiant - Add and Remove Startups")
    void testAddAndRemoveStartups() {
        TechGiant techGiant = new TechGiant("Tech Masters", 200000);
        techGiant.addStartup(startup1);
        techGiant.addStartup(startup2);

        assertEquals(2, techGiant.getStartups().size(), "TechGiant should have 2 startups initially.");

        techGiant.removeStartup(startup1);
        assertEquals(1, techGiant.getStartups().size(), "TechGiant should have 1 startup after removal.");
        assertFalse(techGiant.getStartups().contains(startup1), "Startup1 should be removed.");
    }


    /**
     * Tests the financialEnhancement method in TechGiant.
     */
    @Test
    @DisplayName("Test TechGiant - Financial Enhancement")
    void testFinancialEnhancement() {
        TechGiant techGiant = new TechGiant("Tech Leaders", 100000);
        techGiant.addStartup(startup1);

        techGiant.financialEnhancement();

        assertTrue(techGiant.getFunds() < 100000, "Funds should decrease after financial enhancement.");
        assertTrue(startup1.getMarketShare() > 20.0, "Startup market share should increase.");
        assertTrue(startup1.getRevenue() > 30000, "Startup revenue should increase.");
    }


}
