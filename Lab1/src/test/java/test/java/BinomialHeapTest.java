package test.java;

import lab1.BinomialHeap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class BinomialHeapTest {

    @Test
    public void testInsert() {
        BinomialHeap<Integer> heap = new BinomialHeap<>();
        heap.insert(3);
        Assertions.assertEquals(1, heap.getSize(), "Incorrect heap size after insertion");
        heap.insert(1);
        Assertions.assertEquals(2, heap.getSize(), "Incorrect heap size after insertion");
        heap.insert(3);
        Assertions.assertEquals(3, heap.getSize(), "Incorrect heap size after insertion");

        heap.removeSmallest();
        Assertions.assertEquals(2, heap.getSize(), "Incorrect heap size after removal");
        heap.removeSmallest();
        Assertions.assertEquals(1, heap.getSize(), "Incorrect heap size after removal");
        heap.removeSmallest();
        Assertions.assertEquals(0, heap.getSize(), "Incorrect heap size after removal");
    }

    @Test
    public void testClear() {
        BinomialHeap<Integer> heap = new BinomialHeap<>();
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.clear();
        Assertions.assertEquals(0, heap.getSize(), "Heap size should be 0 after clear()");
        heap.clear();
        Assertions.assertEquals(0, heap.getSize(), "Heap size should be 0 after clearing an empty heap");
    }

    @Test
    public void testRemoveSmallest() {
        BinomialHeap<Integer> heap = new BinomialHeap<>();
        heap.insert(1);
        heap.insert(-3);
        heap.insert(99);
        heap.insert(0);
        Assertions.assertEquals(new Integer(-3), heap.removeSmallest(), "The element removed through removeSmallest() is not the smallest");
        Assertions.assertEquals(new Integer(0), heap.removeSmallest(), "The element removed through removeSmallest() is not the smallest");
        Assertions.assertEquals(new Integer(1), heap.removeSmallest(), "The element removed through removeSmallest() is not the smallest");
        Assertions.assertEquals(new Integer(99), heap.removeSmallest(), "The element removed through removeSmallest() is not the smallest");
        Assertions.assertNull(heap.removeSmallest(), "The element removed through removeSmallest() is not the smallest");
    }

    @Test
    public void testMerge() {
        BinomialHeap<Integer> heap = new BinomialHeap<>();
        Integer[] items1 = {2, 4, 6, 8};
        for (int item : items1) {
            heap.insert(item);
        }

        BinomialHeap<Integer> heap2 = new BinomialHeap<>();
        Integer[] items2 = {1, 3, 5, 7};
        for (int item : items2) {
            heap2.insert(item);
        }

        heap.merge(heap2);

        Assertions.assertEquals(items1.length + items2.length, heap.getSize(), "When you merge another heap B to a heap A, size of the heap A must be equal to the sum of their sizes");
        Assertions.assertEquals(0, heap2.getSize(), "When you merge another heap B to a heap A, size of the heap B must be equal to 0");


        Integer[] totalItems = Arrays.copyOf(items1, items1.length + items2.length);
        System.arraycopy(items2, 0, totalItems, items1.length, items2.length);

        Integer[] heapItems = new Integer[items1.length + items2.length];
        for (int i = 0; i < items1.length + items2.length; i++) {
            heapItems[i] = heap.removeSmallest();
        }

        Arrays.sort(totalItems);
        Arrays.sort(heapItems);

        Assertions.assertArrayEquals(totalItems, heapItems, "After merging two heaps, resulting contents should be equal to the sum of contents of both heaps");
    }

    @Test
    public void testEmptyHeap() {
        BinomialHeap<Integer> heap = new BinomialHeap<>();
        Assertions.assertEquals(0, heap.getSize(), "Size of an empty heap should be 0");
    }

    @Test
    public void testOneItemHeap() {
        BinomialHeap<Integer> heap = new BinomialHeap<>(9);
        Assertions.assertEquals(1, heap.getSize(), "Size of a heap initialized with an item should be 1");
    }

    @Test
    public void testHeapStructure() {
        BinomialHeap<Integer> heap = new BinomialHeap<>(3);
        heap.insert(1);
        heap.insert(9);
        heap.insert(0);
        heap.insert(6);
        heap.insert(2);
        heap.insert(7);
        heap.insert(2);

        /*
        the structure is gonna look like this
        null null null 0
                     / | \
                    2  1   9
                  / |  |
                 2  6  3
                 |
                 7
         */

        Assertions.assertNull(heap.getTrees()[0], "Internal structure is incorrect");
        Assertions.assertNull(heap.getTrees()[1], "Internal structure is incorrect");
        Assertions.assertNull(heap.getTrees()[2], "Internal structure is incorrect");
        Assertions.assertEquals(new Integer(0), heap.getTrees()[3].element, "Internal structure is incorrect");

        Assertions.assertEquals(new Integer(2), heap.getTrees()[3].leftChild.element, "Internal structure is incorrect");
        Assertions.assertEquals(new Integer(1), heap.getTrees()[3].leftChild.nextSibling.element, "Internal structure is incorrect");
        Assertions.assertEquals(new Integer(9), heap.getTrees()[3].leftChild.nextSibling.nextSibling.element, "Internal structure is incorrect");

        Assertions.assertEquals(new Integer(2), heap.getTrees()[3].leftChild.leftChild.element, "Internal structure is incorrect");
        Assertions.assertEquals(new Integer(6), heap.getTrees()[3].leftChild.leftChild.nextSibling.element, "Internal structure is incorrect");
        Assertions.assertEquals(new Integer(3), heap.getTrees()[3].leftChild.nextSibling.leftChild.element, "Internal structure is incorrect");

        Assertions.assertEquals(new Integer(7), heap.getTrees()[3].leftChild.leftChild.leftChild.element, "Internal structure is incorrect");
    }
}
