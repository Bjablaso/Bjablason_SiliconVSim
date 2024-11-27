package vsim.siminterface;

import vsim.types.Quarter;

/**
 * Represents the interface for a startup, defining essential behaviors
 * such as leveling up and applying event effects.
 */
public interface StartUpInterface {

    /**
     * Levels up the startup by increasing its level value.
     *
     * @param valueGain The amount to increase the startup's level value by.
     *                  This determines the progression of the startup to higher levels.
     */
    void levelUp(int valueGain);

    /**
     * Applies the effects of a given quarterly event to the startup.
     *
     * @param event The {@link Quarter} event to process, which can influence
     *              the startup's attributes such as revenue or market share
     *              based on the type of event.
     */
    void applyEventEffect(Quarter event);
}

