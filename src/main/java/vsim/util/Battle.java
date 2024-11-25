package vsim.util;

import vsim.core.StartUp;
import vsim.core.TechGiant;
import vsim.types.Quarter;

/**
 * Battle class create a landscape for Tech Giant startup to engage in battle.
 * Battle between start up only occur in the 4 quarter of the year.
 */
public class Battle<T> {
    private T battlerOne;
    private T battlerTwo;


    /**
     * Initializes participants for the battle.
     * @param battlerOne -> First participant.
     * @param battlerTwo -> Second participant.
     */
    public Battle(T battlerOne, T battlerTwo) {
        this.battlerOne = battlerOne;
        this.battlerTwo = battlerTwo;
    }

    /**
     * Method to conduct a battle.
     * @return the winning participant.
     */
    public StartUp startBattle(Quarter currentquarterEvent) {
        // Implement the battle logic here
        // For example, compare attributes and determine the winner
        // Return the winning startup
        return null;
    }
}
