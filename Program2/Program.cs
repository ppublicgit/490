using System;
using System.Collections.Generic;
using System.Threading;

namespace ConsoleApp1
{
    class Program
    {
        private static MinHeap globalHeap; //shared heap
        private static SharedBool globalProductionFinished; //shared bool to signal produciton finished

        

        static void C1()
        {
            Consumer consumer1 = new Consumer("C1", 1, globalHeap, 1000, globalProductionFinished);
            consumer1.Run();
        }

        static void C2()
        {
            Consumer consumer2 = new Consumer("C2", 2, globalHeap, 1000, globalProductionFinished);
            consumer2.Run();
        }

        static void P()
        {
            Producer producer = new Producer("P1", globalHeap, 25, 3, globalProductionFinished);
            producer.Run();
        }
        static void Main(string[] args)
        {
            globalHeap = new MinHeap();
            globalProductionFinished = new SharedBool(false);

            Thread t_consumer1 = new Thread(new ThreadStart(C1)); 
            Thread t_consumer2 = new Thread(new ThreadStart(C2));
            Thread t_producer = new Thread(new ThreadStart(P));

            // Start threads
            t_consumer1.Start();
            t_consumer2.Start();
            t_producer.Start();

            // Wait for threads to complete 
            t_consumer1.Join();
            t_consumer2.Join();
            t_producer.Join();

            Console.Write("\n\nConsumers completed. Press any key...");
            Console.ReadKey();
        }
    }
}
