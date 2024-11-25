package vsim.types;

/**
 * Enum representing different types of events and their associated impact factors.
 * Each event has a description and an impact factor, which can be modified or reset to defaults.
 */
public enum EventType {
    ECONOMIC_DOWNTURN("Economic Downturn", 0.2),
    CORPORATE_TAX_CUTS("Corporate Tax Cuts", 0.1),
    REGULATORY_SCRUTINY("Regulatory Scrutiny", 0.15),
    COMPETITIVE_BATTLES("Competitive Battles", 0.05);

    private final String description;
    private double impactFactor;

    /**
     * Constructor for initializing an event type with its description and impact factor.
     *
     * @param description  the description of the event
     * @param impactFactor the impact factor of the event
     */
    EventType(String description, double impactFactor) {
        this.description = description;
        this.impactFactor = impactFactor;
    }

    /**
     * Gets the description of the event type.
     *
     * @return the description of the event
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the impact factor of the event type.
     *
     * @return the impact factor of the event
     */
    public double getImpactFactor() {
        return impactFactor;
    }

    /**
     * Sets a new impact factor for an event type based on its description.
     *
     * @param description  the description of the event to match
     * @param impactFactor the new impact factor to set
     */
    public void setImpactFactor(String description, double impactFactor) {
        for (EventType e : EventType.values()) {
            if (e.getDescription().equals(description)) {
                this.impactFactor = impactFactor;
            }
        }
    }

    /**
     * Resets the impact factor of the current event type to its default value.
     */
    public void resetImpactFactor(EventType typeOf) {
        switch (typeOf.getDescription()) {
            case "Economic Downturn":
                this.impactFactor = 0.2;
                break;
            case "Corporate Tax Cuts":
                this.impactFactor = 0.1;
                break;
            case "Regulatory Scrutiny":
                this.impactFactor = 0.15;
                break;
            case "Competitive Battles":
                this.impactFactor = 0.05;
                break;
            default :
                throw new IllegalArgumentException("Unknown event type: ");

        }
    }
}
