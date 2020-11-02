using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    class RandomInt
    {
        private int m_min; // min integer
        private int m_max; // max integer
        private Random r = new Random();

        /*
         * RandomInt Constructor
         */
        public RandomInt(int min, int max)
        {
            if (min >= max)
            {
                throw new Exception();
            }
            m_min = min;
            m_max = max;
        }

        /*
         * Get Next Integer
         */
        public int GetNext()
        {
            return r.Next(m_min, m_max);
        }
    }
}
