package VSim.Util;

import VSim.Core.StartUp;

/**
 * Battle class create a landscape for Tech Giant startup to engauge in battle when 4 quarter conmanses
 */
public class Battle {
    private StartUp startup1;
    private StartUp startup2;

    /**
     * Initialiazes start for battle
     * @param startup1
     * @param startup2
     */
    public Battle(StartUp startup1, StartUp startup2) {
        this.startup1 = startup1;
        this.startup2 = startup2;
    }

    /**
     * Start the Battle
     * @return
     */
    public StartUp startBattle() {
        // Implement the battle logic here
        // For example, compare attributes and determine the winner
        // Return the winning startup
        return null;
    }
}
