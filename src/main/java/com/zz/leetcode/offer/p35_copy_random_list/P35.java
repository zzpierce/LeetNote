package com.zz.leetcode.offer.p35_copy_random_list;

import java.util.HashMap;
import java.util.Map;

public class P35 {
}


class Solution {

    private Map<Node, Node> m = new HashMap<>();
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node p = head;
        Node qHead = new Node(head.val);
        Node q = qHead;
        m.put(head, q);
        while (p != null) {
            if (p.next != null) {
                if (m.containsKey(p.next)) {
                    q.next = m.get(p.next);
                } else {
                    q.next = new Node(p.next.val);
                    m.put(p.next, q.next);
                }
            }
            if (p.random != null) {
                if (m.containsKey(p.random)) {
                    q.random = m.get(p.random);
                } else {
                    q.random = new Node(p.random.val);
                    m.put(p.random, q.random);
                }
            }
            p = p.next;
            q = q.next;
        }
        return qHead;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}