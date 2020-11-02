using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    class SharedBool
    {
        private Boolean m_bool; // bool value

        /*
         * Shared Bool constructor
         */
         public SharedBool(Boolean boolean)
        {
            m_bool = boolean;
        }

        /*
         * Get bool value
         */
        [MethodImpl(MethodImplOptions.Synchronized)]
        public Boolean Get()
        {
            return m_bool;
        }

        /*
         * Set bool value
         */
        [MethodImpl(MethodImplOptions.Synchronized)]
        public void Set(Boolean boolean)
        {
            m_bool = boolean;
        }
    }
}
