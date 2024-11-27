package vsim.types;

/**
 * Represents the grade of a startup based on its size and market share.
 */
public enum StartUpGrade {
    Small("Small", 10.0),
    Large("Large", 25.0);

    private final String description; // A textual description of the startup grade.
    private final double marketShare; // The market share percentage associated with the grade.

    /**
     * Constructs a StartUpGrade enum with the specified description and market share.
     *
     * @param description A string representing the startup grade (e.g., "Small" or "Large").
     * @param marketShare The market share percentage associated with this grade.
     */
    StartUpGrade(String description, double marketShare) {
        this.description = description;
        this.marketShare = marketShare;
    }

    /**
     * Gets the description of the startup grade.
     *
     * @return A string representing the startup grade (e.g., "Small" or "Large").
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the market share percentage associated with this startup grade.
     *
     * @return The market share percentage as a double (e.g., 10.0 for Small).
     */
    public double getMarketShare() {
        return marketShare;
    }
}

