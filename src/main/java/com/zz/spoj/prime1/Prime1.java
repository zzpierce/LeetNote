package com.zz.spoj.prime1;

import com.zz.lib.PrimeFunction;

import java.util.List;
import java.util.Scanner;

public class Prime1 {
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t -- > 0) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            List<Integer> prime = PrimeFunction.primeBetween(l, r);
            for (int p : prime) {
                System.out.println(p);
            }
        }
    }
}