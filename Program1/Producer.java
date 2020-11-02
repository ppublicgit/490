/*
 * Producer class for producing nodes for the heap
 */
public class Producer implements Runnable {
    private MinHeap m_heap; // shared heap
    private String m_name; // name of producer
    private int m_numProcesses; // number of processes to produce per production cycle
    private int m_numProductions; // number of production cycles
    private RandomInt randomPriority = new RandomInt(0, 10); // random int generator for priorities
    private RandomInt randomIdle = new RandomInt(1000, 2000); // random int generator for idle time between productions
    private RandomInt randomTimeSlice = new RandomInt(100, 500); // random int generator for runtimes for nodes
    private SharedBool m_productionFinished; // shared bool class for flagging production has finished

    /*
     * Constructor for producer class
     */
    Producer (String name, MinHeap heap, int numProcesses, int numProductions, SharedBool productionFinished) {
           m_heap = heap;
           m_name = name;
           m_numProductions = numProductions;
           m_numProcesses = numProcesses;
           m_productionFinished = productionFinished;
    }

    /*
     * Thread run function
     */
    public void run() {
        System.out.println("Starting producer."); // signal that producer has started
        // loop over each production cycle
        for (int iProd=1; iProd <= m_numProductions; iProd++) {
            // display information about current priority queue
            System.out.println("Current priority queue has " + m_heap.size() + " nodes.");
            System.out.println("Producer adding " + m_numProcesses + " nodes.");
            int idleTime = randomIdle.getNext();
            // produce the processes for the heap
            for (int iProc=1; iProc <= m_numProcesses; iProc++) {
                int id = iProd + iProc + (iProd - 1)*m_numProcesses; // create unique process ids
                int priority = randomPriority.getNext(); // get random priority
                int timems = randomTimeSlice.getNext(); // get random runtime
                m_heap.insertNode(new HeapNode(id, priority, timems)); // add process node to the heap
            }
            // sleep the producer
            try {
                System.out.println("Producer going to sleep.");
                Thread.sleep(idleTime);
            }
            catch (InterruptedException ex) {
                //tbd
            }
        }
        m_productionFinished.set(true); // flag that the producer has finished
        System.out.println("Producer is exiting.");
    }
}
