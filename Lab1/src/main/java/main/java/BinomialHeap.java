package main.java;

public class BinomialHeap<T extends Comparable<? super T>> {
    private int size;
    private Node<T>[] trees;

    public static class Node<T> {
        public T element;
        public Node<T> leftChild;
        public Node<T> nextSibling;

        Node(T el) {
            this(el, null, null);
        }

        Node(T el, Node<T> lt, Node<T> nt) {
            element = el;
            leftChild = lt;
            nextSibling = nt;
        }
    }

    public BinomialHeap() {
        clear();
    }

    public BinomialHeap(T item) {
        size = 1;
        trees = new Node[1];
        trees[0] = new Node<>(item);
    }

    public int getSize() {
        return size;
    }

    public Node<T>[] getTrees() {
        return trees;
    }

    public void clear() {
        size = 0;
        trees = new Node[1];
        trees[0] = null;
    }

    public void merge(BinomialHeap<T> secondHeap) {
        size += secondHeap.size;
        if (size > capacity()) {
            int maxLength = Math.max(trees.length, secondHeap.trees.length);
            expandTrees(maxLength + 1);
        }
        Node<T> carry = null;
        for (int i = 0, j = 1; j <= size; i++, j *= 2) {
            Node<T> t1 = trees[i];
            Node<T> t2 = i < secondHeap.trees.length ? secondHeap.trees[i] : null;

            if (t1 == null && t2 == null && carry != null) {
                trees[i] = carry;
                carry = null;
            } else if (t1 != null && t2 == null && carry != null) {
                carry = combineTrees(t1, carry);
                trees[i] = null;
            } else if (t1 == null && t2 != null) {
                if (carry == null) {
                    trees[i] = t2;
                } else {
                    carry = combineTrees(t2, carry);
                }
                secondHeap.trees[i] = null;
            } else if (t1 != null && t2 != null) {
                if (carry == null) {
                    carry = combineTrees(t1, t2);
                    trees[i] = null;
                } else {
                    trees[i] = carry;
                    carry = combineTrees(t1, t2);
                }
                secondHeap.trees[i] = null;
            }
        }
        secondHeap.clear();
    }

    private int capacity() {
        return (int) Math.pow(2, trees.length) - 1;
    }

    private void expandTrees(int newNumTrees) {
        Node<T>[] old = trees;
        int oldNumTrees = old.length;
        trees = new Node[newNumTrees];
        for (int i = 0; i < Math.min(oldNumTrees, newNumTrees); i++) {
            trees[i] = old[i];
        }
        for (int i = oldNumTrees; i < newNumTrees; i++) {
            trees[i] = null;
        }
    }

    private Node<T> combineTrees(Node<T> t1, Node<T> t2) {
        if (t1.element.compareTo(t2.element) > 0) {
            return combineTrees(t2, t1);
        }
        t2.nextSibling = t1.leftChild;
        t1.leftChild = t2;
        return t1;
    }

    public void insert(T x) {
        merge(new BinomialHeap<>(x));
    }

    private int findMinIndex() {
        int minIndex = 0;
        for (int i = 0; i < trees.length; i++) {
            if (trees[minIndex] == null || trees[i] != null && trees[i].element.compareTo(trees[minIndex].element) < 0) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public T removeSmallest() {
        if (size == 0) return null;

        int minIndex = findMinIndex();
        T minItem = trees[minIndex].element;

        BinomialHeap<T> detachedHeap = new BinomialHeap<>();
        detachedHeap.expandTrees(minIndex + 1);
        detachedHeap.size = (int) Math.pow(2, minIndex) - 1;

        Node<T> subTree = trees[minIndex].leftChild;
        for (int j = minIndex - 1; j >= 0; j--) {
            detachedHeap.trees[j] = subTree;
            subTree = subTree.nextSibling;
            detachedHeap.trees[j].nextSibling = null;
        }

        trees[minIndex] = null;
        size -= detachedHeap.size + 1;
        merge(detachedHeap);
        return minItem;
    }
}