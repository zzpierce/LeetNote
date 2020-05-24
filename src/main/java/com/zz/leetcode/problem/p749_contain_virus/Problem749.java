package com.zz.leetcode.problem.p749_contain_virus;

import com.zz.util.ReadUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A virus is spreading rapidly, and your task is to quarantine the infected area by installing walls.
 *
 * The world is modeled as a 2-D array of cells, where 0 represents uninfected cells, and 1 represents cells contaminated with the virus. A wall (and only one wall) can be installed between any two 4-directionally adjacent cells, on the shared boundary.
 *
 * Every night, the virus spreads to all neighboring cells in all four directions unless blocked by a wall. Resources are limited. Each day, you can install walls around only one region -- the affected area (continuous block of infected cells) that threatens the most uninfected cells the following night. There will never be a tie.
 *
 * Can you save the day? If so, what is the number of walls required? If not, and the world becomes fully infected, return the number of walls used.
 *
 * Example 1:
 * Input: grid =
 * [[0,1,0,0,0,0,0,1],
 *  [0,1,0,0,0,0,0,1],
 *  [0,0,0,0,0,0,0,1],
 *  [0,0,0,0,0,0,0,0]]
 * Output: 10
 * Explanation:
 * There are 2 contaminated regions.
 * On the first day, add 5 walls to quarantine the viral region on the left. The board after the virus spreads is:
 *
 * [[0,1,0,0,0,0,1,1],
 *  [0,1,0,0,0,0,1,1],
 *  [0,0,0,0,0,0,1,1],
 *  [0,0,0,0,0,0,0,1]]
 *
 * On the second day, add 5 walls to quarantine the viral region on the right. The virus is fully contained.
 *
 */
public class Problem749 {

    public static void main(String[] args) {
        Solution s = new Solution();
        //int[][] g = ReadUtil.read("[[0,1,0,0,0,0,1,1],[0,1,0,0,0,0,1,1],[0,0,0,0,0,0,1,1],[0,0,0,0,0,0,0,1]]");
        int[][] g = ReadUtil.read("[[0,1,0,1,1,1,1,1,1,0],[0,0,0,1,0,0,0,0,0,0],[0,0,1,1,1,0,0,0,1,0],[0,0,0,1,1,0,0,1,1,0],[0,1,0,0,1,0,1,1,0,1],[0,0,0,1,0,1,0,1,1,1],[0,1,0,0,1,0,0,1,1,0],[0,1,0,1,0,0,0,1,1,0],[0,1,1,0,0,1,1,0,0,1],[1,0,1,1,0,1,0,1,0,1]]");
        s.containVirus(g);
    }

}

/**
 * [6.5]非常繁琐的模拟过程
 * 需要注意的是
 * 每次封锁的是影响的区域最大的感染区
 * 而不是需要墙最多的感染区
 */
class Solution {

    Set<Region> rs;
    int x;
    int y;
    P[][] pg;

    public int containVirus(int[][] grid) {

        this.x = grid.length;
        this.y = grid[0].length;
        pg = new P[x][y];
        this.rs = new HashSet<>();

        for (int i = 0; i < x; i ++) {
            for (int j = 0; j < y; j ++) {
                pg[i][j] = new P(grid[i][j], i, j);
            }
        }

        int w = 0;
        while (!noSafeZone() && initRegion() > 0) {
//            printP();
            w += wall();
//            System.out.println("wall: " + w);
//            printP();
            spread();
//            printP();
            infect();

        }
        System.out.println(w);
        return w;
    }

    private boolean noSafeZone() {
        for (int i = 0; i < x; i ++) {
            for (int j = 0; j < y; j++) {
                P p = pg[i][j];
                if (p.v == 0) return false;
            }
        }
        return true;
    }

    private void printP() {
        for (int i = 0; i < x; i ++) {
            for (int j = 0; j < y; j ++) {
                P p = pg[i][j];
                System.out.print(" " + p.v + " " + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private void spread() {
        for (Region r : rs) {
            int size = r.member.size();
            for (int i = 0; i < size; i ++) {
                P p = r.member.get(i);
                if (p.v == 1) {
                    spreadTo(p.x - 1, p.y);
                    spreadTo(p.x, p.y - 1);
                    spreadTo(p.x + 1, p.y);
                    spreadTo(p.x, p.y + 1);
                }
            }
        }
    }

    private void spreadTo(int i, int j) {
        if (i >= 0 && i < x && j >= 0 && j < y) {
            P p = pg[i][j];
            if (p.v == 0) {
                p.v = 2;
            }
        }
    }

    private void infect() {
        for (int i = 0; i < x; i ++) {
            for (int j = 0; j < y; j ++) {
                P p = pg[i][j];
                if (p.v == 2) p.v = 1;
            }
        }
    }

    private int wall() {
        Region rm = null;
        int longest = -1;
        int theWall = 0;
        for (Region r : rs) {
            int currentLen = 0;
            int currentWall = 0;
            for (P p : r.member) {
                D d1 = checkP(r, p.x + 1, p.y);
                currentLen += d1.b; currentWall += d1.a;
                D d2 = checkP(r, p.x - 1, p.y);
                currentLen += d2.b; currentWall += d2.a;
                D d3 = checkP(r, p.x, p.y + 1);
                currentLen += d3.b; currentWall += d3.a;
                D d4 = checkP(r, p.x, p.y - 1);
                currentLen += d4.b; currentWall += d4.a;
            }
            if (currentLen > longest) {
                longest = currentLen;
                theWall = currentWall;
                rm = r;
            }
        }
        for (P p : rm.member) {
            p.v = 3;
        }
        return theWall;
    }

    private D checkP(Region r, int i, int j) {
        D d = new D(0, 0);
        if (i >= 0 && i < x && j >= 0 && j < y) {
            P p = pg[i][j];
            if (p.v == 0) {
                d.a = 1;
            }
            if (p.v == 0 && p.r != r) {
                p.r = r;
                d.b = 1;
            }
        }
        return d;
    }

    private int initRegion() {
        rs.clear();
        for (int i = 0; i < x; i ++) {
            for (int j = 0; j < y; j ++) {
                pg[i][j].r = null;
            }
        }
        for (int i = 0; i < x; i ++) {
            for (int j = 0; j < y; j ++) {
                P p = pg[i][j];
                if (p.v == 1 && p.r == null) {
                    Region r = new Region();
                    rs.add(r);
                    addToRegion(r, i, j);
                }
            }
        }
        return rs.size();
    }

    private void addToRegion(Region r, int i, int j) {
        if (i >= 0 && i < x && j >= 0 && j < y) {
            P p = pg[i][j];
            if (p.v == 1 && p.r == null) {
                p.r = r;
                r.member.add(p);
                addToRegion(r, i - 1, j);
                addToRegion(r, i, j - 1);
                addToRegion(r, i + 1, j);
                addToRegion(r, i, j + 1);
            }
        }
    }

    static class Region {
        Region() {
            this.member = new ArrayList<>();
        }
        List<P> member;
    }

    static class P {
        int v;  //0 clear 1 virus 2 spread 3 contain
        int x; int y;
        Region r;
        P(int v, int a, int b) {
            x = a; y = b;
            this.v = v;
        }
    }

    class D {
        int a, b;
        D(int a, int b) {this.a = a; this.b = b;}
    }
}