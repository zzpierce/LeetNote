package com.zz.spoj.p2_prime_generator;

import com.zz.lib.PrimeFunction;

import java.util.List;
import java.util.Scanner;

public class SP2 {
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