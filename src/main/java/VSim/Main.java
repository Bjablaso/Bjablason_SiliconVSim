package VSim;

import VSim.Core.StartUp;
import VSim.Core.TechGiant;
import VSim.SimInterface.EventObserver;
import VSim.Types.EventType;
import VSim.Types.StartupLevel;
import VSim.Types.StartupType;
import VSim.Util.Event;

public class Main {
    public static void main(String[] args) {


    }
}
/**
 * Class names should follow Pascal case (also known as UpperCamelCase).
 * Example: MyClass, DataProcessor, UserProfile.
 *
 *  Use camel case (lowerCamelCase) for method names.
 * Example: calculateSum, getUserDetails, processOrder.
 * Use Verbs or Verb Phrases: Method names should start with a verb to indicate an action.
 * Examples:
 * getName (retrieving a value)
 * setPrice (modifying a value)
 * calculateTotal (performing a computation)
 *
 * Use camel case (lowerCamelCase) for variable names.
 * Example: userName, accountBalance, itemCount.
 */


/**
 * test case
 *  RunSimulation sim = new RunSimulation();
 *         boolean simend = false;
 *         int count = 0;
 *         EventType event = null;
 *         String name = null;
 *         double impactFactor = 0;
 *
 *         while(!simend){
 *
 *             event = sim.quaterlyEvent();
 *             name = sim.getQuarterName();
 *             impactFactor = event.getImpactFactor();
 *             System.out.println("This quater ( "+ name + ") "+ "Event is : " + event + "Impact factor : " + impactFactor);
 *             if(count == 8){
 *                 simend = true;
 *             }
 *             count++;
 *         }
 */

/**
 * test case
 *  StartUp operatingSystem = new StartUp(StartupType.OPERATING_SYSTEMS, 50000,
 *                 25000, 33, 60000, StartupLevel.TECH_STAR);
 *         TechGiant apple = new TechGiant("Apple", 100000);
 *         apple.addStartup(operatingSystem);
 *
 *         RunSimulation runSimulation = new RunSimulation();
 *         EventType forthisMonth = runSimulation.quaterlyEvent();
 *         String events = forthisMonth.getDescription().toString();
 *         Event thisMonthEvent = new Event(events);
 *         thisMonthEvent.addObserver(apple);
 *         thisMonthEvent.notifyObservers();
 *         String current = apple.getCurrentEvent();
 *         System.out.println(current);
 */