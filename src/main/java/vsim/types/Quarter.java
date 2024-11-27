package vsim.types;

/**
 * Represents the quarters of a fiscal year, each associated with a specific event type.
 */
public enum Quarter {
    FirstQuarter("1", EventType.CORPORATE_TAX_CUTS),
    SecondQuarter("2", EventType.ECONOMIC_DOWNTURN),
    ThirdQuarter("3", EventType.REGULATORY_SCRUTINY),
    FourthQuarter("4", EventType.COMPETITIVE_BATTLES);

    private final String description;
    private final EventType type;

    /**
     * Constructs a Quarter enum with the specified description and associated event type.
     *
     * @param description A string representing the quarter (e.g., "1" for the first quarter).
     * @param type        The event type associated with this quarter.
     */
    Quarter(String description, EventType type) {
        this.description = description;
        this.type = type;
    }

    /**
     * Gets the description of the quarter.
     *
     * @return A string representation of the quarter (e.g., "1" for the first quarter).
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the event type associated with this quarter.
     *
     * @return The {@link EventType} associated with the quarter.
     */
    public EventType getType() {
        return type;
    }
}
