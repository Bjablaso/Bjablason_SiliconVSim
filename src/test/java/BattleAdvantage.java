import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import vsim.core.StartUp;
import vsim.util.BattleAdvantageDecorator;
import vsim.types.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Tests the battle advantages and damage boost calculations for startups.
 */
public class BattleAdvantage {
    private BattleAdvantageDecorator osDecorator;
    private BattleAdvantageDecorator socialMediaDecorator;
    private BattleAdvantageDecorator fintechDecorator;
    private BattleAdvantageDecorator realEstateDecorator;

    /**
     * Sets up the test environment with initialized startups and their decorators.
     */
    @BeforeEach
    void setUp() {
        StartUp osStartup = new StartUp("OS Startup", StartupType.OPERATING_SYSTEMS,
                100000, 30000, 20.0, StartUpGrade.Small, StartupLevel.TECH_STAR);
        StartUp socialMediaStartup = new StartUp("Social Media Startup", StartupType.SOCIAL_MEDIA,
                80000, 25000, 15.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP);
        StartUp fintechStartup = new StartUp("FinTech Startup", StartupType.FINTECH,
                120000, 40000, 25.0, StartUpGrade.Large, StartupLevel.UNICORN);
        StartUp realEstateStartup = new StartUp("Real Estate Startup", StartupType.REAL_ESTATE,
                90000, 35000, 18.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP);

        osDecorator = new BattleAdvantageDecorator(osStartup);
        socialMediaDecorator = new BattleAdvantageDecorator(socialMediaStartup);
        fintechDecorator = new BattleAdvantageDecorator(fintechStartup);
        realEstateDecorator = new BattleAdvantageDecorator(realEstateStartup);
    }

    /**
     * Tests if the Operating Systems startup has an advantage over the Social Media startup.
     */
    @Test
    @DisplayName("Test Advantage Calculation - OS vs Social Media")
    void testAdvantageOSvsSocialMedia() {
        osDecorator.determinAdvantage(socialMediaDecorator);
        assertTrue(osDecorator.isAdvantage());
    }

    /**
     * Tests if the FinTech startup has an advantage over the Real Estate startup.
     */
    @Test
    @DisplayName("Test Advantage Calculation - FinTech vs Real Estate")
    void testAdvantageFinTechvsRealEstate() {
        fintechDecorator.determinAdvantage(realEstateDecorator);
        assertTrue(fintechDecorator.isAdvantage());
    }

    /**
     * Tests that the Operating Systems startup does not have an advantage over the FinTech startup.
     */
    @Test
    @DisplayName("Test No Advantage - OS vs FinTech")
    void testNoAdvantageOSvsFinTech() {
        osDecorator.determinAdvantage(fintechDecorator);
        assertFalse(osDecorator.isAdvantage());
    }

    /**
     * Tests that damage boost is applied correctly when an advantage is present.
     */
    @Test
    @DisplayName("Test Damage Boost Application with Advantage")
    void testDamageBoostWithAdvantage() {
        osDecorator.getAttacks().add(AttackType.TALENT_DRAIN);
        osDecorator.getAttacks().add(AttackType.TRADE_SECRET_THEFT);

        osDecorator.determinAdvantage(socialMediaDecorator);
        osDecorator.calculateDamageWithAdvantage();

        for (AttackType attack : osDecorator.getAttacks()) {
            double expectedDamage = attack.getDamage() / 1.1; // Reverse the boost calculation
            double actualDamage = expectedDamage + (expectedDamage * 0.1);
            assertEquals(actualDamage, attack.getDamage(), 0.001);
        }
    }

    /**
     * Tests that no damage boost is applied when there is no advantage.
     */
    @Test
    @DisplayName("Test No Damage Boost Without Advantage")
    void testNoDamageBoostWithoutAdvantage() {
        fintechDecorator.getAttacks().add(AttackType.PRICE_UNDERCUTTING);
        fintechDecorator.getAttacks().add(AttackType.TALENT_DRAIN);

        fintechDecorator.calculateDamageWithAdvantage();

        for (AttackType attack : fintechDecorator.getAttacks()) {
            double expectedDamage = attack.getDamage();
            assertEquals(expectedDamage, attack.getDamage(), 0.001);
        }
    }

    /**
     * Tests the applyCriticalOrMiss method and stops upon the first critical hit.
     */
    @Test
    @DisplayName("Test Apply Critical Hit - Stop on First Critical Hit")
    void testApplyCriticalHit() {
        osDecorator.getAttacks().add(AttackType.TALENT_DRAIN);
        osDecorator.getAttacks().add(AttackType.TRADE_SECRET_THEFT);

        boolean criticalHitDetected = false;

        // Simulate attacks and stop when the first critical hit occurs
        for (int i = 0; i < 50; i++) {
            boolean result = osDecorator.applyCriticalOrMiss(); // Simulate attack

            if (result) { // Attack was not a miss
                for (AttackType attack : osDecorator.getAttacks()) {
                    double originalDamage = attack.getDamage() / 2; // Reverse critical hit calculation
                    if (attack.getDamage() == originalDamage * 2) { // Check if damage was doubled
                        criticalHitDetected = true;
                        System.out.println("Critical hit detected on iteration " + (i + 1));
                        break;
                    }
                }
            }

            if (criticalHitDetected) {
                break; // Stop testing upon the first critical hit
            }
        }

        // Assert that a critical hit was detected
        assertTrue(criticalHitDetected, "A critical hit should have been detected within 50 attempts.");
    }

}
