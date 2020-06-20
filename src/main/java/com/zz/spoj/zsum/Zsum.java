package com.zz.spoj.zsum;

import com.zz.lib.BasicMath;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author pierce
 */
public class Zsum {
}

class Main {

    public static void main(String[] args) {
        int M = 10000007;
        FastScanner scanner = new FastScanner();
        while (true) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            if (n == k && n == 0) break;
            long p1 = binPow(n, k, M);
            long p2 = binPow(n - 1, k, M);
            long p3 = binPow(n, n, M);
            long p4 = binPow(n - 1, n - 1, M);
            long ans = (p1 + 2 * p2 + p3 + 2 * p4) % M;
            System.out.println(ans);
        }
    }

    public static long binPow(int a, int b, int m) {
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
