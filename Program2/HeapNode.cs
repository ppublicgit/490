using System;

namespace ConsoleApp1
{
    class HeapNode
    {
        private int m_id; //process id
        private int m_priority; // process priority
        private int m_timems; // process run time ms

        /*
         * Constructor
         */
         public HeapNode(int id, int priority, int timems)
        {
            m_id = id;
            m_priority = priority;
            m_timems = timems;
        }

        /*
         * Get id
         */
         public int GetId()
        {
            return m_id;
        }

        /*
         *  Get priority
         */
         public int GetPriority()
        {
            return m_priority;
        }

        /*
         * Get Time
         */
         public int GetTimems()
        {
            return m_timems;
        }

        /*
         * Override string for a more sensible print
         */
        public override string ToString()
        {
            return "HeapNode: " + m_id + " " + m_priority + " " + m_timems;
        }
    }
}
