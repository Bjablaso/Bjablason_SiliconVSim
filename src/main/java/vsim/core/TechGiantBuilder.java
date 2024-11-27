package vsim.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder class for creating instances of {@link TechGiant}.
 * Allows for incremental construction of a TechGiant object with customizable properties.
 */
public class TechGiantBuilder {
    private String name;
    private double funds;
    private List<StartUp> startups = new ArrayList<>();

    /**
     * Sets the name of the tech giant.
     *
     * @param name the name to set
     * @return the current instance of the builder for method chaining
     */
    public TechGiantBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the funds available to the tech giant.
     *
     * @param funds the amount of funds to set
     * @return the current instance of the builder for method chaining
     */
    public TechGiantBuilder setFunds(double funds) {
        this.funds = funds;
        return this;
    }

    /**
     * Adds a startup to the list of startups associated with the tech giant.
     *
     * @param startup the startup to add
     * @return the current instance of the builder for method chaining
     */
    public TechGiantBuilder addStartup(StartUp startup) {
        this.startups.add(startup);
        return this;
    }

    /**
     * Builds and returns a new {@link TechGiant} instance based on the provided properties.
     *
     * @return a new instance of {@link TechGiant}
     */
    public TechGiant build() {
        System.out.println("Starting " + name + " with funds: " + funds + "\n");
        TechGiant techGiant = new TechGiant(name, funds);
        for (StartUp startup : startups) {
            techGiant.addStartup(startup);
        }
        return techGiant;
    }
}
