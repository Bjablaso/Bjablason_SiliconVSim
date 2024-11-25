package vsim.types;

/**
 * Enum representing different types of attacks and their associated properties.
 * Each attack type has a description and a damage value, which can be retrieved or modified.
 */
public enum AttackType {
    TALENT_DRAIN("Drains opponent's market share", 30),
    TRADE_SECRET_THEFT("Steals opponent's net income", 40),
    PRICE_UNDERCUTTING("Reduces opponent's revenue", 20);

    private final String description; // Description of the attack
    private  double damage;         // Base damage value of the attack

    /**
     * Constructor to initialize an attack type with its description and damage value.
     *
     * @param description the description of the attack
     * @param damage the base damage value of the attack
     */
    AttackType(String description, double damage) {
        this.description = description;
        this.damage = damage;
    }

    /**
     * Gets the description of the attack type.
     *
     * @return the description of the attack
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the damage value of the attack type.
     *
     * @return the damage value of the attack
     */
    public double getDamage() {
        return damage;
    }

    /**
     * Sets a new damage value for a specific attack type.
     *
     * @param attackType the attack type whose damage value is to be updated
     * @param damagex    the new damage value to set
     */
    public void setDamage(AttackType attackType, double damagex) {
        switch (attackType) {
            case TALENT_DRAIN:
                this.damage = damagex;
                break;
            case TRADE_SECRET_THEFT:
                this.damage = damagex;
                break;
            case PRICE_UNDERCUTTING:
                this.damage = damagex;
                break;
            default:
                throw new IllegalArgumentException("Unknown attack type: ");
        }
    }
}
