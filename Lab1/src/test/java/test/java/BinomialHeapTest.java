package test.java;

import main.java.BinomialHeap;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BinomialHeapTest {

    @Test
    public void testInsert() {
        BinomialHeap<Integer> heap = new BinomialHeap<>();
        heap.insert(3);
        Assert.assertEquals("Incorrect heap size after insertion", 1, heap.getSize());
        heap.insert(1);
        Assert.assertEquals("Incorrect heap size after insertion", 2, heap.getSize());
        heap.insert(3);
        Assert.assertEquals("Incorrect heap size after insertion", 3, heap.getSize());

        heap.removeSmallest();
        Assert.assertEquals("Incorrect heap size after removal", 2, heap.getSize());
        heap.removeSmallest();
        Assert.assertEquals("Incorrect heap size after removal", 1, heap.getSize());
        heap.removeSmallest();
        Assert.assertEquals("Incorrect heap size after removal", 0, heap.getSize());
    }

    @Test
    public void testClear() {
        BinomialHeap<Integer> heap = new BinomialHeap<>();
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.clear();
        Assert.assertEquals("Heap size should be 0 after clear()", 0, heap.getSize());
        heap.clear();
        Assert.assertEquals("Heap size should be 0 after clearing an empty heap", 0, heap.getSize());
    }

    @Test
    public void testRemoveSmallest() {
        BinomialHeap<Integer> heap = new BinomialHeap<>();
        heap.insert(1);
        heap.insert(-3);
        heap.insert(99);
        heap.insert(0);
        Assert.assertEquals("The element removed through removeSmallest() is not the smallest", new Integer(-3), heap.removeSmallest());
        Assert.assertEquals("The element removed through removeSmallest() is not the smallest", new Integer(0), heap.removeSmallest());
        Assert.assertEquals("The element removed through removeSmallest() is not the smallest", new Integer(1), heap.removeSmallest());
        Assert.assertEquals("The element removed through removeSmallest() is not the smallest", new Integer(99), heap.removeSmallest());
        Assert.assertNull("Calling removeSmallest() on empty heap should return null", heap.removeSmallest());
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

        Assert.assertEquals("When you merge another heap B to a heap A, size of the heap A must be equal to the sum of their sizes", items1.length + items2.length, heap.getSize());
        Assert.assertEquals("When you merge another heap B to a heap A, size of the heap B must be equal to 0", 0, heap2.getSize());


        Integer[] totalItems = Arrays.copyOf(items1, items1.length + items2.length);
        System.arraycopy(items2, 0, totalItems, items1.length, items2.length);

        Integer[] heapItems = new Integer[items1.length + items2.length];
        for (int i = 0; i < items1.length + items2.length; i++) {
            heapItems[i] = heap.removeSmallest();
        }

        Arrays.sort(totalItems);
        Arrays.sort(heapItems);

        Assert.assertArrayEquals("After merging two heaps, resulting contents should be equal to the sum of contents of both heaps", totalItems, heapItems);
    }

    @Test
    public void testEmptyHeap() {
        BinomialHeap<Integer> heap = new BinomialHeap<>();
        Assert.assertEquals("Size of an empty heap should be 0", 0, heap.getSize());
    }

    @Test
    public void testOneItemHeap() {
        BinomialHeap<Integer> heap = new BinomialHeap<>(9);
        Assert.assertEquals("Size of a heap initialized with an item should be 1", 1, heap.getSize());
    }
}
