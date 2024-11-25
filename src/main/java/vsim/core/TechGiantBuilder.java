package vsim.core;

import java.util.ArrayList;
import java.util.List;

public class TechGiantBuilder {
    private String name;
    private double funds;
    private List<StartUp> startups = new ArrayList<>();

    public TechGiantBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TechGiantBuilder setFunds(double funds) {
        this.funds = funds;
        return this;
    }

    public TechGiantBuilder addStartup(StartUp startup) {
        this.startups.add(startup);
        return this;
    }

    public TechGiant build() {
        TechGiant techGiant = new TechGiant(name, funds);
        for (StartUp startup : startups) {
            techGiant.addStartup(startup);
        }
        return techGiant;
    }
}
