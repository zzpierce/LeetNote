package com.zz.lib;

import java.util.ArrayList;
import java.util.List;

/**
 * 质数计算
 */
public class PrimeFunction {

    /**
     * return prime less than r
     */
    public static List<Integer> primeLess(int r) {
        int[] p = new int[r + 1];
        List<Integer> prime = new ArrayList<>();
        for (int i = 2; i * i <= r; i ++) {
            if (p[i] == 0) {
                for (int j = i * i; j <= r; j += i) {
                    p[j] = 1;
                }
            }
        }
        for (int i = 2; i <= r; i ++) {
            if (p[i] == 0) prime.add(i);
        }
        return prime;
    }

    /**
     * return prime between l and r
     */
    public static List<Integer> primeBetween(int l, int r) {
        List<Integer> prime = primeLess(sqrt(r));
        int[] arr = new int[r - l + 1];
        for (int p : prime) {
            for (int i = Math.max(p * p, (l + p - 1) / p * p); i <= r; i += p) {
                arr[i - l] = 1;
            }
        }
        List<Integer> primeIn = new ArrayList<>();
        for (int i = 0; i < arr.length; i ++) {
            if (arr[i] == 0 && i + l > 1) primeIn.add(i + l);
        }
        return primeIn;
    }

    private static int sqrt(int b) {
        double d = Math.sqrt((double)b);
        return (int)d + 1;
    }

//    public static void main(String[] args) {
//        List<Integer> p = PrimeFunction.primeBetween(1, 10);
//        System.out.println(p);
//    }

}
