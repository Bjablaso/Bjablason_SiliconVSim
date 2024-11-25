package VSim;

import VSim.Types.EventType;
import VSim.Types.Quarter;
import VSim.Util.QuarterCycle;

/***
 * RunSimulator Class establishes All simulated Event that could occur base on certain triggers
 */
public class RunSimulation {
    private boolean SimulationEnded = false;
    private QuarterCycle cycle;
    private String quaterName;

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
          if(!SimulationEnded){
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
        do {
            // -> all events happen here

        }while(!SimulationEnded);

    }

    /**
     * End the simulation base on current event
     */
    public void endSimulation() {

    }

    /**
     * get the name of the current Quarter
     * @return Quarter name
     */
    public String getQuarterName() {
        return quaterName;
    }
}
