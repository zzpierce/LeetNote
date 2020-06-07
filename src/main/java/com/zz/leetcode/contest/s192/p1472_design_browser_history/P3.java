package com.zz.leetcode.contest.s192.p1472_design_browser_history;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pierce
 */
public class P3 {
    public static void main(String[] args) {
        BrowserHistory b = new BrowserHistory("es.com");
        b.visit("cg.com");
        b.visit("tp.com");
        b.back(9);
        b.visit("kt.com");
        b.forward(7);
        b.visit("cr.com");
        b.visit("iy.com");
        b.forward(5);
        b.visit("uu.com");
    }
}

class BrowserHistory {

    private List<String> his = new ArrayList<>();
    int p = 0;
    int c = 0;

    public BrowserHistory(String homepage) {
        his.add(homepage);
    }

    public void visit(String url) {
        if (c == p && c == his.size() - 1) {
            his.add(url); c ++; p ++;
        } else {
            his.set(++c, url);
            p = c;
        }
    }

    public String back(int steps) {
        if (c - steps < 0) c = 0;
        else c -= steps;
        return his.get(c);
    }

    public String forward(int steps) {
        if (c + steps > p) c = p;
        else c += steps;
        return his.get(c);
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */
