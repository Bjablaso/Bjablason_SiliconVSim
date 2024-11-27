package vsim.core;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vsim.siminterface.StartUpInterface;
import vsim.types.AttackType;
import vsim.types.EventType;
import vsim.types.Quarter;
import vsim.types.StartUpGrade;
import vsim.types.StartupLevel;
import vsim.types.StartupType;

/**
 * Represents a startup entity with various attributes and operations.
 */
public class StartUp implements StartUpInterface {
    private final String name;
    private final StartupType type; // e.g., SOCIAL_MEDIA, FINTECH
    private final double netIncome; // Attack Power
    private double revenue; // Health
    private double marketShare; // Defense
    private final StartUpGrade grade;
    private StartupLevel level; // e.g., GARAGE_STARTUP, TECH_STAR
    private int levelVal;
    private int xp; // Experience Points
    private final List<AttackType> attacks; // List of available attacks
    private int winnerIndicator; // 0 for initial, 1 if battle is won, -1 if battle is lost

    /**
     * Constructs a startup with the specified parameters.
     *
     * @param startupName The name of the startup.
     * @param type        The type of startup.
     * @param netIncome   The net income of the startup.
     * @param revenue     The revenue of the startup.
     * @param marketShare The market share of the startup.
     * @param grade       The grade of the startup.
     * @param levelType   The level of the startup.
     */
    public StartUp(String startupName, StartupType type, double netIncome,
                   double revenue, double marketShare, StartUpGrade grade,
                   StartupLevel levelType) {
        this.name = startupName;
        this.type = type;
        this.netIncome = netIncome;
        this.revenue = revenue;
        this.marketShare = marketShare;
        this.grade = grade;
        this.level = levelType; // Default level
        setLevelVal(level.getLevelVal());
        this.xp = 0;
        this.winnerIndicator = 0;
        this.attacks = new ArrayList<>();
        Collections.addAll(attacks, AttackType.values());
    }

    /**
     * Gets the type of the startup.
     *
     * @return The type of the startup.
     */
    public StartupType getType() {
        return type;
    }

    /**
     * Gets the name of the startup.
     *
     * @return The name of the startup.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the net income of the startup.
     *
     * @return The net income.
     */
    public double getNetIncome() {
        return netIncome;
    }

    /**
     * Gets the revenue of the startup.
     *
     * @return The revenue.
     */
    public double getRevenue() {
        return revenue;
    }

    /**
     * Gets the market share of the startup.
     *
     * @return The market share.
     */
    public double getMarketShare() {
        return marketShare;
    }

    /**
     * Gets the grade of the startup.
     *
     * @return The grade.
     */
    public StartUpGrade getGrade() {
        return grade;
    }

    /**
     * Gets the current level of the startup.
     *
     * @return The current level.
     */
    public StartupLevel getLevel() {
        return level;
    }

    /**
     * Updates the winner indicator of the startup.
     *
     * @param winValue The new winner indicator value.
     */
    public void updateWinnerIndicator(int winValue) {
        this.winnerIndicator = winValue;
    }

    /**
     * Sets the level value of the startup.
     *
     * @param levelVal The new level value.
     */
    public void setLevelVal(int levelVal) {
        this.levelVal = levelVal;
    }

    /**
     * Gets the experience points (XP) of the startup.
     *
     * @return The XP value.
     */
    public int getXp() {
        return xp;
    }

    /**
     * Sets the experience points (XP) of the startup.
     *
     * @param xp The new XP value.
     */
    public void setXp(int xp) {
        this.xp = xp;
    }

    /**
     * Gets the list of available attacks for the startup.
     *
     * @return The list of attacks.
     */
    public List<AttackType> getAttacks() {
        return attacks;
    }

    /**
     * Updates the revenue of the startup.
     *
     * @param funds The amount to add to the revenue.
     */
    public void updateRevenue(double funds) {
        this.revenue += funds;
    }

    /**
     * Updates the market share of the startup.
     *
     * @param share The amount to add to the market share.
     */
    public void updateMarketShare(double share) {
        this.marketShare += share;
    }

    /**
     * Levels up the startup based on the value gained.
     *
     * @param valueGain The amount to increase the level value by.
     * @throws IllegalCallerException If the level exceeds the maximum allowable level.
     */
    @Override
    public void levelUp(int valueGain) {
        this.levelVal += valueGain;

        switch (levelVal) {
            case 1:
                level = StartupLevel.GARAGE_STARTUP;
                break;
            case 5:
                level = StartupLevel.TECH_STAR;
                System.out.println(name + " StartUp achieved new level: " + levelVal);
                break;
            case 10:
                level = StartupLevel.UNICORN;
                System.out.println(name + " StartUp achieved new level: " + levelVal);
                break;
            default:
                throw new IllegalCallerException("Cannot exceed max level " + level);
        }
    }

    /**
     * Applies the effect of a given quarter's event to the startup.
     *
     * @param event The quarter event to process.
     */
    @Override
    public void applyEventEffect(Quarter event) {
        EventType current = event.getType();

        switch (current.getDescription()) {
            case "Economic Downturn":
                if (type == StartupType.FINTECH) {
                    this.revenue -= revenue * EventType.ECONOMIC_DOWNTURN.getImpactFactor();
                    System.out.printf("For the %s quarter cycle, %s reduces %s %s revenue by: %f%%%n",
                            event.getDescription(), current.getDescription(),
                            name, type,
                            EventType.ECONOMIC_DOWNTURN.getImpactFactor() * 100);
                } else if (type == StartupType.HEALTHCARE) {
                    this.revenue += revenue * EventType.ECONOMIC_DOWNTURN.getImpactFactor();
                    System.out.printf("For the %s quarter cycle, %s increases %s %s revenue by: %f%%%n",
                            event.getDescription(), current.getDescription(),
                            name, type,
                            EventType.ECONOMIC_DOWNTURN.getImpactFactor() * 100);
                }
                break;

            case "Regulatory Scrutiny":
                if (marketShare > StartUpGrade.Large.getMarketShare()) {
                    marketShare -= marketShare * (grade.getMarketShare() / 100.0);
                    System.out.printf("For the %s quarter cycle, %s reduces %s market share by: %f%%%n",
                            event.getDescription(), current.getDescription(),
                            name, grade.getMarketShare() * 100);
                } else if (marketShare < StartUpGrade.Large.getMarketShare()) {
                    double marketshareReduction = 2.75;
                    marketShare += marketshareReduction * (grade.getMarketShare() / 100.0);
                    System.out.printf("For the %s quarter cycle, %s increases %s market share by: %f%%%n",
                            event.getDescription(), current.getDescription(),
                            name, grade.getMarketShare() * 100);
                }
                break;

            default:
                System.out.println("No specific effect for this event.");
        }
    }
}
