/*
 * A minimum heap to use as a priority queue that is synchronized to allow use in multi-threaded programs
 * Uses an array list as its underlying structure for storing the heap
 */

import java.util.ArrayList;

public class MinHeap {
    ArrayList<HeapNode> m_heapArray = new ArrayList<HeapNode>(); // Array list used for heap
    private int m_size = 0; // size of heap

    /*
     * Empty constructor
     */
    MinHeap() {
    }

    /*
     * constructor for passing in a single heap node
     */
    MinHeap(HeapNode node) {
        m_heapArray.add(node); // add node to heap as root
        m_size = 1; // update size
    }

    /*
     * Array list constructor to build heap in linear time
     */
    MinHeap(ArrayList<HeapNode> heapArray) {
        m_heapArray.addAll(heapArray); // copy input array into internal array
        m_size = heapArray.size(); // update size
        buildHeap();
    }

    /*
     * Linear time build of heap using float down of first n//2 nodes
     */
    private void buildHeap() {
        // loop over each n//2 nodes (starting at last non leaf node and work to root)
        // and float each node down to proper position
        for (int i = Math.floorDiv(m_size, 2); i >= 0; i--) {
            int index = i; // first non-leaf node is at n//2 - 1 for a 0 indexed array
            floatDown(index); // start recursive call of float down for index
        }
    }

    /*
     * Float a node from its position up to the correct position
     */
    private boolean floatUp(int index) {
        int parent = Math.floorDiv(index, 2); // parent of current node
        boolean ret = false;
        if (parent < 0) { // check that current node is not root
            ret = false;
        }
        else {
            // if parent node is greater than current node, swap nodes
            if (m_heapArray.get(parent).getPriority() > m_heapArray.get(index).getPriority()) {
                swapNode(index, parent);
                ret = true;
            }
            else {
                ret = false;
            }
        }
        return ret;
    }

    /*
     * Swap node of parent with child
     */
    private void swapNode(int parent, int child) {
        HeapNode temp = m_heapArray.get(parent);
        m_heapArray.set(parent, m_heapArray.get(child));
        m_heapArray.set(child, temp);
    }

    /*
     * public method call for inserting node
     * if heap is empty, simply adds the node
     * if heap is not empty then it inserts node at the end and floats it up
     */
    synchronized public void insertNode(HeapNode node) {
        if (isEmpty()) {
            m_heapArray.add(node);
            m_size += 1;
        }
        else {
            m_heapArray.add(node);
            m_size += 1;
            boolean swapped = true;
            int index = m_size-1;
            while (swapped == true) {
                swapped = floatUp(index);
                index = Math.floorDiv(index, 2);
            }
        }
    }

    /*
     * Float down for building heap
     * Floats a node down recursively to find the correct position for a node during the build heap process
     */
    private boolean floatDown(int index) {
        int leftchild = 2 * index + 1; // left child in an array heap
        int rightchild = 2 * index + 2; // right child in an array heap
        boolean ret = false;
        if (index < 0) { // invalid index
            ret = false;
        }
        else if (m_size <= leftchild) { // check if left child exists
            ret = false;
        }
        else if (m_size <= rightchild) { // check if right child exists
            // right child does not exist so just check if parent is less then left child
            // if so then swap nodes and recursively call floatdown on child node
            if (m_heapArray.get(index).getPriority() > m_heapArray.get(leftchild).getPriority()) {
                swapNode(index, leftchild);
                floatDown(leftchild);
                ret = true;
            }
            else {
                ret = false;
            }
        }
        else {
            // left and right exist
            // same structure as previous, but now just check if either is less and if so then grab minimum child node
            // recursively call floatdown on min node if swapping
            if (m_heapArray.get(index).getPriority() > m_heapArray.get(leftchild).getPriority()
                    || m_heapArray.get(index).getPriority() > m_heapArray.get(rightchild).getPriority()) {
                int minchild;
                if (m_heapArray.get(leftchild).getPriority() < m_heapArray.get(rightchild).getPriority()) {
                    minchild = leftchild;
                }
                else {
                    minchild = rightchild;
                }
                swapNode(index, minchild);
                floatDown(minchild);
                ret = true;
            }
        }
        return ret;
    }

    /*
     * return the root node without removing the root node
     */
    synchronized public HeapNode getMin() {
        return m_heapArray.get(0);
    }

    /*
     * check if heap is empty
     */
    synchronized public boolean isEmpty() {
        boolean ret = false;
        if (m_size == 0) {
            ret = true;
        }
        else {
            ret = false;
        }
        return ret;
    }

    /*
     * remove the minimum (root) node from the heap
     * Swap root with last node, pop the last node and then recursively call float down on root node to
     * re-establish heap property
     */
    synchronized public HeapNode removeMin() throws MyEmptyHeapException {
        HeapNode retNode;
        if (isEmpty()) { // cant pop root from an empty heap
            throw new MyEmptyHeapException("Heap is empty");
        }
        else {
            retNode = m_heapArray.get(0); // current root
            m_heapArray.set(0, m_heapArray.get(m_size-1)); //set root to last value of heap
            m_heapArray.remove(m_size-1); // remove last value
            m_size -= 1; //decrease size
            floatDown(0); // float root down
        }
        return retNode;
    }

    /*
     * simplistic print of heap for error checking
     */
    synchronized public void printHeap() {
        if (m_size == 0) {
            System.out.println("Heap is empty.");
        }
        for (int i = 0; i < m_size; i++) {
            System.out.println(m_heapArray.get(i));
        }
    }

    /*
     * simple check of heap property for error check
     * checks to make sure every node's child is greater than or equal to itself
     */
    synchronized public boolean checkHeapProperty() {
        boolean ret = true;
        for (int i=0; i < Math.floorDiv(m_size, 2); i++) {
            if (!checkHeapPropertyHelp(i)) {
                ret = false;
                break;
            }
        }
        return ret;
    }

    /*
     * helper function for check heap property to start the recursive call stack
     */
    private boolean checkHeapPropertyHelp(int index) {
        int leftchild = index * 2 + 1;
        int rightchild = index * 2 + 2;
        boolean ret = true;
        if (leftchild >= m_size) {
            ret = true;
        }
        else if (rightchild >= m_size) {
            if (m_heapArray.get(leftchild).getPriority() < m_heapArray.get(index).getPriority()) {
                ret = false;
            }
        }
        else if (m_heapArray.get(leftchild).getPriority() < m_heapArray.get(index).getPriority()
            || m_heapArray.get(rightchild).getPriority() < m_heapArray.get(index).getPriority()) {
            ret = false;
        }
        else ;
        return ret;
    }

    /*
     * return the size of the heap
     */
    synchronized int size() {
        return m_size;
    }
}
