package com.zz.leetcode.problem.p736_parse_lisp_expression;

import java.util.HashMap;
import java.util.Map;

public class Problem736 {
    public static void main(String[] args) {
        String p = "(add 1 2)";
        Solution s = new Solution();
        System.out.println(s.evaluate("(add 1 2)"));

        System.out.println(s.evaluate("(mult 3 (add 2 3))"));

        System.out.println(s.evaluate("(let x 2 (mult x 5))"));

        System.out.println(s.evaluate("(let x 2 (mult x (let x 3 y 4 (add x y))))"));

        System.out.println(s.evaluate("(let x 3 x 2 x)"));

        System.out.println(s.evaluate("(let x 1 y 2 x (add x y) (add x y))"));

        System.out.println(s.evaluate("(let x 2 (add (let x 3 (let x 4 x)) x))"));

        System.out.println(s.evaluate("(let a1 3 b2 (add a1 1) b2) "));

        System.out.println(s.evaluate("(let x 7 -12)"));
    }
}

class Solution {

    public int evaluate(String expression) {
        Expr e = read(expression);
        return eva(e);
    }

    private Expr read(String s) {
        char begin = s.charAt(0);
        Expr expr = new Expr();
        if ((begin >= '0' && begin <= '9') || begin == '-') {
            expr.type = 1;
            expr.value = Integer.parseInt(s);
        }
        if (begin >= 'a' && begin <= 'z') {
            expr.type = 2;
            expr.varName = s;
        }
        if (begin == '(') {
            Piece op = readPiece(s, 1);
            expr.type = 0;
            expr.operation = op.p;
            if ("add".equals(op.p) || "mult".equals(op.p)) {
                Piece p1 = readPiece(s, op.end + 1);
                Piece p2 = readPiece(s, p1.end + 1);
                Expr e1 = read(p1.p);
                Expr e2 = read(p2.p);
                e1.parent = expr;
                e2.parent = expr;
                expr.varMap.put("1v", e1);
                expr.varMap.put("2v", e2);
            }
            if ("let".equals(op.p)) {
                int end = op.end;
                while (true) {
                    Piece p1 = readPiece(s, end + 1);
                    Expr e1 = read(p1.p);
                    char ec = s.charAt(p1.end);
                    if (ec == ')') {
                        e1.parent = expr;
                        expr.varMap.put("0v", e1);
                        break;
                    } else {
                        Piece p2 = readPiece(s, p1.end + 1);
                        Expr e2 = read(p2.p);
                        e2.parent = expr;

                        int v2 = eva(e2);
                        Expr saveV2 = new Expr();
                        saveV2.type = 1;
                        saveV2.value = v2;
                        end = p2.end;
                        expr.varMap.put(e1.varName, saveV2);
                    }
                }
            }
        }
        return expr;
    }

    private Piece readPiece(String s, int start) {
        int p = start;
        if (s.charAt(p) == '(') {
            int deep = 1;
            while(deep > 0) {
                p ++;
                if (s.charAt(p) == '(') deep ++;
                if (s.charAt(p) == ')') deep --;
            }
            p ++;
        } else {
            while (s.charAt(p) != ' ' && s.charAt(p) != ')') {
                p++;
            }
        }
        Piece piece = new Piece();
        piece.end = p;
        piece.p = s.substring(start, p);
        return piece;
    }

    private int eva(Expr e) {
        if (e.type == 1) return e.value;
        if (e.type == 2) {
            Expr p = e;
            while (true) {
                if (p.varMap.containsKey(e.varName)) {
                    return eva(p.varMap.get(e.varName));
                } else {
                    p = p.parent;
                }
            }
        }
        if (e.type == 0) {
            if ("add".equals(e.operation)) {
                Expr e1 = e.varMap.get("1v");
                Expr e2 = e.varMap.get("2v");
                return eva(e1) + eva(e2);
            }
            if ("mult".equals(e.operation)) {
                Expr e1 = e.varMap.get("1v");
                Expr e2 = e.varMap.get("2v");
                return eva(e1) * eva(e2);
            }
            if ("let".equals(e.operation)) {
                Expr e0 = e.varMap.get("0v");
                return eva(e0);
            }
        }
        return 0;
    }

    class Expr {
        int type; // 0: expr 1: number 2: variable
        String varName;
        String operation; //add mult let
        Expr parent;
        Map<String, Expr> varMap = new HashMap<>();
        int value;
    }

    class Piece {
        int end;
        String p;
    }
}
