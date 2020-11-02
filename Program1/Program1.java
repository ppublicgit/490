/*
 * Program 1
 */
public class Program1 {
    private static MinHeap globalHeap; //shared heap
    private static SharedBool globalProductionFinished; // shared bool to signal production has finished

    public static void main(String[] args) {
        globalHeap = new MinHeap();
        globalProductionFinished = new SharedBool(false);

        try {
            // create consumers
            Consumer consumer1 = new Consumer("C1", 1, globalHeap, 1000, globalProductionFinished);
            Consumer consumer2 = new Consumer("C2", 2, globalHeap, 1000, globalProductionFinished);

            // create producer
            Producer producer1 = new Producer("P1", globalHeap, 25, 3, globalProductionFinished);

            // thread producer and consumers
            Thread threadc1 = new Thread(consumer1);
            Thread threadc2 = new Thread(consumer2);
            Thread threadp = new Thread(producer1);

            // start producer and consumers
            threadc1.start();
            threadc2.start();
            threadp.start();

            try {
                // wait for producer and consumers to finish running
                threadc1.join();
                threadc2.join();
                threadp.join();
            }
            catch (Exception e) {
                // to do
            }
        }
        catch (Exception e) {
            // needed because consumer object can throw an exception if an invalid num is passed in
        }
        // print to screen that main program has finished
        System.out.print("\nmain program exit");
        System.exit(0);
    }
}