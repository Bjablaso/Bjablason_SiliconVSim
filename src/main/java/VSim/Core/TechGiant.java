package VSim.Core;

import VSim.SimInterface.EventObserver;

import java.util.ArrayList;
import java.util.List;

public class TechGiant implements EventObserver {
    private String name;
    private String currentEvent;
    private List<StartUp> startups;
    private double funds;

    public TechGiant(String name, double initialFunds) {
        this.name = name;
        this.startups = new ArrayList<>();
        this.funds = initialFunds;
        this.currentEvent = null;
    }

    public void addStartup(StartUp startup) {
        startups.add(startup);
    }

    public void removeStartup(StartUp startup) {
        startups.remove(startup);
    }

    @Override
    public void update(String eventName) {
        this.currentEvent = eventName;
        System.out.println(name + " received event notification:\t " + eventName);
    }

    public String getCurrentEvent() {
        return currentEvent;
    }

    public String getName() {
        return name;
    }

    public List<StartUp> getStartups() {
        return startups;
    }

    public double getFunds() {
        return funds;
    }

    public void invest(StartUp startup) {
        // Logic to invest in a startup's market share growth
        // Example: Increase startup's market share and decrease funds
    }

    public void acquireStartup(StartUp startup) {
        // Logic to add a startup to the portfolio after a successful battle
        // Example: Add startup to list and adjust funds accordingly
    }

    public void takeAction() {
        // Logic to execute quarterly actions (investment, acquisition, attack)
        // Example: Decide on an action based on strategy and available funds
    }

    public void exitSimulation() {
        // Logic to remove the Tech Giant from the simulation
        // Example: Clear startups list and set funds to zero
    }


}
