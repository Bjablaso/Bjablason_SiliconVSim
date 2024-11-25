package VSim.Types;

public enum StartUpGrade {
   Small("Small", 10.0),

    Large("Large", 25.0);

   private final String description;
   private double marketShare;
    StartUpGrade(String description, double marketShare) {
        this.description = description;
        this.marketShare = marketShare;
    }

    public String getDescription() {
        return description;
    }

    public double getMarketShare() {
        return marketShare;
    }
}
