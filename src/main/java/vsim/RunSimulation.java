package vsim;

import vsim.core.StartUp;
import vsim.core.TechGiant;
import vsim.core.TechGiantBuilder;
import vsim.types.*;
import vsim.util.Battle;
import vsim.util.Event;
import vsim.util.QuarterCycle;

import java.util.List;
import java.util.Random;

/***
 * RunSimulator Class establishes All simulated Event that could occur base on certain triggers
 */
public class RunSimulation {
    private boolean simulationEnded = false;
    private QuarterCycle cycle;
    private String quaterName;
    List<StartUp> startups = List.of(
            new StartUp("FinBank", StartupType.FINTECH, 15000, 55000, 18.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP),
            new StartUp("CloudSpace", StartupType.OPERATING_SYSTEMS, 12000, 60000, 20.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP),
            new StartUp("HealthTrack", StartupType.HEALTHCARE, 17000, 75000, 22.0, StartUpGrade.Large, StartupLevel.TECH_STAR),
            new StartUp("MediaStream", StartupType.SOCIAL_MEDIA, 14000, 50000, 15.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP),
            new StartUp("HomeConnect", StartupType.REAL_ESTATE, 10000, 40000, 12.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP),
            new StartUp("AdFusion", StartupType.HEALTHCARE, 16000, 65000, 19.0, StartUpGrade.Large, StartupLevel.TECH_STAR),
            new StartUp("EcoPower", StartupType.FINTECH, 20000, 90000, 25.0, StartUpGrade.Large, StartupLevel.UNICORN),
            new StartUp("AutoAI", StartupType.REAL_ESTATE, 13000, 70000, 21.0, StartUpGrade.Large, StartupLevel.TECH_STAR),
            new StartUp("ShopEase", StartupType.SOCIAL_MEDIA, 11000, 45000, 14.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP),
            new StartUp("FitTech", StartupType.HEALTHCARE, 15000, 50000, 18.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP),
            new StartUp("DataHive", StartupType.SOCIAL_MEDIA, 14000, 48000, 17.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP),
            new StartUp("SolarNow", StartupType.OPERATING_SYSTEMS, 22000, 85000, 28.0, StartUpGrade.Large, StartupLevel.UNICORN),
            new StartUp("RideSharePro", StartupType.REAL_ESTATE, 18000, 65000, 23.0, StartUpGrade.Large, StartupLevel.TECH_STAR),
            new StartUp("MarketFlow", StartupType.FINTECH, 12000, 60000, 15.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP),
            new StartUp("AgriTech", StartupType.REAL_ESTATE, 10000, 40000, 12.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP),
            new StartUp("EduLearn", StartupType.OPERATING_SYSTEMS, 11000, 42000, 13.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP),
            new StartUp("CodeBase", StartupType.FINTECH, 15000, 55000, 16.0, StartUpGrade.Small, StartupLevel.GARAGE_STARTUP),
            new StartUp("AutoSense", StartupType.REAL_ESTATE, 17000, 70000, 21.0, StartUpGrade.Large, StartupLevel.TECH_STAR),
            new StartUp("VirtualRealms", StartupType.FINTECH, 19000, 85000, 24.0, StartUpGrade.Large, StartupLevel.UNICORN)
    );

    /**
     * Initializes the simulation
     */
    public RunSimulation() {

        cycle = cycle.getSinglequarterInstance();

    }

    /**
     * Determine the Current Cycle to apply the cycle Event.
     * @return current cycle EventTye
     */
    public Quarter quaterlyEvent(){
        EventType currentEvent = null;
        Quarter currentQuaterly = null;
          if(!simulationEnded){
              currentQuaterly = cycle.getcurrentquaterCycle();
          }

        assert currentQuaterly != null;

        this.quaterName = currentQuaterly.getDescription();
        return currentQuaterly;
    }

    /**
     *  Run the simulation base on all initialize factors
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

       // StartUp appleStartupOne = new StartUp("Apple Pay", StartupType.FINTECH, 12000, 60000, 15.0,
              //  StartUpGrade.Small, StartupLevel.GARAGE_STARTUP);
        StartUp appleStartupTwo = new StartUp("iCloud Services", StartupType.OPERATING_SYSTEMS, 15000,
                70000, 20.0, StartUpGrade.Large, StartupLevel.TECH_STAR);

        StartUp googleStartupOne = new StartUp("Google Ads", StartupType.SOCIAL_MEDIA, 13000, 80000,
                18.0, StartUpGrade.Large, StartupLevel.GARAGE_STARTUP);
      //  StartUp googleStartupTwo = new StartUp("YouTube Premium", StartupType.REAL_ESTATE, 14000, 80000,
            //    22.0, StartUpGrade.Large, StartupLevel.UNICORN);


       // apple.addStartup(appleStartupOne);
        apple.addStartup(appleStartupTwo);

        google.addStartup(googleStartupOne);
      //  google.addStartup(googleStartupTwo);



        do {
            if (simulationEnded){
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
                case FirstQuarter :

                    System.out.println("During the first quarter cycle of Corporate Tax Cuts : \n");
                    apple.takeAction(appleAction, applePick);
                    apple.takeAction(googleAction, googlePick);
                    break;
                case SecondQuarter :
                    System.out.println("During the second quarter cycle of Economic Downturn: \n");
                    apple.getStartups().forEach(startup -> startup.applyEventEffect(currentQuarterly));
                    apple.getStartups().forEach(startUp -> startUp.applyEventEffect(currentQuarterly));
                    break;
                case ThirdQuarter :
                    System.out.println("During the third quarter cycle Regulatory Scrutiny: \n");
                    apple.getStartups().forEach(startup -> startup.applyEventEffect(currentQuarterly));
                    apple.getStartups().forEach(startUp -> startUp.applyEventEffect(currentQuarterly));
                    break;
                case FourthQuarter :
                    System.out.println("During the fourth quarter cycle Competitive Battles: \n");
                    StartUp applebattleStartup = apple.getStartups().get(i);
                    StartUp googlebattleStartup = google.getStartups().get(i);
                    apple.BattlePic(applebattleStartup);
                    google.BattlePic(googlebattleStartup);
                    Battle<TechGiant> techGiantBattle = new Battle<>(apple, google);
                    techGiantBattle.Battle(currentQuarterly);
                    break;

            }

            if(apple.isExitSim()|| google.isExitSim()){
                simulationEnded = true;
            }
            i++;
            // -> check simulation condition

        }while(!simulationEnded);

    }



    /**
     * get the name of the current Quarter
     * @return Quarter name
     */
    public String getQuarterName() {
        return quaterName;
    }


    public <T extends Enum<T>> T takeAction(Class<T> enumClass) {
        Random randomAction = new Random();
        T[] values = enumClass.getEnumConstants();
        return values[randomAction.nextInt(values.length)];
    }

    public StartUp randomStartUp(){
        Random random = new Random();
        StartUp randPick = startups.get(random.nextInt(startups.size()));
        return randPick;
    }
}
