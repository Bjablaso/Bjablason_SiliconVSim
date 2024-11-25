package vsim.core;

import java.util.ArrayList;
import java.util.List;

import vsim.siminterface.StartUpInterface;
import vsim.types.*;

/**
 * Represents a startup entity with various attributes and operations.
 */
public class StartUp implements StartUpInterface {
    private String name;
    private StartupType type; // e.g., SOCIAL_MEDIA, FINTECH
    private double netIncome; // Attack Power
    private double revenue; // Health
    private double marketShare; // Defense
    private StartUpGrade grade;
    private StartupLevel level; // e.g., GARAGE_STARTUP, TECH_STAR
    private int xp; // Experience Points
    private List<AttackType> attacks; // List of available attacks
    private final double marketsharReduction = 2.75;

    /**
     * Constructs a startup with the specified parameters.
     *
     * @param startupName The name of the startup.
     * @param type The type of startup.
     * @param netIncome The net income of the startup.
     * @param revenue The revenue of the startup.
     * @param marketShare The market share of the startup.
     * @param grade The grade of the startup.
     * @param levelType The level of the startup.
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
        this.xp = 0;
        this.attacks = new ArrayList<>();
    }

    /**
     * Adds an attack to the list of attacks.
     *
     * @param attack The attack type to add.
     */
    public void addAttack(AttackType attack) {
        attacks.add(attack);
    }

    public StartupType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getNetIncome() {
        return netIncome;
    }

    public double getRevenue() {
        return revenue;
    }

    public double getMarketShare() {
        return marketShare;
    }

    public StartUpGrade getGrade() {
        return grade;
    }

    public StartupLevel getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setMarketShare(double marketShare) {
        this.marketShare = marketShare;
    }

    public List<AttackType> getAttacks() {
        return attacks;
    }

    private double setInitialFunds(double funds) {
        return funds;
    }

    protected void updateRevenue(double funds) {
        this.revenue += funds;
    }

    protected void updateMarketShare(double share) {
        this.marketShare += share;
    }

    public void setFunds(double funds) {
        // Placeholder for future implementation
    }

    @Override
    public void levelUp() {
        // Placeholder for level-up logic
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
                    System.out.printf("For the %s quarter cycle, %s reduces %s %s revenue by: %f%%\n",
                            event.getDescription(), current.getDescription(),
                            name, type.toString(),
                            EventType.ECONOMIC_DOWNTURN.getImpactFactor() * 100);
                } else if (type == StartupType.HEALTHCARE) {
                    this.revenue += revenue * EventType.ECONOMIC_DOWNTURN.getImpactFactor();
                    System.out.printf("For the %s quarter cycle, %s increases %s %s revenue by: %f%%\n",
                            event.getDescription(), current.getDescription(),
                            name, type.toString(),
                            EventType.ECONOMIC_DOWNTURN.getImpactFactor() * 100);
                }
                break;

            case "Regulatory Scrutiny":
                if (marketShare > StartUpGrade.Large.getMarketShare()) {
                    marketShare -= marketShare * (grade.getMarketShare() / 100.0);
                    System.out.printf("For the %s quarter cycle, %s reduces %s market share by: %f%%\n",
                            event.getDescription(), current.getDescription(),
                            name, grade.getMarketShare() * 100);
                } else if (marketShare < StartUpGrade.Large.getMarketShare()) {
                    marketShare += marketsharReduction * (grade.getMarketShare() / 100.0);
                    System.out.printf("For the %s quarter cycle, %s increases %s market share by: %f%%\n",
                            event.getDescription(), current.getDescription(),
                            name, grade.getMarketShare() * 100);
                }
                break;

            default:
                System.out.println("No specific effect for this event.");
        }
    }

    @Override
    public void attack(StartUp target) {
        // Placeholder for attack logic
    }
}

