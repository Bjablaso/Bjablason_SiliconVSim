package vsim.types;

public enum Quarter {
    FirstQuarter("1", EventType.CORPORATE_TAX_CUTS),
    SecondQuarter("2", EventType.ECONOMIC_DOWNTURN),
    ThirdQuarter("3", EventType.REGULATORY_SCRUTINY),
    FourthQuarter("4", EventType.COMPETITIVE_BATTLES);

    private final String description;
    private final EventType type;

    Quarter(String description, EventType type) {
        this.description = description;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public EventType getType() {
        return type;
    }
}
