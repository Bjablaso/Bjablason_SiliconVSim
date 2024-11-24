package VSim;

import VSim.Types.EventType;

public class Main {
    public static void main(String[] args) {
        RunSimulation sim = new RunSimulation();
        boolean simend = false;
        int count = 0;
        EventType event = null;
        String name = null;
        double impactFactor = 0;

        while(!simend){

            event = sim.quaterlyEvent();
            name = sim.getQuarterName();
            impactFactor = event.getImpactFactor();
            System.out.println("This quater ( "+ name + ") "+ "Event is : " + event + "Impact factor : " + impactFactor);
            if(count == 8){
                simend = true;
            }
            count++;
        }
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