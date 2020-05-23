package com.zz.leetcode.problem.p460_lfu_cache;

import java.util.HashMap;
import java.util.Map;

//Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
//
//get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
//put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
//
//Note that the number of times an item is used is the number of calls to the get and put functions for that item since it was inserted. This number is set to zero when the item is removed.
//
// 
//
//Follow up:
//Could you do both operations in O(1) time complexity?
//
// 
//
//Example:
//
//LFUCache cache = new LFUCache( 2  capacity  );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // returns 1
//cache.put(3, 3);    // evicts key 2
//cache.get(2);       // returns -1 (not found)
//cache.get(3);       // returns 3.
//cache.put(4, 4);    // evicts key 1.
//cache.get(1);       // returns -1 (not found)
//cache.get(3);       // returns 3
//cache.get(4);       // returns 4

public class Problem460 {

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(3, 1);
        cache.put(2, 1);
        cache.put(2, 2);
        cache.put(4, 4);
        System.out.println(cache.get(2));       // returns 1
//        cache.put(3, 3);
//        System.out.println(cache.get(2));       // returns -1 (not found)
//        System.out.println(cache.get(3));       // returns 3.
//
//        cache.put(4, 4);
//        System.out.println(cache.get(1));       // returns -1 (not found)
//        System.out.println(cache.get(3));       // returns 3
//        System.out.println(cache.get(4));       // returns 4
//        System.out.println(cache.get(4));       // returns 4
    }
}

/**
 * [5.0]
 * 注意事项
 * FIFO
 * put的时候如果Cache已经满了，要先删除再put
 */
class LFUCache {

    private Map<Integer, Node> store = new HashMap<>();
    private int capacity;

    Node head;
    Node tail;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.head = this.tail = null;
    }

    public int get(int key) {
        if (store.containsKey(key)) {
            Node n = store.get(key);
            n.c ++;
            si(n);
            return n.v;
        } else {
            return -1;
        }
    }

    private void si(Node n) {
        Node p = n;
        while (p.pre != null && p.c >= p.pre.c) {
            Node pre = p.pre;

            if (p.next != null) {
                p.next.pre = pre;
            }
            if (pre.pre != null) {
                pre.pre.next = p;
            }
            pre.next = p.next;
            p.pre = pre.pre;

            pre.pre = p;
            p.next = pre;

            if (p == tail) tail = pre;
            if (pre == head) head = p;

        }
    }

    private void del() {
        if (store.size() == capacity) {
            if (tail != null) {
                if (head == tail) {
                    store.remove(tail.k);
                    head = tail = null;
                } else {
                    store.remove(tail.k);
                    tail = tail.pre;
                    tail.next = null;
                }
            }
        }
    }

    public void put(int key, int value) {
        if (capacity > 0) {
            if (store.containsKey(key)) {
                Node n = store.get(key);
                n.v = value;
                n.c++;
                si(n);
            } else {
                del();
                if (head == null) {
                    head = tail = new Node(key, value, null, null);
                    store.put(key, head);
                } else {
                    tail.next = new Node(key, value, null, null);
                    tail.next.pre = tail;
                    tail = tail.next;
                    store.put(key, tail);
                }
                tail.c++;
                Node p = tail;
                si(p);
            }
        }
    }

    class Node {
        public int k;
        public int v;
        public int c;
        public Node pre;
        public Node next;
        Node(int k, int v, Node pre, Node next) {
            this.k = k; this.v = v; this.pre = pre; this.next = next;
        }
    }

}

