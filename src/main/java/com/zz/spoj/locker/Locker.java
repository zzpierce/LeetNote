package com.zz.spoj.locker;

import com.zz.lib.BasicMath;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Locker {
}

class Main {
    public static void main(String[] args) {
        int M = 1000000007;
        FastScanner scanner = new FastScanner();
        int t = scanner.nextInt();
        while (t -- > 0) {
            long r = scanner.nextLong();
            if (r == 1) {
                System.out.println(1);
                continue;
            }
            long m = r % 3;
            long ans = 0;
            if (m == 1) {
                ans = binPow(3, r / 3 - 1, M);
                ans = (ans * 4) % M;
            }
            if (m == 0) {
                ans = binPow(3, r / 3, M);
            }
            if (m == 2) {
                ans = binPow( 3, r / 3, M);
                ans = (ans * 2) % M;
            }
            System.out.println(ans);
        }
    }

    public static long binPow(int a, long b, int m) {
        b = b % (m - 1);
        long p = 1L;
        long e = a;
        while (b > 0) {
            if ((b & 1) == 1) p = (p * e) % m;
            e = (e * e) % m;
            b >>= 1;
        }
        return p;
    }


    public static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner(String s) {
            try {
                br = new BufferedReader(new FileReader(s));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String nextToken() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(nextToken());
        }

        long nextLong() {
            return Long.parseLong(nextToken());
        }

        double nextDouble() {
            return Double.parseDouble(nextToken());
        }
    }
}
