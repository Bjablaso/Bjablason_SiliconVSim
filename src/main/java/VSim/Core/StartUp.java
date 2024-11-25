package VSim.Core;

import VSim.SimInterface.StartUpInterface;
import VSim.Types.*;

import java.util.ArrayList;
import java.util.List;

public class StartUp implements StartUpInterface {
    private String name;
    private StartupType type;       // e.g., SOCIAL_MEDIA, FINTECH
    private double netIncome;          // Attack Power
    private double revenue;            // Health
    private double marketShare;        // Defense
    private StartUpGrade grade;
    private StartupLevel level;     // e.g., GARAGE_STARTUP, TECH_STAR
  //  private double funds;
    private int xp;                 // Experience Points
    private List<AttackType> attacks; // List of available attacks
    private final double marketsharReduction = 2.75;


    public StartUp(String startupName, StartupType type, double netIncome,
                   double revenue, double marketShare,StartUpGrade grade ,
                   StartupLevel levelType ) {
        this.name = startupName;
        this.type = type;
        this.netIncome = netIncome;
        this.revenue = revenue;
        this.marketShare = marketShare;
        this.grade = grade;
        this.level = levelType; // Default level
        this.xp = 0;
        this.attacks = new ArrayList<>();
       // this.funds = setinitialFunds(funds);
    }

    public void addAttack(AttackType attack) {

    }

    public StartupType getType() { return type; }
    public String getName() { return name; }
    public double getNetIncome() { return netIncome; }
    public double getRevenue() { return revenue; }
    public double getMarketShare() { return marketShare; }
    public StartUpGrade getGrade() { return grade; }
   // public double getFunds() { return funds; }
    public StartupLevel getLevel() { return level; }
    public int getXp() { return xp; }
    public void setXp(int xp) { this.xp = xp; }
    public void setMarketShare(int marketShare) { this.marketShare = marketShare; }
    public List<AttackType> getAttacks() { return attacks; }
    private double setinitialFunds(double funds) { return funds; }

    public void setFunds(double funds) {
     }

    @Override
    public void levelUp() {

    }

    /**
     * Calculate Quarterly cycle effect and apply changes tho our Startup base on current cycle
     * @param event take in an Quarter even and update appropriate field such as revenue and market share
     */
    @Override
    public void applyEventEffect(Quarter event) {
        EventType current = event.getType();

        switch (current.getDescription()){
            case "Economic Downturn":
                if( type == StartupType.FINTECH) {
                    // reduce by percentage
                    this.revenue = revenue - (revenue * EventType.ECONOMIC_DOWNTURN.getImpactFactor());
                    System.out.println( "For the "+ event.getDescription() + " quarter cycle. " + current.getDescription()+
                            "reduces "+ name + type.toString()+ " revenue by: %" + EventType.ECONOMIC_DOWNTURN.getImpactFactor()*100);
                }else if ( type == StartupType.HEALTHCARE){
                    this.revenue = revenue + (revenue * EventType.ECONOMIC_DOWNTURN.getImpactFactor());
                    System.out.println( "For the "+ event.getDescription() + " quarter cycle. " + current.getDescription()+
                            "increase "+ name + type.toString()+ " revenue by : %" + EventType.ECONOMIC_DOWNTURN.getImpactFactor()*100);
                }
                break;

            case "Regulatory Scrutiny":
                if(marketShare > StartUpGrade.Large.getMarketShare()){
                    marketShare = marketShare - (marketShare * (grade.getMarketShare()/100.0));
                    System.out.println("For the " + event.getDescription() + " quarter cycle " + current.getDescription() + "Startup " +
                            name + " market share reduces by : %" + grade.getMarketShare()*100 );
                }else if(marketShare < StartUpGrade.Large.getMarketShare()){
                    marketShare = marketShare + (marketsharReduction * (grade.getMarketShare()/100.0));
                    System.out.println("For the " + event.getDescription() + " quarter cycle " + current.getDescription() + "Startup " +
                            name + " market share increases by : %" + grade.getMarketShare()*100 );
                }
                break;

        }

    }

    @Override
    public void attack(StartUp target) {

    }
}
