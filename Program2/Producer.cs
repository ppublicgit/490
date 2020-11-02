using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace ConsoleApp1
{
    class Producer
    {
        private MinHeap m_heap; //shared heap
        private String m_name; // name of producer
        private int m_numProcesses; // number of process to produce per production cycle
        private int m_numProductions; // number of production cycles
        private RandomInt randomPriority = new RandomInt(0, 10); // random int generator for priorities
        private RandomInt randomIdle = new RandomInt(1000, 2000); // random int generator for idle time between productions
        private RandomInt randomTimeSlice = new RandomInt(100, 500); // random int generator for runtimes for nodes
        private SharedBool m_productionFinished; // shared bool class for flagging production has finished

        /*
         * Constructor for producer
         */
        public Producer(String name, MinHeap heap, int numProcesses, int numProductions, SharedBool productionFinished)
        {
            m_heap = heap;
            m_name = name;
            m_numProductions = numProductions;
            m_numProcesses = numProcesses;
            m_productionFinished = productionFinished;
        }

        /*
         * Thread run function
         */
        public void Run()
        {
            Console.WriteLine("Starting producer..."); // print that producer has started
            // loop over each production cycle
            for (int iProd = 1; iProd <= m_numProductions; iProd++)
            {
                // diplay information about current priority queue
                Console.WriteLine("Current priority queue has " + m_heap.Size() + " nodes.");
                Console.WriteLine("Producer adding " + m_numProcesses + " nodes.");
                int idleTime = randomIdle.GetNext();
                // produce the process for the heap
                for (int iProc = 1; iProc <= m_numProcesses; iProc++)
                {
                    int id = iProd + iProc + (iProd - 1) * m_numProcesses - iProd; //create a unique process id
                    int priority = randomPriority.GetNext(); //get random priority
                    int timems = randomTimeSlice.GetNext(); //get random time slice
                    m_heap.InsertNode(new HeapNode(id, priority, timems)); //add process to heap
                }
                //sleep the producer
                Console.WriteLine("Producer going to sleep.");
                Thread.Sleep(idleTime);
            }
            m_productionFinished.Set(true); // flag that the producer has finished
            Console.WriteLine("Producer is exiting.");
        }
    }
}
