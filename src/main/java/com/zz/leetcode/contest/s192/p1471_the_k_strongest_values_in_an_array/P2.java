package com.zz.leetcode.contest.s192.p1471_the_k_strongest_values_in_an_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author pierce
 */
public class P2 {

    public static void main(String[] args) {
        Solution s =new Solution();
        int[] arr = new int[]{-277,-372,411,-185,135,-502,402,874,-965,-399,-865,-378,742,-891,683,-176,-105,-790,-294,-599,-732,416,338,605,-53,796,-345,-204,357,248,-865,-181,-928,-701,-243,-880,-102,-334,-505,408,471,-929,189,914,-261,250,514,687,309,417,-338,773,-219,-609,-321,-900,377,-846,-625,-960,-666,139,-898,-90,319,66,-640,73,877,665,227,-502,796,-339,-506,-387,-59,204,-974,-461,-152,-96,-255,-679,928,531,950,-221,232,-730,-536,941,-412,-731,-103,-914,-496,-830,-516,43,-882,108,666,-369,128,51,-553,19,374,634,869,-542,427,417,313,9,113,147,-217,-389,-920,-80,385,595,-992,354,513,354,90,-944,-744,-19,-365,3,907,-26,157,373,322,-641,73,-59,-734,-348,-466,296,-273,-458,404,-323,366,-976,394,149,823,-667,112,-32,-54,297,702,547,-144,930,974,537,-332,-291,144,-216,608,-55,778,-651,930,-814,625,125,564,414,-145,-376,970,765,-31,-804,-445,-831,-297,477,-686,-73,893,-527,-654,-96,-604,373,362,-812,724,-79,534,-330,-574,866,-354,744,-625,713,-192,578,-460,982,49,871,164,-101,150,-571,-130,-986,-178,-786,990,634,-348,-462,-475,32,894,-248,-371,-918,-678,-67,590,-979,-818,207,-389,172,-292,304,-311,966,-551,280,-938,740,693,-736,-627,-592,-397,-764,-67,706,481,242,-867,359,-777,-225,-359,-659,16,-445,486,737,209,92,-25,-98,-813,583,-159,-978,-554,462,-259,758,-130,981,444,-571,474,-864,784,737,626,-180,-289,-802,843,806,-33,729,-881,-304,843,892,-505,271,-698,-13,-33,648,-225,515,870,244,-204,-706,17,-238,192,938,-815,-109,762,-883,822,-467,613,-458,737,688,649,843,-796,573,-570,879,-489,-521,-284,-953,-403,-183,799,947,934,-464,332,389,492,-375,-407,-286,546,-945,761,318,723,187,908,-126,405,-133,-946,450,-650,-229,-619,518,-584,-862,-457,-797,141,-435,-386,-890,-748,-295,750,701,-57,458,-955,-858,454,78,-403,965,918,627,677,-862,-50,144,744,737,-548,-717,-210,922,-17,-226,418,-237,491,-965,663,605,730,370,-253,771,117,462,794,-953,-696,272,-916,-784,-912,132,-635,761,-507,-679,-580,493,-586,327,-579,334,337,-555,801,102,-312,-549,-982,433,651,-458,-181,335,631,-728,-934,402,958,518,867,137,394,-871,-683,-33,-216,-256,647,-460,-541,-671,610,-31,-381,453,-204,-547,930,-588,-202,60,-804,-675,-77,-698,-41,716,339,977,26,-174,-703,-485,-155,-376,-356,161,804,-454,996,805,915,674,252,-965,321,242,221,-90,-948,-426,239,573,749,770,977,-269,713,999,-908,45,843,-122,329,798,101,-428,68,91,-356,-139,-536,-844,372,990,558,199,424,851,181,87};
        int[] t = s.getStrongest(arr, 31);

        for (int i : t) {
            System.out.print(i + ",");
        }
    }


}

class Solution {

    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int mid = arr[(arr.length - 1) / 2];
        List<P> ps = new ArrayList<>();
        for (int i = 0; i < arr.length; i ++) {
            if (arr[i] >= mid) {
                ps.add(new P(arr[i] - mid, true));
            } else {
                ps.add(new P(mid - arr[i], false));
            }
        }
        ps.sort(new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                if (o1.v > o2.v) return -1;
                if (o1.v < o2.v) return 1;
                if (o1.posi && !o2.posi) return -1;
                if (!o1.posi && o2.posi) return 1;
                return 0;
            }
        });

        int[] ans = new int[k];
        for (int i = 0; i < k; i ++) {
            P p = ps.get(i);
            if (p.posi) ans[i] = mid + p.v;
            else ans[i] = mid - p.v;
        }
        return ans;
    }


    static class P {
        int v;
        boolean posi;
        P(int value, boolean p) {
            v = value; posi = p;
        }
    }


}