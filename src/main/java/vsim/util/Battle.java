package vsim.util;

import vsim.core.StartUp;

/**
 * Battle class create a landscape for Tech Giant startup to engage in battle.
 * Battle between start up only occur in the 4 quarter of the year.
 */
public class Battle {
    private StartUp startup1;
    private StartUp startup2;

    /**
     * Initialiazes start for battle.
     * @param startup1 -> First start up.
     * @param startup2 -> Second Startup.
     */
    public Battle(StartUp startup1, StartUp startup2) {
        this.startup1 = startup1;
        this.startup2 = startup2;
    }

    /**
     * Method conduct a startup battle.
     * @return the winning start up.
     */
    public StartUp startBattle() {
        // Implement the battle logic here
        // For example, compare attributes and determine the winner
        // Return the winning startup
        return null;
    }
}
