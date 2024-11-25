import static org.junit.jupiter.api.Assertions.assertEquals;



import vsim.core.StartUp;

import vsim.types.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

;

/**
 * Tests market dynamics by applying event effects to different startups.
 */
public class MarketDynamic {
    private StartUp fintechStartup;
    private StartUp healthcareStartup;
    private StartUp smallStartup;
    private StartUp largeStartup;

    /**
     * Sets up the environment by initializing startups with specific attributes.
     */
    @BeforeEach
    void setup() {
        fintechStartup = new StartUp("FinTech Startup", StartupType.FINTECH,
                100000, 30000, 20.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP);

        healthcareStartup = new StartUp("Healthcare Startup", StartupType.HEALTHCARE,
                100000, 40000, 15.0, StartUpGrade.Small, StartupLevel.UNICORN);

        smallStartup = new StartUp("Small Startup", StartupType.SOCIAL_MEDIA,
                50000, 20000, 8.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP);

        largeStartup = new StartUp("Large Startup", StartupType.REAL_ESTATE,
                150000, 50000, 30.0, StartUpGrade.Large, StartupLevel.TECH_STAR);
    }

    /**
     * Tests the effect of an Economic Downturn event on a FinTech startup.
     */
    @Test
    @DisplayName("Test Apply Event Effect - Economic Downturn on FinTech")
    void testEconomicDownturnFintech() {
        Quarter event = Quarter.SecondQuarter; // Associated with Economic Downturn

        // Expected revenue after applying Economic Downturn
        double expectedRevenue = fintechStartup.getRevenue() -
                (fintechStartup.getRevenue() * EventType.ECONOMIC_DOWNTURN.getImpactFactor());

        System.out.println("Before: " + fintechStartup.getRevenue());

        // Apply event effect
        fintechStartup.applyEventEffect(event);

        System.out.println("After: " + fintechStartup.getRevenue());

        // Assert revenue change
        assertEquals(expectedRevenue, fintechStartup.getRevenue(), 0.001);
    }

    /**
     * Tests the effect of an Economic Downturn event on a Healthcare startup.
     */
    @Test
    @DisplayName("Test Apply Event Effect - Economic Downturn on Healthcare")
    void testEconomicDownturnHealthcare() {
        Quarter event = Quarter.SecondQuarter; // Associated with Economic Downturn

        // Expected revenue increase for Healthcare type
        double expectedRevenue = healthcareStartup.getRevenue() +
                (healthcareStartup.getRevenue() * EventType.ECONOMIC_DOWNTURN.getImpactFactor());

        // Apply event effect
        healthcareStartup.applyEventEffect(event);

        // Assert revenue change
        assertEquals(expectedRevenue, healthcareStartup.getRevenue(), 0.001);
    }

    /**
     * Tests the effect of a Regulatory Scrutiny event on a large startup.
     */
    @Test
    @DisplayName("Test Apply Event Effect - Regulatory Scrutiny on Large Startup")
    void testRegulatoryScrutinyLargeStartup() {
        Quarter event = Quarter.ThirdQuarter; // Associated with Regulatory Scrutiny

        // Expected market share after applying Regulatory Scrutiny
        double expectedMarketShare = largeStartup.getMarketShare() -
                (largeStartup.getMarketShare() * (largeStartup.getGrade().getMarketShare() / 100.0));

        // Apply event effect
        largeStartup.applyEventEffect(event);

        // Assert market share change
        assertEquals(expectedMarketShare, largeStartup.getMarketShare(), 0.001);
    }

    /**
     * Tests the effect of a Regulatory Scrutiny event on a small startup.
     */
    @Test
    @DisplayName("Test Apply Event Effect - Regulatory Scrutiny on Small Startup")
    void testRegulatoryScrutinySmallStartup() {
        Quarter event = Quarter.ThirdQuarter; // Associated with Regulatory Scrutiny

        // Expected market share after applying Regulatory Scrutiny
        double expectedMarketShare = smallStartup.getMarketShare() +
                (smallStartup.getGrade().getMarketShare() * 2.75 / 100.0);

        // Apply event effect
        smallStartup.applyEventEffect(event);

        // Assert market share change
        assertEquals(expectedMarketShare, smallStartup.getMarketShare(), 0.001);
    }
}
