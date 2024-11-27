package vsim.types;


/**
 * Represents the levels of a startup based on its growth stage and associated value.
 */
public enum StartupLevel {
    GARAGE_STARTUP(1),
    TECH_STAR(5),
    UNICORN(10);

    private final int levelVal; // The numerical value associated with the startup level.

    /**
     * Constructs a StartupLevel enum with the specified level value.
     *
     * @param levelVal The numerical value representing the startup level.
     */
    StartupLevel(int levelVal) {
        this.levelVal = levelVal;
    }

    /**
     * Gets the numerical value associated with the startup level.
     *
     * @return The level value as an integer (e.g., 1 for GARAGE_STARTUP).
     */
    public int getLevelVal() {
        return levelVal;
    }
}
