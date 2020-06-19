package com.zz.lib;

/**
 * 将String映射为数字
 */
public class StringHash {

    private static final long P = 31;
    private static final long M = 1000000009;

    public static long calcHash(String s) {
        long hv = 0;
        long pe = 1;
        for (int i = 0; i < s.length(); i ++) {
            int c = s.charAt(i) - 'a' + 1;
            hv = (hv + c * pe) % M;
            pe = (pe * P) % M;
        }
        return hv;
    }

}
