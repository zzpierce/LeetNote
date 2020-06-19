package com.zz.spoj.lastdig;

import com.zz.lib.BasicMath;

import java.util.Scanner;

public class Lastdig {
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t -- > 0) {
            int a = scanner.nextInt(); int b = scanner.nextInt();
            int p = (int) BasicMath.binPow(a, b, 10);
            System.out.println(p);
        }
    }
}