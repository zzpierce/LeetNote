package leetcode.p432_all_o_one_data_structure;

import java.util.*;

/**
 * @author pierce
 */
public class Problem432 {
    public static void main(String[] args) {
        AllOne one = new AllOne();
        one.inc("hello");
        one.inc("world");
        one.inc("leet");
        one.inc("code");
        one.inc("DS");
        one.inc("leet");
        System.out.println(one.getMaxKey());
        one.inc("DS");
        one.dec("leet");
        System.out.println(one.getMaxKey());
        one.dec("DS");
        one.inc("hello");
        System.out.println(one.getMaxKey());
        one.inc("hello");
        one.inc("hello");
        one.dec("world");
        one.dec("leet");
        one.dec("code");
        one.dec("DS");
        System.out.println(one.getMaxKey());
        one.inc("new");
        one.inc("new");
        one.inc("new");
        one.inc("new");
        one.inc("new");
        one.inc("new");
        System.out.println(one.getMaxKey());
        System.out.println(one.getMinKey());
    }
}

class AllOne {

    MainNode head;
    MainNode tail;
    HashMap<String, Node> map;

    /** Initialize your data structure here. */
    public AllOne() {
        head = new MainNode(1);
        tail = head;
        map = new HashMap<>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (map.containsKey(key)) {

            Node n = map.get(key);
            n.val ++;
            MainNode mainNode = n.mainNode;
            MainNode pre = mainNode.pre;
            if (pre == null || pre.val != n.val) {
                MainNode newMain = new MainNode(n.val);
                newMain.keySet.add(key);
                newMain.pre = pre;
                newMain.next = mainNode;
                if (pre != null) {
                    pre.next = newMain;
                } else {
                    //set newMain head;
                    head = newMain;
                }
                mainNode.pre = newMain;
                n.mainNode = newMain;
            } else {
                n.mainNode = pre;
                pre.keySet.add(key);
            }

            mainNode.keySet.remove(key);
            if (mainNode.keySet.isEmpty()) {
                mainNode.pre.next = mainNode.next;
                if (mainNode == tail) {
                    tail = mainNode.pre;
                } else {
                    mainNode.next.pre = mainNode.pre;
                }
                mainNode.pre = null;
                mainNode.next = null;
            }
        } else {
            Node n = new Node();
            if (tail.val == 1) {
                n.mainNode = tail;
                tail.keySet.add(key);
            } else {
                MainNode newTail = new MainNode(1);
                tail.next = newTail;
                newTail.pre = tail;
                newTail.keySet.add(key);
                n.mainNode = newTail;
                tail = newTail;
            }
            map.put(key, n);
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            n.val --;
            MainNode mainNode = n.mainNode;
            MainNode next = mainNode.next;
            mainNode.keySet.remove(key);
            if (n.val == 0) {
                map.remove(key);
                n.mainNode = null;
                if (mainNode.keySet.isEmpty()) {
                    if (mainNode.pre != null) {
                        tail = mainNode.pre;
                        mainNode.pre.next = null;
                        mainNode.pre = null;
                    }
                }
            } else {
                if (next == null || next.val != n.val) {
                    MainNode newMain = new MainNode(n.val);
                    newMain.keySet.add(key);
                    newMain.next = next;
                    newMain.pre = mainNode;
                    if (next != null) {
                        next.pre = newMain;
                    } else {
                        tail = newMain;
                    }
                    mainNode.next = newMain;
                    n.mainNode = newMain;
                } else {
                    n.mainNode = next;
                    next.keySet.add(key);
                }

                if (mainNode.keySet.isEmpty()) {
                    mainNode.next.pre = mainNode.pre;
                    if (mainNode == head) {
                        head = mainNode.next;
                    } else {
                        mainNode.pre.next = mainNode.next;
                    }
                    mainNode.pre = null;
                    mainNode.next = null;
                }
            }

        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        Iterator<String> iterator = head.keySet.iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            return "";
        }
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        Iterator<String> iterator = tail.keySet.iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            return "";
        }
    }

    class MainNode {
        int val;
        MainNode pre;
        MainNode next;
        Set<String> keySet;
        MainNode(int val) {
            this.val = val;
            keySet = new HashSet<>();
        }
    }

    class Node {
        int val;
        MainNode mainNode;
        Node() {
            this.val = 1;
        }
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
