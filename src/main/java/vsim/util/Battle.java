package vsim.util;

import vsim.core.StartUp;
import vsim.core.TechGiant;
import vsim.types.Quarter;

/**
 * Battle class creates a landscape for Tech Giant startups to engage in battle.
 * Battles between startups occur only in the 4th quarter of the year.
 */
public class Battle<T> {
    private T battlerOne;
    private T battlerTwo;

    /**
     * Initializes participants for the battle.
     *
     * @param battlerOne -> First participant.
     * @param battlerTwo -> Second participant.
     */
    public Battle(T battlerOne, T battlerTwo) {
        this.battlerOne = battlerOne;
        this.battlerTwo = battlerTwo;
    }

    /**
     * Method to conduct a battle between two participants.
     *
     * @param currentQuarterEvent The current quarter's event affecting the battle.
     * @return The winning startup, or null if no valid battle takes place.
     */
    public StartUp Battle(Quarter currentQuarterEvent) {
        System.out.println("Starting a new battle...");
        System.out.printf("Event for this quarter: %s\n", currentQuarterEvent.getDescription());

        if (battlerOne instanceof TechGiant && battlerTwo instanceof TechGiant) {
            TechGiant techGiantOne = (TechGiant) battlerOne;
            TechGiant techGiantTwo = (TechGiant) battlerTwo;

            StartUp startupOne = wrapAsDecorator(techGiantOne.getBattlePick());
            StartUp startupTwo = wrapAsDecorator(techGiantTwo.getBattlePick());

            if (startupOne != null && startupTwo != null) {
                StartUp winner = executeStartupBattle(
                        (BattleAdvantageDecorator) startupOne,
                        (BattleAdvantageDecorator) startupTwo,
                        currentQuarterEvent
                );

                // Ensure market share updates are only applied when there is a winner
                if (winner != null) {
                    double share = 5.0; // Fixed market share increase for the winner
                    winner.updateMarketShare(share);
                    System.out.printf("Winner's market share updated by %.2f%%\n", share);
                    return winner;
                }
            }
        } else if (battlerOne instanceof TechGiant && battlerTwo instanceof StartUp) {
            TechGiant techGiant = (TechGiant) battlerOne;
            StartUp wildStartup = wrapAsDecorator((StartUp) battlerTwo);

            StartUp techGiantStartup = wrapAsDecorator(techGiant.getBattlePick());

            if (techGiantStartup != null && wildStartup != null) {
                StartUp winner = executeStartupBattle(
                        (BattleAdvantageDecorator) techGiantStartup,
                        (BattleAdvantageDecorator) wildStartup,
                        currentQuarterEvent
                );

                // Ensure Tech Giant acquires wildcard startup if it wins
                if (winner == techGiantStartup) {
                    techGiant.acquireStartup(wildStartup);
                    System.out.printf("%s acquired wildcard startup %s.\n", techGiant.getName(), wildStartup.getName());
                }

                return winner;
            }
        }

        System.out.println("No valid battle could take place.");
        return null;
    }

    /**
     * Wraps a StartUp as a BattleAdvantageDecorator.
     *
     * @param startup The startup to wrap.
     * @return The decorated startup.
     */
    private BattleAdvantageDecorator wrapAsDecorator(StartUp startup) {
        if (startup instanceof BattleAdvantageDecorator) {
            return (BattleAdvantageDecorator) startup; // Already wrapped
        }
        return new BattleAdvantageDecorator(startup); // Wrap as decorator
    }

    /**
     * Executes a single round of battle between two startups and determines the winner.
     *
     * @param startupOne The first startup.
     * @param startupTwo The second startup.
     * @param currentQuarterEvent The current quarter's event.
     * @return The winning startup, or null if no one loses in this round.
     */
    private StartUp executeStartupBattle(BattleAdvantageDecorator startupOne,
                                         BattleAdvantageDecorator startupTwo,
                                         Quarter currentQuarterEvent) {
        // Apply quarter event effects
        startupOne.applyEventEffect(currentQuarterEvent);
        startupTwo.applyEventEffect(currentQuarterEvent);

        // Determine advantages
        startupOne.determinAdvantage(startupTwo).calculateDamageWithAdvantage();
        startupTwo.determinAdvantage(startupOne).calculateDamageWithAdvantage();

        System.out.printf("\nBattle begins between %s and %s!\n", startupOne.getName(), startupTwo.getName());

        // Startup One's turn to attack
        if (startupOne.applyCriticalOrMiss()) {
            startupTwo.updateRevenue(-startupOne.getNetIncome());
            System.out.printf("%s attacks %s, reducing revenue to %.2f\n",
                    startupOne.getName(), startupTwo.getName(), startupTwo.getRevenue());
        } else {
            System.out.printf("%s's attack missed!\n", startupOne.getName());
        }

        // Check if Startup Two has lost
        if (startupTwo.getRevenue() <= 0) {
            System.out.printf("%s wins the battle!\n", startupOne.getName());
            startupOne.updategetWindeterminer(1);
            startupTwo.updategetWindeterminer(-1);
            applyEffect(startupOne, startupTwo);
            return startupOne;
        }

        // Startup Two's turn to attack
        if (startupTwo.applyCriticalOrMiss()) {
            startupOne.updateRevenue(-startupTwo.getNetIncome());
            System.out.printf("%s attacks %s, reducing revenue to %.2f\n",
                    startupTwo.getName(), startupOne.getName(), startupOne.getRevenue());
        } else {
            System.out.printf("%s's attack missed!\n", startupTwo.getName());
        }

        // Check if Startup One has lost
        if (startupOne.getRevenue() <= 0) {
            System.out.printf("%s wins the battle!\n", startupTwo.getName());
            startupTwo.updategetWindeterminer(1);
            startupOne.updategetWindeterminer(-1);
            applyEffect(startupTwo, startupOne);
            return startupTwo;
        }

        // If neither startup loses, return null for no winner in this round
        System.out.println("No winner in this round of battle.");
        return null;
    }


    /**
     * Applies rewards and punishments based on the battle outcome.
     *
     * @param winner The winning startup.
     * @param loser  The losing startup.
     */
    public void applyEffect(StartUp winner, StartUp loser) {
        // Check if the winner and loser are not null before applying any effect
        if (winner != null && loser != null) {
            // Reward the winner
            winner.setXp(winner.getXp() + 100); // Increase experience
            winner.updateRevenue(5000); // Bonus revenue
            winner.updateMarketShare(5); // Increase market share
            System.out.printf("\nWinner: %s rewarded with XP, revenue, and market share!\n", winner.getName());
            System.out.printf("- XP increased to: %d\n", winner.getXp());
            System.out.printf("- Revenue increased to: %.2f\n", winner.getRevenue());
            System.out.printf("- Market share increased to: %.2f%%\n", winner.getMarketShare());

            // Penalize the loser
            loser.updateRevenue(-5000); // Deduct revenue
            loser.updateMarketShare(-5); // Decrease market share
            System.out.printf("\nLoser: %s penalized with loss of revenue and market share.\n", loser.getName());
            System.out.printf("- Revenue decreased to: %.2f\n", loser.getRevenue());
            System.out.printf("- Market share decreased to: %.2f%%\n", loser.getMarketShare());
        } else {
            System.out.println("Cannot apply effects as one or both startups are null.");
        }
    }

}

