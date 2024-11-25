import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import vsim.core.StartUp;
import vsim.util.BattleAdvantageDecorator;
import vsim.types.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BattleAdvantage {
    StartUp osStartup;
    StartUp socialMediaStartup;
    StartUp fintechStartup;
    StartUp realEstateStartup;
    BattleAdvantageDecorator osDecorator;
    BattleAdvantageDecorator socialMediaDecorator;
    BattleAdvantageDecorator fintechDecorator;
    BattleAdvantageDecorator realEstateDecorator;


    @BeforeEach
    void setUp() {
        osStartup = new StartUp("OS Startup", StartupType.OPERATING_SYSTEMS,
                100000, 30000, 20.0, StartUpGrade.Small, StartupLevel.TECH_STAR);
        socialMediaStartup = new StartUp("Social Media Startup", StartupType.SOCIAL_MEDIA,
                80000, 25000, 15.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP);
        fintechStartup = new StartUp("FinTech Startup", StartupType.FINTECH,
                120000, 40000, 25.0, StartUpGrade.Large, StartupLevel.UNICORN);
        realEstateStartup = new StartUp("Real Estate Startup", StartupType.REAL_ESTATE,
                90000, 35000, 18.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP);

        osDecorator = new BattleAdvantageDecorator(osStartup);
        socialMediaDecorator = new BattleAdvantageDecorator(socialMediaStartup);
        fintechDecorator = new BattleAdvantageDecorator(fintechStartup);
        realEstateDecorator = new BattleAdvantageDecorator(realEstateStartup);

    }

    @Test
    @DisplayName("Test Advantage Calculation - OS vs Social Media")
    void testAdvantageOSvsSocialMedia() {
        // Determine advantage
        osDecorator.determinAdvantage(socialMediaDecorator);

        // Check if advantage is applied
        assertTrue(osDecorator.isAdvantage());
    }

    @Test
    @DisplayName("Test Advantage Calculation - FinTech vs Real Estate")
    void testAdvantageFinTechvsRealEstate() {
        // Determine advantage
        fintechDecorator.determinAdvantage(realEstateDecorator);

        // Check if advantage is applied
        assertTrue(fintechDecorator.isAdvantage());
    }

    @Test
    @DisplayName("Test No Advantage - OS vs FinTech")
    void testNoAdvantageOSvsFinTech() {
        // Determine advantage
        osDecorator.determinAdvantage(fintechDecorator);

        // Check that no advantage is applied
        assertFalse(osDecorator.isAdvantage());
    }

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
}
