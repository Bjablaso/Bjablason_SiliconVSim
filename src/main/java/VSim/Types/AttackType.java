package VSim.Types;


public enum AttackType {
    TALENT_DRAIN("Drains opponent's market share", 30),
    TRADE_SECRET_THEFT("Steals opponent's net income", 40),
    PRICE_UNDERCUTTING("Reduces opponent's revenue", 20);

    private final String description; // Description of the attack
    private final int damage;         // Base damage value of the attack

    // Constructor
    AttackType(String description, int damage) {
        this.description = description;
        this.damage = damage;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Getter for damage
    public int getDamage() {
        return damage;
    }
}
