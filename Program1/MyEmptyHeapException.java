/*
 * A simple exception to throw if an invalid operation is requested on an empty heap
 */
public class MyEmptyHeapException extends Exception {
    public MyEmptyHeapException(String message) {
        super(message);
    }
}