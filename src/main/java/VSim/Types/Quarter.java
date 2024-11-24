package VSim.Types;

public enum Quarter {
    FirstQuater ("Q1", EventType.CORPORATE_TAX_CUTS),
    SecoundQuater("Q2", EventType.ECONOMIC_DOWNTURN),
    ThirdQuater("Q3", EventType.REGULATORY_SCRUTINY),
    FourthQuater("Q4", EventType.COMPETITIVE_BATTLES),
    ;

    private final String Description;
    private final EventType type;

    Quarter(String description, EventType type) {
        Description = description;
        this.type = type;
    }

    public String getDescription() {
        return Description;
    }

    public EventType getType() {
        return type;
    }
}
