package vsim.core;

import java.util.ArrayList;
import java.util.List;

import vsim.siminterface.EventObserver;
import vsim.types.Action;
import vsim.types.Quarter;

/**
 * Represents a Tech Giant entity in the simulation.
 * Tech Giants manage startups, funds, and respond to events during the simulation.
 */
public class TechGiant implements EventObserver {
    private final String name; // Name of the Tech Giant
    private Quarter currentEvent; // Current event affecting the Tech Giant
   private final List<StartUp> startups; // List of startups owned by the Tech Giant
    private double funds; // Available funds for investments and acquisitions
    private boolean exitSim; // Determines if the Tech Giant should exit the simulation
    private double investment; // Amount allocated for investment
    private StartUp battlePick;

    /**
     * Constructs a Tech Giant with a specified name and initial funds.
     *
     * @param name         The name of the Tech Giant.
     * @param initialFunds The initial funds available to the Tech Giant.
     */
    public TechGiant(String name, double initialFunds) {
        this.name = name;
        this.startups = new ArrayList<>();
        this.funds = initialFunds;
        this.currentEvent = null;
        this.investment = 0.0;
        this.exitSim = false;
        this.battlePick = null;
    }

    /**
     * Adds a startup to the Tech Giant's portfolio.
     *
     * @param startup The startup to add.
     */
    public void addStartup(StartUp startup) {
        if (funds > 0){
            System.out.println("Building " + startup.getName() + " with " + funds + " funds");
            System.out.println("Adding " + startup.getName() + " to"+this.name+" TechGiant\n");
            this.funds -= startup.getRevenue();
             startups.add(startup);
        }
        System.out.println("Startup cant be add to TechGiant :  "+this.name+ "due to insufficient funds");
    }

    /**
     * Removes a startup from the Tech Giant's portfolio.
     *
     * @param startup The startup to remove.
     */
    public void removeStartup(StartUp startup) {
        startups.remove(startup);
    }

    /**
     * Updates the Tech Giant's state in response to an event.
     *
     * @param eventName The event to process.
     */
    @Override
    public void update(Quarter eventName) {
        this.currentEvent = eventName;
        System.out.println(name + " received event notification:\t " + eventName + "\n");
    }

    /**
     * Adds to the current investment amount.
     *
     * @param investment The amount to add to the investment.
     */
    public void makeInvestment(double investment) {
        this.investment += investment;
    }

    /**
     * Gets the current event affecting the Tech Giant.
     *
     * @return The current event.
     */
    public Quarter getCurrentEvent() {
        return currentEvent;
    }

    /**
     * Gets the name of the Tech Giant.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of startups owned by the Tech Giant.
     *
     * @return The list of startups.
     */
    public List<StartUp> getStartups() {
        return startups;
    }

    /**
     * Gets the funds available to the Tech Giant.
     *
     * @return The available funds.
     */
    public double getFunds() {
        return funds;
    }

    public boolean isExitSim() {
        return exitSim;
    }

    /**
     * Invests a specified amount into all owned startups.
     *
     * @param investmentAmount The amount to invest.
     */
    public void invest(double investmentAmount) {

        if(startups.isEmpty()){
            System.out.println("No start up in the list");

        } else  if (funds > investmentAmount) {
            double growthMultiplier = 0.1;



            for (StartUp startup : startups) {
                this.funds -= investmentAmount;
                int marketShareGrowth = (int) (investmentAmount * growthMultiplier);
                int revenueGrowth = (int) (investmentAmount * growthMultiplier * 2);
                startup.updateRevenue(marketShareGrowth);
                startup.updateRevenue(revenueGrowth);
            }
            System.out.println(name + " made an investment. Current funds amount: " + funds);
        }
        System.out.println("TeachGiant "+this.name+ "Cant make investment due to insufficient funds");

    }

    /**
     * Acquires a specified startup, deducting its revenue from available funds.
     *
     * @param startup The startup to acquire.
     */
    public void acquireStartup(StartUp startup) {


            startups.add(startup);
            System.out.println(name + " acquired new startup " + startup.getName() + ". Current funds amount: " + funds + "\n");

    }

    /**
     * Enhances the financial attributes of all owned startups using the Tech Giant's funds.
     */
    public void financialEnhancement() {
        if (funds <= 0) {
            System.out.println(name + " has insufficient funds for financial enhancement.");
            return;
        }

        if(startups.isEmpty()){
            System.out.println("No start up in the list");

        }

        double revenueGrowthMultiplier = 0.2;
        double marketShareGrowthMultiplier = 0.1;
        double netIncomeGrowthMultiplier = 0.15;

        double investmentPerStartup = Math.min(funds / startups.size(), 10_000);

        for (StartUp startup : startups) {
            funds -= investmentPerStartup;

            int revenueIncrease = (int) (investmentPerStartup * revenueGrowthMultiplier);
            int marketShareIncrease = (int) (investmentPerStartup * marketShareGrowthMultiplier);

            startup.updateRevenue(revenueIncrease);
            startup.updateMarketShare(Math.min(startup.getMarketShare() + marketShareIncrease, 100));

            System.out.println("Financial enhancement applied to " + startup.getType() + ":");
            System.out.println("- Revenue increased by: $" + revenueIncrease);
            System.out.println("- Market share increased by: " + marketShareIncrease + "%");
        }

        System.out.println(name + " now has $" + funds + " remaining funds.");
    }


    /**
     * Executes an action involving a startup based on the provided Action type.
     *
     * @param action  The action to execute.
     * @param startup The startup involved in the action.
     */
    public void takeAction(Action action, StartUp startup) {
        switch (action) {
            case Invest:
                invest(investment);
                break;
            case Acquired:
                acquireStartup(startup);
                break;
            case BuildNewStartUp:
                addStartup(startup);
                break;
            case Purchase:
                financialEnhancement();
                break;
            default:
                throw new IllegalArgumentException(name + " action not recognized");
        }
    }

    public StartUp battlePick(StartUp startupPick) {
        if(!startups.contains(startupPick)) {
            throw new IllegalArgumentException(name + " has no startup " + startupPick.getName() + "\n");
        }

        return this.battlePick = startupPick;
    }

    /**
     * Checks if the Tech Giant should exit the simulation.
     *
     * @return True if the Tech Giant should exit; false otherwise.
     */
    public boolean exitSimulation() {
        if (startups.isEmpty()) {
            return this.exitSim = true;
        }
        return this.exitSim = false;
    }

    public void exit(){
        this.exitSim = true;
    }

    public StartUp getBattlePick() {
        return battlePick;
    }
}
