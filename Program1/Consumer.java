/*
 * consumer class for creating a threaded consumer
 */
public class Consumer implements Runnable {
    private MinHeap m_heap; // shared heap
    private String m_name; // name of consumer
    private int m_idle; // length of time to idle consumer if no processes in heap
    private int m_count; // count of how many process have been consumed
    private SharedBool m_productionFinished; // shared pointer with producer to let consumer know producer is done
    private String m_prefix; // prefix used for output messages of consumer

    /*
     * Consumer constructor
     */
    Consumer (String name, int num, MinHeap heap, int idle, SharedBool productionFinished) throws Exception {
        m_heap = heap;
        m_name = name;
        m_idle = idle;
        m_productionFinished = productionFinished;
        try {
            m_prefix = getPrefix(num);
        }
        catch (Exception e) {
            throw e;
        }
    }

    /*
     * calculate the prefix for consumer messages. Throws exception for a consumer number less than zero
     */
    private String getPrefix(int num) throws Exception {
        if (num <= 0) {
            throw new Exception("Consumer number must be greater than zero.");
        }
        else {
            String prefix = new String(new char[num*12]).replace('\0', ' ');
            return prefix;
        }
    }

    /*
     * thread run method that handles the consumption
     */
    public void run() {
        System.out.println(m_prefix + "Consumer " + m_name + " is starting."); // consumer has spun up
        while (!m_productionFinished.get() || !m_heap.isEmpty()) { // check for empty heap and finished production
            if (m_heap.isEmpty()) { // if heap is empty, then idle to allow producer to finish producing
                try {
                    System.out.println(m_prefix + "Consumer " + m_name + " is idle.");
                    Thread.sleep(m_idle);
                } catch (InterruptedException ex) {
                    //tbd
                }
            }
            else { // consume the next process
                String message = consume();
                System.out.println(m_prefix + "Consumer " + m_name + " finished " + message);
            }
        }
        System.out.println(m_prefix + "Consumption by consumer " + m_name + " has finished.");
        System.out.println(m_prefix + "Consumer " + m_name + " completed " + m_count + " processes.");
    }

    /*
     * Pop the root of the heap and then idle for the amount of time the process node specifies
     * Returns the message to print to stdout
     */
    private String consume() {
        String message; // output message
        try {
            HeapNode node = m_heap.removeMin(); // pop root

            try {
                Thread.sleep(node.getTime()); // sleep for process nodes time to simulate running a program
            } catch (InterruptedException ex) {
                //tbd
            }
            // output message for process that was consumed
            message = "Process: "+node.getId()+" with priority: "+node.getPriority()+" at "+System.currentTimeMillis();
            m_count += 1; // increase count of number of consumptions
        } catch (MyEmptyHeapException ex) {
            // empty heap message. theoretically should never be thrown
           message = m_prefix + "Consumer " + m_name + " cannot consume from empty heap.";
        }
        return message;
    }
}
