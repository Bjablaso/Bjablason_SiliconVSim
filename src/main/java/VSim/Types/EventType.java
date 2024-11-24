package VSim.Types;

public enum EventType {
    ECONOMIC_DOWNTURN("Economic Downturn", 0.2),
    CORPORATE_TAX_CUTS("Corporate Tax Cuts", 0.1),
    REGULATORY_SCRUTINY("Regulatory Scrutiny", 0.15),
    COMPETITIVE_BATTLES("Competitive Battles", 0.05);

    private final String description;
    private double impactFactor;

    EventType(String description, double impactFactor) {
        this.description = description;
        this.impactFactor = impactFactor;
    }

    public String getDescription() {
        return description;
    }


    public double getImpactFactor() {
        return impactFactor;
    }


    public void setImpactFactor(String discription, double impactFactor) {
        for (EventType e : EventType.values()) {
            if (e.getDescription().equals(discription)) {
                this.impactFactor = impactFactor;
            }
        }
    }

    public void resetImpactFactor() {
        switch (this) {
            case ECONOMIC_DOWNTURN:
                this.impactFactor = 0.2;
                break;
            case CORPORATE_TAX_CUTS:
                this.impactFactor = 0.1;
                break;
            case REGULATORY_SCRUTINY:
                this.impactFactor = 0.15;
                break;
            case COMPETITIVE_BATTLES:
                this.impactFactor = 0.05;
                break;
        }
    }
}
