package leetcode.p354_russian_doll_envelopes;

import java.util.*;

/**
 * @author pierce
 */
public class Problem354 {
    public static void main(String[] args) {
        int[][] ma = new int[1][];
        int d = new Solution().maxEnvelopes(ma);

        System.out.println(d);
    }
}


class Solution {

    public int maxEnvelopes(int[][] envelopes) {

        if (envelopes.length == 0) {
            return 0;
        }

        List<P> ps = new ArrayList<>(envelopes.length);
        for (int[] e : envelopes) {
            ps.add(new P(e[0], e[1]));
        }

        Collections.sort(ps, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                if (o1.w < o2.w) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

//        for (P p : ps) {
//            System.out.println(p.w + "-" + p.h);
//        }

        P[] pArray = new P[ps.size()];
        ps.toArray(pArray);
        pArray[0].level = 1;
        int maxLevel = 1;
        for (int i = 0; i < pArray.length; i ++) {
            for (int j = i - 1; j >= 0; j --) {
                if (pArray[i].w > pArray[j].w && pArray[i].h > pArray[j].h) {
                    if (pArray[j].level + 1 > pArray[i].level) {
                        pArray[i].level = pArray[j].level + 1;
                        if (pArray[i].level > maxLevel) {
                            maxLevel = pArray[i].level;
                        }
                    }
                }
            }
        }

        return maxLevel;
    }

    class P {
        int w;
        int h;
        int level;
        P(int w, int h) {
            this.w = w;
            this.h = h;
            this.level = 1;
        }
    }
}