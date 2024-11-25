package VSim.Types;


public enum AttackType {
    TALENT_DRAIN("Drains opponent's market share", 30),
    TRADE_SECRET_THEFT("Steals opponent's net income", 40),
    PRICE_UNDERCUTTING("Reduces opponent's revenue", 20);

    private final String description; // Description of the attack
    private  double damage;         // Base damage value of the attack

    // Constructor
    AttackType(String description, double damage) {
        this.description = description;
        this.damage = damage;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Getter for damage
    public double getDamage() {
        return damage;
    }
    public void setDamage(AttackType attackType, double damagex) {
       switch (attackType){
           case TALENT_DRAIN:
               this.damage = damagex;
               break;
           case TRADE_SECRET_THEFT:
                   this.damage = damagex;
                   break;
           case PRICE_UNDERCUTTING:
               this.damage = damagex;
               break;
       }
    }
}
