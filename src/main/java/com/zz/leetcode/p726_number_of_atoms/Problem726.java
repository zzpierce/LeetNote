package com.zz.leetcode.p726_number_of_atoms;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class Problem726 {

    public static void main(String[] args) {
        Solution s = new Solution();
        String st = s.countOfAtoms("K4(ON(SO3)2)2");

        System.out.println(st);

    }

}

class Solution {


    public String countOfAtoms(String formula) {
        int p = 0;
        Stack<String> stack = new Stack<>();
        stack.push("");

        for (int i = 0; i < formula.length(); i ++) {
            if (formula.charAt(i) == '(') {
                //update old top
                String top = stack.pop() + formula.substring(p, i);
                stack.push(top);
                //new top
                stack.push("");
                p = i + 1;
            } else if (formula.charAt(i) == ')') {
                if (isDigit(formula.charAt(i + 1))) {

                    String top = stack.pop() + formula.substring(p, i);
                    Map<String, Integer> topMap = countEleSimple(top);
                    p = i + 1;
                    Elem d = readEle(formula, p);
                    if (d != null && d.type == 0) {
                        topMap.forEach((k, v) -> topMap.put(k, v * Integer.valueOf(d.data)));
                        p += d.data.length();
                        i = p - 1;
                    }
                    stack.push(stack.pop() + simpleStr(topMap));

                }
            } else if (i == formula.length() - 1) {
                String top = stack.pop() + formula.substring(p, i + 1);
                stack.push(top);
            }
        }

        return simpleStr(countEleSimple(stack.pop()));
    }

    public String simpleStr(Map<String, Integer> m) {
        StringBuilder b = new StringBuilder();
        for (String k : m.keySet()) {
            b.append(k);
            if (m.get(k) != 1) {
                b.append(m.get(k));
            }
        }
        return b.toString();
    }

    public Map<String, Integer> countEleSimple(String f) {
        int i = 0;
        Map<String, Integer> resMap = new TreeMap<>();
        Map<String, Integer> tMap = new TreeMap<>();
        Elem e;
        while ((e = readEle(f, i)) != null) {
            i += e.data.length();
            if (e.type == 1) {
                join(resMap, tMap);
                tMap.clear();
                tMap.put(e.data, 1);
            } else if (e.type == 0) {
                for (String k : tMap.keySet()) {
                    tMap.put(k, tMap.get(k) * Integer.valueOf(e.data));
                }
            }
        }
        join(resMap, tMap);
        tMap.clear();
        return resMap;
    }

    private Elem readEle(String f, int i) {
        if (i >= f.length()) return null;
        char c = f.charAt(i);
        if (isDigit(c)) {
            int j;
            for (j = i + 1; j < f.length(); j ++) {
                if (!isDigit(f.charAt(j))) {
                    break;
                }
            }
            return new Elem(f.substring(i, j), 0);
        }
        if (isUpper(c)) {
            int j;
            for (j = i + 1; j < f.length(); j ++) {
                if (!isLower(f.charAt(j))) {
                    break;
                }
            }
            return new Elem(f.substring(i, j), 1);
        }
        return new Elem(String.valueOf(c), 2);
    }

    private void join(Map<String, Integer> resMap, Map<String, Integer> tMap) {
        for (String k : tMap.keySet()) {
            if (resMap.containsKey(k)) {
                resMap.put(k, resMap.get(k) + tMap.get(k));
            } else {
                resMap.put(k, tMap.get(k));
            }
        }
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isLower(char c) {
        return c >= 'a' && c <= 'z';
    }

    private boolean isUpper(char c) {
        return c >='A' && c <= 'Z';
    }

    class Elem {
        String data;
        int type;

        Elem(String data, int type) {
            this.data = data;
            this.type = type;
        }
    }
}