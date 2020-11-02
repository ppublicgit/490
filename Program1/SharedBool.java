/*
 * Shared boolean class to allow sharing of boolean between multiple threads
 */
public class SharedBool {
    private boolean m_bool; // bool value

    /*
     * Shared bool constructor
     */
    SharedBool(boolean bool) {
        m_bool = bool;
    }

    /*
     * Synchronized method to get bools value
     */
    synchronized public boolean get() {
        return m_bool;
    }

    /*
     * synchronized method to set bools value
     */
    synchronized public void set(boolean bool) {
        m_bool = bool;
    }
}
