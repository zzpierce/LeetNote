package com.zz.leetcode.problem.p587_erect_the_fence;

import com.zz.util.ReadUtil;

import java.util.*;

/**
 * @author pierce
 */
public class Problem587 {

    public static void main(String[] args) {

        String input1 = "[[0,3],[1,2],[2,1],[3,0],[4,0],[5,0],[6,1],[7,2],[7,3],[7,4],[6,5],[5,5],[4,5],[3,5],[2,5],[1,4]]";
        String input2 = "[[0,0],[0,5],[0,9],[2,2],[2,3],[2,4],[3,6],[4,0],[4,1],[4,9],[5,0],[5,1],[5,7],[6,0],[6,2],[6,5],[7,9],[8,1],[8,7],[9,1],[9,2],[9,5]]";


        int[][] points = ReadUtil.read(input1);
        Solution s = new Solution();
        int[][] p1 = s.outerTrees(points);
        System.out.println("");
        for (int i = 0; i < p1.length; i ++) {
            System.out.print("[" + p1[i][0] + "," + p1[i][1] + "],");
        }

        System.out.println("");
        int[][] p2 = ReadUtil.read(input2);
        p2 = s.outerTrees(p2);
        for (int i = 0; i < p2.length; i ++) {
            System.out.print("[" + p2[i][0] + "," + p2[i][1] + "],");
        }


    }

}


/**
 * Definition for a point.
 * class Point {
 * int x;
 * int y;
 * Point() { x = 0; y = 0; }
 * Point(int a, int b) { x = a; y = b; }
 * }
 */
class Solution {


    public int[][] outerTrees(int[][] points) {

        if (points.length <= 3) {
            return points;
        }
        //find mini point
        Point[] pointArray = new Point[points.length];
        for (int i = 0; i < points.length; i ++) {
            pointArray[i] = new Point(points[i][0], points[i][1]);
        }

        Point s = pointArray[0];
        for (Point p : pointArray) {
            if (p.x < s.x) s = p;
            else if (p.x == s.x && p.y < s.y) s = p;
        }
        //sort by angle
        List<Point> ps = Arrays.asList(pointArray);
        final Point start = s;
        ps.sort((Point p1, Point p2) -> compareK(start, p1, p2));
        int[] chosen = new int[ps.size()];
        chosen[0] = chosen[1] = 1;

        //System.out.println(ps);

        int p1 = 0, p2 = 1, p3 = 2;
        while(p3 < ps.size()) {
            int cs = cs(ps.get(p1), ps.get(p2), ps.get(p2), ps.get(p3));
            if (cs >= 0) {
                chosen[p3] = 1;
                p1 = p2; p2 = p3; p3 ++;
            } else {
                chosen[p2] = 0;
                while (chosen[p2] == 0) {p2 --;}
                p1 = p2 - 1;
                while (chosen[p1] == 0) {p1 --;}
            }
        }

        //check last
        if (cs(ps.get(p1), ps.get(p2), ps.get(p2), ps.get(0)) >= 0) {
            chosen[p2] = 1;
        } else {
            chosen[p2] = 0;
        }

        List<Point> fence = new ArrayList<>();
        for (int i = 0; i < chosen.length; i ++) {
            if (chosen[i] == 1) {
                fence.add(ps.get(i));
            }
        }

        int[][] f = new int[fence.size()][2];
        for (int i = 0; i < f.length; i ++) {
            f[i][0] = fence.get(i).x;
            f[i][1] = fence.get(i).y;
        }
        return f;
    }

    private int compareK(Point s, Point p1, Point p2) {
        if (s.equals(p1)) {
            return -1;
        }
        if (s.equals(p2)) {
            return 1;
        }
        double k1 = k(s, p1);
        double k2 = k(s, p2);
        if (k1 < k2) return -1;
        if (k1 == k2) {
            int dis = dis(s, p1) - dis(s, p2);
            if (k(s, p1) > 0) {
                return -dis;
            } else {
                return dis;
            }

        }
        return 1;
    }

    private double k(Point p1, Point p2) {

        int dx = p2.x - p1.x;
        int dy = p2.y - p1.y;
        if (dy == 0) return 0;
        if (dx == 0) return Double.MAX_VALUE;

        return (double)dy / dx;
    }

    private int cs(Point p1, Point p2, Point p3, Point p4) {
        int x1 = p2.x - p1.x;
        int x2 = p4.x - p3.x;
        int y1 = p2.y - p1.y;
        int y2 = p4.y - p3.y;
        int v = x1 * y2 - x2 * y1;
        return v;
    }

    private int dis(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

}

class Point {
    int x;
    int y;
    Point() {
        x = 0;
        y = 0;
    }
    Point(int a, int b) {
        x = a;
        y = b;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}