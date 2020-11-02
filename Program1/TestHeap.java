/*
 * This is a test file for checking the heap class and its functionality
 */

import java.util.ArrayList;

public class TestHeap {
    public static void main(String[] args) {
        try {
            ArrayList<HeapNode> heapArray = new ArrayList<HeapNode>();
            heapArray.add(new HeapNode(1, 8, 100));
            heapArray.add(new HeapNode(2, 5, 100));
            heapArray.add(new HeapNode(3, 3, 100));
            heapArray.add(new HeapNode(4, 6, 100));
            heapArray.add(new HeapNode(5, 2, 100));
            heapArray.add(new HeapNode(6, 3, 100));
            heapArray.add(new HeapNode(7, 1, 100));
            heapArray.add(new HeapNode(1, 8, 100));
            heapArray.add(new HeapNode(2, 5, 100));
            heapArray.add(new HeapNode(3, 3, 100));
            heapArray.add(new HeapNode(4, 6, 100));
            heapArray.add(new HeapNode(5, 2, 100));
            heapArray.add(new HeapNode(6, 3, 100));
            heapArray.add(new HeapNode(7, 1, 100));

            MinHeap EmptyConstruct = new MinHeap();
            MinHeap SingleConstruct = new MinHeap(heapArray.get(0));
            MinHeap ArrayConstruct = new MinHeap(heapArray);

            for (int i = 0; i < heapArray.size(); i++) {
                if (i==0) {
                    EmptyConstruct.insertNode(heapArray.get(i));
                }
                else {
                    EmptyConstruct.insertNode(heapArray.get(i));
                    SingleConstruct.insertNode(heapArray.get(i));
                }
            }
            System.out.println("Empty");
            EmptyConstruct.printHeap();
            System.out.println("Single");
            SingleConstruct.printHeap();
            System.out.println("Array");
            ArrayConstruct.printHeap();

            System.out.println("Array Min Checked");
            System.out.println(ArrayConstruct.getMin());
            System.out.println("Array Min Removed");
            System.out.println(ArrayConstruct.removeMin());
            System.out.println("Array");
            ArrayConstruct.printHeap();

            System.out.println("Checking Empty Heap");
            while (!EmptyConstruct.isEmpty()) {
                if (!EmptyConstruct.checkHeapProperty()) {
                    System.out.println("Heap Property Failed");
                }
                System.out.println(EmptyConstruct.removeMin());
            }
            if (!EmptyConstruct.checkHeapProperty()) {
                System.out.println("Heap Property Failed");
            }
            System.out.println(EmptyConstruct.removeMin());
        } catch (MyEmptyHeapException e) {
            System.out.println("error caught");
        }
        System.out.println("Main exit");
        System.exit(0);
    }
}
