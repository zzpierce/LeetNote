package leetcode.p224_basic_calculator;

import java.util.*;

/**
 * @author pierce
 */
public class Problem224 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int v = s.calculate("1 - 2 + 3+(33-1)-1 ");

        System.out.println(v);
    }
}

class Solution {

    public int calculate(String s) {

        List<E> eList = readString(s);
        Stack<E> stack = new Stack<>();
        int size = eList.size();
        for (int i = size - 1; i >= 0; i --) {
            E e = eList.get(i);
            if ((e.s && e.v == 3 || i == 0)) {
                while (true) {
                    E e1 = stack.pop();
                    if (stack.empty()) {
                        return e1.v;
                    }
                    E es = stack.pop();
                    if (es.v == 4) {
                        stack.push(e1);
                        break;
                    } else {
                        E e2 = stack.pop();
                        if (es.v == 1) {
                            stack.push(new E(e1.v + e2.v, false));
                        } else {
                            stack.push(new E(e1.v - e2.v, false));
                        }
                    }
                }
            } else {
                stack.push(e);
            }
        }
        return 0;
    }

    private List<E> readString(String s) {
        char[] cs = s.toCharArray();
        List<E> res = new ArrayList<>();
        //add extra one
        res.add(new E(0, false));

        int p1 = 0, p2 = 0;
        while (p1 < cs.length) {
            if (cs[p1] == '+' || cs[p1] == '-' || cs[p1] == '(' || cs[p1] == ')') {
                if (p1 - p2 > 0) {
                    String str = s.substring(p2, p1).trim();
                    if (!str.equals("")) {
                        int v = Integer.valueOf(str);
                        res.add(new E(v, false));
                    }
                }
                res.add(new E(cmap.get(cs[p1]), true));
                ++ p1;
                p2 = p1;
            } else {
                p1 ++;
            }
        }
        if (p1 - p2 > 0) {
            String str = s.substring(p2, p1).trim();
            if (!str.equals("")) {
                res.add(new E(Integer.valueOf(str), false));
            }
        }

        return res;
    }

    static Map<Character, Integer> cmap = new HashMap<>();
    static {
        cmap.put('+', 1);
        cmap.put('-', 2);
        cmap.put('(', 3);
        cmap.put(')', 4);
    }

    class E {

        int v; //+ 1 - 2 ( 3 ) 4
        boolean s;
        E(int v, boolean s) {
            this.v = v;
            this.s = s;
        }
    }
}
