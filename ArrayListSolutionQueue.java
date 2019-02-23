/**
Author: Connor Bos
Student number: 300011530
Course code: ITI1121
Lab section: A03
Lecture Section: A00
Assignment: 1
*/

import java.util.ArrayList;

public class ArrayListSolutionQueue implements SolutionQueue {


    /**
     * <b>queue</b> stores the references of the elements
     * currentluy in the queue
     */
    private ArrayList<Solution> queue;

    /**
     * Constructor, initializes <b>queue</b>
     */
    public ArrayListSolutionQueue() {
       this.queue = new ArrayList<Solution>();
    }

    /**
     * implementation of the method <b>enqueue</b>
     * from the interface <b>SolutionQueue</b>.
     * @param value
     *      The reference to the new element
     */
    public void enqueue(Solution value) {
       this.queue.add(value);
    }

    /**
     * implementation of the method <b>dequeue</b>
     * from the interface <b>SolutionQueue</b>.
     * @return
     *      The reference to removed Solution
     */
    public Solution dequeue() {
       Solution dequeued = this.queue.get(0);
       this.queue.remove(0);
       return dequeued;
    }

    /**
     * implementation of the method <b>isEmpty</b>
     * from the interface <b>SolutionQueue</b>.
     * @return
     *      true if the queue is empty
     */
    public boolean isEmpty() {
       return this.queue.isEmpty();
    }

}