package com.zz.c2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author pierce
 */
public class P {
    public static void main(String[] args) {
        String[] n = new String[]{"kaido","kaido(1)","kaido","kaido(1)","kaido(2)"};
        Solution s = new Solution();
        String[] t =s.getFolderNames(n);
        System.out.println(t);
    }

}

class Solution {

    public String[] getFolderNames(String[] names) {
        Map<String, Integer> s = new HashMap<>();
        String[] r = new String[names.length];
        for (int i = 0; i < names.length; i ++) {
            String name = names[i];
            if (!s.containsKey(name)) {
                s.put(name, 1);
                r[i] = name;
            } else {
                int p = s.get(name);
                String nr = name + "(" + p + ")";
                while (s.containsKey(nr)) {
                    p ++;
                    nr = name + "(" + p + ")";
                }
                s.put(name, p + 1);
                s.put(nr, 1);
                r[i] = nr;
            }
        }
        return r;
    }
}

