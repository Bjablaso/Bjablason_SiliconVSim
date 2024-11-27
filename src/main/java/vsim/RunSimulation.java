package vsim;

import java.util.List;
import java.util.Random;

import vsim.core.StartUp;
import vsim.core.TechGiant;
import vsim.core.TechGiantBuilder;
import vsim.types.Action;
import vsim.types.Quarter;
import vsim.types.StartUpGrade;
import vsim.types.StartupLevel;
import vsim.types.StartupType;
import vsim.util.Battle;
import vsim.util.Event;
import vsim.util.QuarterCycle;

/**
 * RunSimulation Class establishes all simulated events that could occur based on certain triggers.
 */
public class RunSimulation {

    private boolean simulationEnded = false;
    private QuarterCycle cycle;
    private String quaterName;
    private final Random random;

    List<StartUp> startups = List.of(
            new StartUp("FinBank", StartupType.FINTECH, 15000, 55000,
                    18.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP),
            new StartUp("CloudSpace", StartupType.OPERATING_SYSTEMS, 12000,
                    60000, 20.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP),
            new StartUp("HealthTrack", StartupType.HEALTHCARE, 17000, 75000,
                    22.0, StartUpGrade.Large, StartupLevel.TECH_STAR),
            new StartUp("MediaStream", StartupType.SOCIAL_MEDIA, 14000, 50000,
                    15.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP),
            new StartUp("HomeConnect", StartupType.REAL_ESTATE, 10000, 40000,
                    12.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP),
            new StartUp("AdFusion", StartupType.HEALTHCARE, 16000, 65000,
                    19.0, StartUpGrade.Large, StartupLevel.TECH_STAR),
            new StartUp("EcoPower", StartupType.FINTECH, 20000, 90000,
                    25.0, StartUpGrade.Large, StartupLevel.UNICORN),
            new StartUp("AutoAI", StartupType.REAL_ESTATE, 13000, 70000,
                    21.0, StartUpGrade.Large, StartupLevel.TECH_STAR),
            new StartUp("ShopEase", StartupType.SOCIAL_MEDIA, 11000, 45000,
                    14.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP),
            new StartUp("FitTech", StartupType.HEALTHCARE, 15000, 50000,
                    18.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP)
    );

    /**
     * Initializes the simulation.
     */
    public RunSimulation() {
        cycle = QuarterCycle.getSinglequarterInstance();
        random = new Random();
    }

    /**
     * Determines the current cycle and applies the cycle's event.
     *
     * @return The current cycle's {@link Quarter}.
     */
    public Quarter quaterlyEvent() {
        Quarter currentQuarterly = null;

        if (!simulationEnded) {
            currentQuarterly = cycle.getcurrentquaterCycle();
        }

        assert currentQuarterly != null;

        this.quaterName = currentQuarterly.getDescription();
        return currentQuarterly;
    }

    /**
     * Runs the simulation based on all initialized factors.
     */
    public void runSimulation() {

        System.out.println("Starting Simulation...");

        TechGiantBuilder appleTech = new TechGiantBuilder();
        appleTech.setName("Apple");
        appleTech.setFunds(800000);
        TechGiant apple = appleTech.build();

        TechGiantBuilder googleTech = new TechGiantBuilder();
        googleTech.setName("Google");
        googleTech.setFunds(700000);
        TechGiant google = googleTech.build();

        StartUp appleStartup = new StartUp("iCloud Services", StartupType.OPERATING_SYSTEMS,
                15000, 70000, 20.0, StartUpGrade.Large, StartupLevel.TECH_STAR);

        StartUp googleStartup = new StartUp("Google Ads", StartupType.SOCIAL_MEDIA, 13000,
                80000, 18.0, StartUpGrade.Large, StartupLevel.GARAGE_STARTUP);

        apple.addStartup(appleStartup);
        google.addStartup(googleStartup);

        do {
            if (simulationEnded) {
                System.out.println("Exited simulation...");
                break;
            }

            Quarter currentQuarterly = quaterlyEvent();
            Event eventTakingPlace = new Event(currentQuarterly);
            eventTakingPlace.addObserver(apple);
            eventTakingPlace.addObserver(google);

            eventTakingPlace.notifyObservers();

            Action appleAction = takeAction(Action.class);
            Action googleAction = takeAction(Action.class);

            StartUp applePick = randomStartUp();
            StartUp googlePick = randomStartUp();
            int i = 0;

            switch (currentQuarterly) {
                case FirstQuarter:
                    System.out.println("First quarter cycle of Corporate Tax Cuts:\n");
                    apple.takeAction(appleAction, applePick);
                    google.takeAction(googleAction, googlePick);
                    break;
                case SecondQuarter:
                    System.out.println("second quarter cycle of Economic Downturn:\n");
                    apple.getStartups().forEach(startup ->
                            startup.applyEventEffect(currentQuarterly));
                    google.getStartups().forEach(startup ->
                            startup.applyEventEffect(currentQuarterly));
                    break;
                case ThirdQuarter:
                    System.out.println("Third quarter cycle of Regulatory Scrutiny:\n");
                    apple.getStartups().forEach(startup ->
                            startup.applyEventEffect(currentQuarterly));
                    google.getStartups().forEach(startup ->
                            startup.applyEventEffect(currentQuarterly));
                    break;
                case FourthQuarter:
                    System.out.println("During the fourth quarter cycle Competitive Battles: \n");
                    StartUp applebattleStartup = apple.getStartups().get(i);
                    StartUp googlebattleStartup = google.getStartups().get(i);
                    apple.battlePick(applebattleStartup);
                    google.battlePick(googlebattleStartup);
                    Battle<TechGiant> techGiantBattle = new Battle<>(apple, google);
                    techGiantBattle.battle(currentQuarterly);
                    break;
                default:
                    System.out.println("Unknown event.");
                    break;
            }

            if (apple.isExitSim() || google.isExitSim()) {
                simulationEnded = true;
            }
            i++;

        } while (!simulationEnded);
    }

    /**
     * Gets the name of the current quarter.
     *
     * @return The name of the quarter.
     */
    public String getQuarterName() {
        return quaterName;
    }

    /**
     * Randomly selects an action to perform.
     *
     * @param enumClass The class of the action enum.
     * @param <T>       The type of the action enum.
     * @return A randomly selected action.
     */
    public <T extends Enum<T>> T takeAction(Class<T> enumClass) {

        T[] values = enumClass.getEnumConstants();
        return values[random.nextInt(values.length)];
    }

    /**
     * Randomly selects a startup from the list of startups.
     *
     * @return A randomly selected {@link StartUp}.
     */
    public StartUp randomStartUp() {

        return startups.get(random.nextInt(startups.size()));
    }
}
