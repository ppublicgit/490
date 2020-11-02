using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace ConsoleApp1
{
    class Consumer
    {
        private MinHeap m_heap; // shared heap
        private String m_name; // name of consumer
        private int m_idle; // length of time to idle consumer if no processes in heap
        private int m_count; // count of how many process have been consumed
        private SharedBool m_productionFinished; // shared pointer with producer to let consumer know producer is done
        private String m_prefix; // prefix used for output messages of consumer

        /*
        * Consumer constructor
        */
        public Consumer(String name, int num, MinHeap heap, int idle, SharedBool productionFinished)
        {
            m_heap = heap;
            m_name = name;
            m_idle = idle;
            m_prefix = GetPrefix(num);
            m_productionFinished = productionFinished;
        }

        /*
        * calculate the prefix for consumer messages. Throws exception for a consumer number less than zero
        */
        private String GetPrefix(int num)
        {
            if (num <= 0)
            {
                throw new Exception("Consumer number must be greater than zero.");
            }
            else
            {
                String prefix = new String(new char[num * 12]).Replace('\0', ' ');
                return prefix;
            }
        }


        /*
        * thread run method that handles the consumption
        */
        public void Run()
        {
            Console.WriteLine(m_prefix + "Consumer " + m_name + " is starting."); // consumer has spun up
            while (!m_productionFinished.Get() || m_heap.Size() != 0)
            { // check for empty heap and finished production
                if (m_heap.Size() == 0)
                { // if heap is empty, then idle to allow producer to finish producing
                    Console.WriteLine(m_prefix + "Consumer " + m_name + " is idle.");
                    Thread.Sleep(m_idle);
                }
                else
                { // consume the next process
                    String message = Consume();
                    Console.WriteLine(m_prefix + "Consumer " + m_name + " finished " + message);
                }
            }
            Console.WriteLine(m_prefix + "Consumption by consumer " + m_name + " has finished.");
            Console.WriteLine(m_prefix + "Consumer " + m_name + " completed " + m_count + " processes.");
        }

        /*
        * Pop the root of the heap and then idle for the amount of time the process node specifies
        * Returns the message to print to stdout
        */
        private String Consume()
        {
            String message; // output message
            HeapNode node = m_heap.RemoveMin(); // pop root
            Thread.Sleep(node.GetTimems()); // sleep for process nodes time to simulate running a program
            // output message for process that was consumed
            message = "Process: " + node.GetId() + " with priority: " + node.GetPriority() + " at " + DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
            m_count += 1; // increase count of number of consumptions
            return message;
        }
    }
}
