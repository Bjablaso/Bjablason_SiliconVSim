package VSim.Util;

import VSim.Types.Quarter;

/**
 * Create circular List to iterate through the different quarter until the simulation end.
 * this class uses Singleton design pattern to keep only one instance of the Quarterly Cycle throughout our program
 */
public class QuarterCycle {
    private Node head;
    private Node tail;
    private Node current;
    private static QuarterCycle singlequarterInstance;

    /**
     * create a new  circular linked list Base Quarter enum class.
     */
    public QuarterCycle() {
       Node mover = null;
        for(Quarter q : Quarter.values()) {
            Node newNode = new Node(q);

            if(head == null) {
                head = newNode;
                mover = head;
            }else {
                mover.next = newNode;
                mover = mover.next;
                tail = mover;
            }
        }

        if(tail != null) {
            tail.next = head;
        }
        current = head;
    }


    /**
     * This method return the Current Quarter
     * @return current Quarter
     */
    public Quarter getcurrentquaterCycle() {
       Quarter currentQuarter = current.quarter;
        current = current.next;
        return currentQuarter;
    }

    /**
     * This method return a single instance
     * @return new QuarterCycle
     */
    public static QuarterCycle getSinglequarterInstance() {
        if(singlequarterInstance == null) {
            singlequarterInstance = new QuarterCycle();
        }
        return singlequarterInstance;
    }

    /**
     * Node class that create node that will later be added to our circular linked list
     */
    class Node {
        Quarter quarter;
        Node next;

        /**
         * Initialize a new node
         * @param quarter
         */
        public Node(Quarter quarter) {
            this.quarter = quarter;
            this.next = null;
        }

        /**
         * Get a specific node in the list
         * @return Quarter
         */
        public Quarter getQuarter() {
            return quarter;
        }

        /**
         * Go to the next node
         * @return next
         */
        public Node getNext() {
            return next;
        }
    }

}
