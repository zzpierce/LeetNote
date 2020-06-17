package com.zz.leetcode.contest.s193.pc_minimum_number_of_days;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pierce
 */
public class P3 {
    public static void main(String[] args) {
        int[] b = new int[]{1,10,3,10,2};
        Solution s = new Solution();
        System.out.println(s.minDays(b, 3, 1));
        System.out.println(s.minDays(b, 3, 2));
        b=new int[]{7,7,7,7,12,7,7};
        System.out.println(s.minDays(b,2,3));
        b=new int[]{1000000000,1000000000};
        System.out.println(s.minDays(b,1,1));
        b=new int[]{1,10,2,9,3,8,4,7,5,6};
        System.out.println(s.minDays(b,4,2));
    }
}

class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        List<B> bs = new ArrayList<>(bloomDay.length);
        for (int i=0;i<bloomDay.length;i++) {
            bs.add(new B(bloomDay[i], i));
        }
        bs.sort((a, b)->{
            return a.v - b.v;
        });
        List<C> cs = new ArrayList<>(bloomDay.length);
        for (int i : bloomDay) {
            cs.add(new C(i, null));
        }

        int fc = 0;
        for (B b : bs) {
            int id = b.id;
            C c = cs.get(id);
            if (id == 0 || cs.get(id-1).h == null) {
                c.h = new H(1);
                fc += 1/k; if (fc >= m) return b.v;
            } else if (cs.get(id-1).h != null) {
                C cp = cs.get(id -1);
                c.h = getH(cp);
                fc -= (c.h.len)/k;
                c.h.len ++;
                fc += c.h.len/k; if (fc >= m) return b.v;
            }

            if (id != bs.size() - 1 && cs.get(id+1).h != null) {
                C ca = cs.get(id+1);
                H hca = getH(ca);
                H hc = getH(c);
                hca.h = hc;
                fc -= hc.len/k;
                fc -= hca.len/k;
                hc.len += hca.len;
                fc += hc.len/k; if (fc >= m) return b.v;
            }
        }
        return -1;
    }

    private H getH(C c) {
        H h = c.h;
        while (h.h != null) {
            h = h.h;
        }
        c.h = h;
        return h;
    }

    class B {
        int v;int id;
        B(int v, int id) {
            this.v=v;this.id=id;
        }
    }
    class C {
        int v;H h; C(int v,H h){this.v=v;this.h=h;}
    }
    class H {
        int len;
        H h;
        H(int len) {
            this.len =len;
        }
    }
}