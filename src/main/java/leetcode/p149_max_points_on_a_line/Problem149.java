package leetcode.p149_max_points_on_a_line;

import java.awt.*;

/**
 * @author pierce
 */
public class Problem149 {
    public static void main(String[] args) {
        Point[] ps = new Point[4];
        ps[0] = new Point(0, 0);
        ps[1] = new Point(1,0);
        ps[2] = new Point(0, -70);
        ps[3] = new Point(0, -70);

        int d = new Solution().maxPoints(ps);

        System.out.println(d);

        int s = 65536;
        int m = s * s;

        System.out.println(m);
    }
}

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
class Solution {
    public int maxPoints(Point[] points) {
        if (points.length < 3) {
            return points.length;
        }
        // if all the points are same
        boolean same = true;
        for (int i = 1; i < points.length; i ++) {
            if (!samePoint(points[0], points[i])) {
                same = false; break;
            }
        }
        if (same) return points.length;

        int ans = 0;
        for(int i = 0; i < points.length; i ++) {
            for (int j = i + 1; j < points.length; j ++) {
                if (samePoint(points[i], points[j])) {
                    continue;
                }
                int v = 2;
                for (int k = 0; k < points.length; k ++) {
                    if (k != i && k != j && line(points[i], points[j], points[k])) {
                        v ++;
                    }
                }
                if (v > ans) ans = v;
            }
        }
        return ans;
    }

    private boolean samePoint(Point a, Point b) {
        return a.x == b.x && a.y == b.y;
    }

    private boolean line(Point a, Point b, Point c) {
        if (((long)a.x - b.x) * ((long)a.y - c.y) == ((long)a.y - b.y) * ((long)a.x - c.x)) return true;
        return false;
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
        x = a; y = b;
    }
}
