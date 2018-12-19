package com.zz.lib;

/**
 * heap basic implementation
 * could be used as priority queue
 * @param <E> elementType
 */

public class Heap<E> {

    private Object[] array;
    private boolean bigTop;
    private int p;

    public Heap(int size, boolean bigTop) {
        array = new Object[size];
        this.bigTop = bigTop;
        p = 0;
    }

    @SuppressWarnings("unchecked")
    public void insert(E e) {
        array[++ p] = e;
        int fp = p;
        while (fp > 1) {
            if (compare((E) array[fp / 2], (E) array[fp])) {
                swap(fp, fp / 2);
                fp = fp / 2;
            } else {
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public E extract() {
        if (p < 1) return null;
        E top = (E) array[1];
        if (p > 1) {
            array[1] = array[p --];
            int fp = 1;
            while (true) {
                int go = 0;
                int c = fp * 2;
                if (c <= p && compare((E) array[fp], (E) array[c])) {
                    go = 1;
                }
                if (c + 1 <= p && compare((E) array[c], (E) array[c + 1]) && compare((E) array[fp], (E) array[c + 1])) {
                    go = 2;
                }
                if (go == 0) {
                    break;
                } else {
                    swap(fp, c + go - 1);
                    fp = c + go - 1;
                }
            }
        }
        return top;
    }

    @SuppressWarnings("unchecked")
    private void swap(int i, int j) {
        E x = (E) array[i];
        array[i] = array[j];
        array[j] = x;
    }

    private boolean compare(E a, E b) {
        if (bigTop) {
            return (Integer)a < (Integer)b;
        } else {
            return (Integer)a > (Integer)b;
        }

    }

    public static void main(String[] args) {
        Heap<Integer> h = new Heap<>(200, true);
        for (int i = 0; i < 100; i ++) {
            h.insert((int)(Math.random() * 1000));
        }
        for (int i = 0; i < 100; i ++) {
            System.out.println(h.extract());
        }
    }
}

