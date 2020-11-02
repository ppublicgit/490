using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    class TestHeap
    {
        public TestHeap() {
            List<HeapNode> heapArray = new List<HeapNode>();
            heapArray.Add(new HeapNode(2, 5, 100));
            heapArray.Add(new HeapNode(1, 8, 100));
            heapArray.Add(new HeapNode(3, 3, 100));
            heapArray.Add(new HeapNode(4, 6, 100));
            heapArray.Add(new HeapNode(5, 2, 100));
            heapArray.Add(new HeapNode(6, 3, 100));
            heapArray.Add(new HeapNode(7, 1, 100));
            heapArray.Add(new HeapNode(1, 8, 100));
            heapArray.Add(new HeapNode(2, 5, 100));
            heapArray.Add(new HeapNode(3, 3, 100));
            heapArray.Add(new HeapNode(4, 6, 100));
            heapArray.Add(new HeapNode(5, 2, 100));
            heapArray.Add(new HeapNode(6, 3, 100));
            heapArray.Add(new HeapNode(7, 1, 100));

            MinHeap EmptyConstruct = new MinHeap();
            MinHeap SingleConstruct = new MinHeap(heapArray[0]);
            MinHeap ArrayConstruct = new MinHeap(heapArray);


            for (int i = 0; i < heapArray.Count; i++)
            {
                if (i == 0)
                {
                    EmptyConstruct.InsertNode(heapArray[i]);
                }
                else
                {
                    EmptyConstruct.InsertNode(heapArray[i]);
                    SingleConstruct.InsertNode(heapArray[i]);
                }
            }

            Console.WriteLine("Empty");
            EmptyConstruct.PrintHeap();
            Console.WriteLine("Single");
            SingleConstruct.PrintHeap();
            Console.WriteLine("Array");
            ArrayConstruct.PrintHeap();

            Console.WriteLine("Array min checked");
            Console.WriteLine(ArrayConstruct.GetMin());
            Console.WriteLine("Array min removed");
            Console.WriteLine(ArrayConstruct.RemoveMin());
            Console.WriteLine("Array");
            ArrayConstruct.PrintHeap();


            Console.Write("\n\nConsumers completed. Press any key...");
            Console.ReadKey();
        }

    }
}
