package vsim.util;

import vsim.core.StartUp;
import vsim.types.AttackType;
import vsim.types.StartupType;


/**
 * Decorator class to provide battle advantages for startups.
 */
public class BattleAdvantageDecorator extends StartUp {
    private boolean isAdvantage;
    private final double attackBoost = 10.0;
    public AttackType currentAttack;
    private boolean chance;

    /**
     * Constructs a BattleAdvantageDecorator with the specified base startup.
     *
     * @param baseStartup The base startup to decorate.
     */
    public BattleAdvantageDecorator(StartUp baseStartup) {
        super(
                baseStartup.getName(),
                baseStartup.getType(),
                baseStartup.getNetIncome(),
                baseStartup.getRevenue(),
                baseStartup.getMarketShare(),
                baseStartup.getGrade(),
                baseStartup.getLevel()
        );
        this.isAdvantage = false;
        this.currentAttack = null;
        this.chance = false;
    }

    /**
     * Determines if this startup has an advantage over the target startup.
     *
     * @param target The target startup.
     * @return The current instance of BattleAdvantageDecorator.
     */
    public BattleAdvantageDecorator determinAdvantage(StartUp target) {
        if (getType().equals(StartupType.OPERATING_SYSTEMS)
                && target.getType().equals(StartupType.SOCIAL_MEDIA)) {
            this.isAdvantage = true;
            System.out.println(this.getName() + " is given a %" + attackBoost
                    + " attack boost as an advantage over " + target.getName());
            return this;
        } else if (getType().equals(StartupType.FINTECH)
                && target.getType().equals(StartupType.REAL_ESTATE)) {
            this.isAdvantage = true;
            System.out.println(this.getName() + " is given a %" + attackBoost
                    + " attack boost as an advantage over " + target.getName());
            return this;
        }

        return this;
    }

    /**
     * Applies a 10% increase to all attacks for this startup if it has an advantage.
     *
     * @return The current instance of BattleAdvantageDecorator.
     */
    public BattleAdvantageDecorator calculateDamageWithAdvantage() {
        for (AttackType currentAttack : this.getAttacks()) {
            double damage = currentAttack.getDamage()
                    + (currentAttack.getDamage() * (attackBoost / 100));
            currentAttack.setDamage(currentAttack, damage);
        }
        return this;
    }

    /**
     * Simulates whether an attack critically hits, misses, or proceeds as normal.
     *
     * @return true if the attack is successful (either normal or critical), false if it misses.
     */
    public boolean applyCriticalOrMiss() {
        double chance = Math.random(); // Generate a random value between 0.0 and 1.0

        if (chance < 0.1) { // 10% chance for a critical hit
            System.out.println("Critical hit! Damage is doubled.");
            for (AttackType attack : this.getAttacks()) {
                double criticalDamage = attack.getDamage() * 2; // Double the damage
                attack.setDamage(attack, criticalDamage);
            }
            return this.chance = true;
        } else if (chance > 0.9) { // 10% chance to miss
            System.out.println("Attack missed!");
            return this.chance = false; // Indicates a miss
        }

        System.out.println("Normal attack.");
        return this.chance = true; // Normal attack
    }


    /**
     * Checks if this startup has an advantage.
     *
     * @return true if the startup has an advantage, false otherwise.
     */
    public boolean isAdvantage() {
        return isAdvantage;
    }
}

