package com.zz.leetcode.offer.p30_min_stack;

import java.util.Stack;

/**
 * @author pierce
 */
public class P30 {
    public static void main(String[] args) {
        MinStack m = new MinStack();
        m.push(-2);
        m.push(0);
        m.push(-3);
        System.out.println(m.min());
        m.pop();
        System.out.println(m.min());
    }
}

class MinStack {

    private Stack<Integer> s = new Stack<>();
    private Stack<M> ms = new Stack<>();
    private M m = null;

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        s.push(x);
        if (m == null || x < m.val) {
            m = new M(x, 1); ms.push(m);
        } else if (x == m.val) {
            m.count ++;
        }
    }

    public void pop() {
        int i = s.pop();
        if (i == m.val) {
            if (m.count > 1) m.count --;
            else {
                ms.pop();
                if (ms.empty()) {
                    m = null;
                } else {
                    m = ms.peek();
                }
            }
        }
    }

    public int top() {
        return s.peek();
    }

    public int min() {
        return m.val;
    }

    static class M {
        int val;
        int count;
        M(int v, int c) {
            val = v; count = c;
        }
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
