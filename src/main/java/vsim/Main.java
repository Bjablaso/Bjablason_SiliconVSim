package vsim;

/**
 * Program entry to begin executing code.
 */
public class Main {
    /**
     * Main function to run code.
     * @param args can take in a string argument
     */
    public static void main(String[] args) {

        RunSimulation simulation = new RunSimulation();

        int count = 0;

        while (count < 2) {
            simulation.runSimulation();
            count++;
        }

    }
}

