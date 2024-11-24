package VSim.Core;

import VSim.SimInterface.StartUpInterface;
import VSim.Types.AttackType;
import VSim.Types.EventType;
import VSim.Types.StartupLevel;
import VSim.Types.StartupType;

import java.util.ArrayList;
import java.util.List;

public class StartUp implements StartUpInterface {
    private StartupType type;       // e.g., SOCIAL_MEDIA, FINTECH
    private int netIncome;          // Attack Power
    private int revenue;            // Health
    private int marketShare;        // Defense
    private StartupLevel level;     // e.g., GARAGE_STARTUP, TECH_STAR
    private double funds;
    private int xp;                 // Experience Points
    private List<AttackType> attacks; // List of available attacks


    public StartUp(StartupType type, int netIncome, int revenue, int marketShare, double funds) {
        this.type = type;
        this.netIncome = netIncome;
        this.revenue = revenue;
        this.marketShare = marketShare;
        this.level = StartupLevel.GARAGE_STARTUP; // Default level
        this.xp = 0;
        this.attacks = new ArrayList<>();
        this.funds = setinitialFunds(funds);
    }

    public void addAttack(AttackType attack) {

    }

    public StartupType getType() {
        return type;
    }


    public int getNetIncome() {
        return netIncome;
    }

    public int getRevenue() {
        return revenue;
    }

    public int getMarketShare() {
        return marketShare;
    }

    public double getFunds() {
        return funds;
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

    public void setMarketShare(int marketShare) {
        this.marketShare = marketShare;
    }

    public List<AttackType> getAttacks() {
        return attacks;
    }

    private double setinitialFunds(double funds) {
       return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    @Override
    public void levelUp() {

    }

    @Override
    public void applyEventEffect(EventType event) {

    }

    @Override
    public void attack(StartUp target) {

    }
}
