/*
 * A simple heap node class to simulate a process
 * contains an id, priority and sleep time to simulate runtime
 */
public class HeapNode {
    private int m_id; // process id
    private int m_priority; // process priority
    private int m_timems; // run time

    /*
     * Constructor
     */
    public HeapNode(int id, int priority, int timems) {
        m_id = id;
        m_priority = priority;
        m_timems = timems;
    }

    /*
     * get method for id
     */
    public int getId() {
        return m_id;
    }

    /*
     * get method for priority
     */
    public int getPriority() {
        return m_priority;
    }

    /*
     * get method for runtime
     */
    public int getTime() {
        return m_timems;
    }

    /*
     * override of to string to do a more sensible print statement
     */
    @Override
    public String toString() {
        String print = String.format("Process(id=%d, priority=%d, runtime=%d)", m_id, m_priority, m_timems);
        return print;
    }
}
