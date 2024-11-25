
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
     * Tests building a TechGiant with the builder pattern.
     */
    @Test
    @DisplayName("Test TechGiant Builder - Build TechGiant with Startups")
    void testBuildTechGiant() {
        TechGiant techGiant = builder
                .setName("Tech Innovators")
                .setFunds(500000)
                .addStartup(startup1)
                .addStartup(startup2)
                .build();

        assertEquals("Tech Innovators", techGiant.getName(), "TechGiant name should match.");
        assertEquals(500000, techGiant.getFunds(), 0.01, "TechGiant funds should match.");
        assertEquals(2, techGiant.getStartups().size(), "TechGiant should have 2 startups.");
        assertTrue(techGiant.getStartups().contains(startup1), "TechGiant should contain startup1.");
        assertTrue(techGiant.getStartups().contains(startup2), "TechGiant should contain startup2.");
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
     * Tests the invest method in TechGiant.
     */
    @Test
    @DisplayName("Test TechGiant - Invest in Startups")
    void testInvestInStartups() {
        TechGiant techGiant = new TechGiant("Tech Visionaries", 100000);
        techGiant.addStartup(startup1);

        double initialFunds = techGiant.getFunds();
        double investmentAmount = 20000;
        techGiant.invest(investmentAmount);

        assertTrue(techGiant.getFunds() < initialFunds, "Funds should decrease after investment.");
        List<StartUp> startups = techGiant.getStartups();
        assertEquals(1, startups.size(), "TechGiant should still have 1 startup.");
        assertTrue(startups.get(0).getRevenue() > startup1.getRevenue(),
                "Startup revenue should increase after investment.");
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

    /**
     * Tests the exitSimulation method in TechGiant.
     */
    @Test
    @DisplayName("Test TechGiant - Exit Simulation")
    void testExitSimulation() {
        TechGiant techGiant = new TechGiant("Tech Survivors", 0);
        assertTrue(techGiant.exitSimulation(), "TechGiant should exit simulation with zero funds and no startups.");

        techGiant.addStartup(startup1);
        assertFalse(techGiant.exitSimulation(), "TechGiant should not exit simulation with startups present.");
    }
}
