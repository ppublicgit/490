using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Threading;

namespace ConsoleApp1
{
    class MinHeap
    {
        // member variables
        List<HeapNode> m_heapArray = new List<HeapNode>(); // list to store heap nodes
        private int m_size = 0; // size of heap

        /*
         * Empty Constructor
         */
         public MinHeap()
        {
            m_size = 0;
        }

        /*
         * Single Value constructor 
         */
         public MinHeap(HeapNode node)
        {
            m_heapArray.Add(node); // add node to heap array
            m_size = 1; //size is 1
        }

        /*
         * Build heap in linear time Constructor
         */
         public MinHeap(List<HeapNode> heapArray)
        {
            m_heapArray = heapArray.GetRange(0, heapArray.Count); //copy input array into internal array
            m_size = heapArray.Count();
            BuildHeap();
        }

        /*
         * Linear time build of heap using float down on first n//2 nodes
         */
         private void BuildHeap()
        {
            //loop over each n//2 nodes (starting at last leaf node and work to root)
            //and float down each node to proper position in heap
            for (int i = m_size / 2; i >= 0; i--)
            {
                FloatDown(i);
            }
        }

        /*
         * Float Down method to float nodes down to proper location in heap
         */
         private void FloatDown(int index)
        {
            int leftchild = 2 * index + 1; //left child in heap array
            int rightchild = 2 * index + 2; // right child in heap array
            if (index < 0 || m_size <= leftchild) //invalid index
            {
                ;
            }
            else if (m_size <= rightchild) // check if right child exists 
            {
                // no right child so just check if leftchild is less than parent
                if (m_heapArray[index].GetPriority() > m_heapArray[leftchild].GetPriority() )
                {
                    SwapNode(index, leftchild);
                    FloatDown(leftchild);
                }
            }
            else
            {
                // left and right child exists
                if (m_heapArray[index].GetPriority() > m_heapArray[leftchild].GetPriority() ||
                    m_heapArray[index].GetPriority() > m_heapArray[rightchild].GetPriority())
                {
                    int minchild;
                    if (m_heapArray[leftchild].GetPriority()  < m_heapArray[rightchild].GetPriority())
                    {
                        minchild = leftchild;
                    }
                    else
                    {
                        minchild = rightchild;
                    }
                    SwapNode(index, minchild);
                    FloatDown(minchild);
                }
            }
        }

        /*
         * Swap Nodes of heap array
         */
         private void SwapNode(int parent, int child)
        {
            HeapNode temp = m_heapArray[parent];
            m_heapArray[parent] = m_heapArray[child];
            m_heapArray[child] = temp;
        }

        /*
         * Float a node up to its correct position.
         */
         private Boolean FloatUp(int index)
        {
            Boolean swapped = false;
            int parent = index / 2; // parent index of node
            if (parent >= 0 && m_heapArray[index].GetPriority() < m_heapArray[parent].GetPriority())
            {
                SwapNode(parent, index);
                swapped = true;
            }
            return swapped;
        }

        /*
         * Public method for inserting node into heap array
         * If heap array is empty, simply adds the node
         * Else it inserts node at end of heap array and floats the node up to the correct position
         */
        [MethodImpl(MethodImplOptions.Synchronized)]
        public void InsertNode(HeapNode node)
        {
            if (m_size == 0)
            {
                m_heapArray.Add(node);
                m_size += 1;
            }
            else
            {
                m_heapArray.Add(node);
                m_size += 1;
                Boolean swapped = true;
                int index = m_size - 1;
                while (swapped)
                {
                    swapped = FloatUp(index);
                    index = index / 2;
                }
            }
        }

        /*
         * Get root node of heap
         */
        [MethodImpl(MethodImplOptions.Synchronized)]
        public HeapNode GetMin()
        {
            if (m_size != 0)
            {
                return m_heapArray[0];
            }
            else
            {
                throw new Exception();
            }
        }

        /*
         * Remove the minimum (root) node from heap
         * Swap root with last node, pop the last node and then recursively call float down on root node
         * to reestablish heap property
         */
        [MethodImpl(MethodImplOptions.Synchronized)]
        public HeapNode RemoveMin()
        {
            HeapNode retNode;
            if (m_size == 0)
            {
                throw new Exception();
            }
            else
            {
                retNode = m_heapArray[0]; // current root
                m_heapArray[0] = m_heapArray[m_size - 1]; // set root node to last node
                m_heapArray.RemoveAt(m_size - 1);
                m_size--;
                FloatDown(0);
            }
            return retNode;
        }

        /*
         * Print Heap
         */
        [MethodImpl(MethodImplOptions.Synchronized)]
        public void PrintHeap()
        {
            if (m_size == 0)
            {
                Console.WriteLine("Heap is empty.");
            }
            for (int i = 0; i < m_size; i++)
            {
                Console.WriteLine(m_heapArray[i]);
            }
        }

        /*
         * Get size of heap
         */
        [MethodImpl(MethodImplOptions.Synchronized)]
        public int Size()
        {
            return m_size;
        }
    }
}
