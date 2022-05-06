package elvis.leetcode;

import java.util.Arrays;

public class PlatesBetweenCandles {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int[] left = new int[s.length()];
        int[] count = new int[s.length()];
        int[] right = new int[s.length()];
        int last = -1, total = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '|') {
                left[i] = i;
                last = i;
                count[i] = total;
            } else if (s.charAt(i) == '*') {
                left[i] = last;
                count[i] = ++total;
            }
        }
        last = Integer.MAX_VALUE;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '|') {
                right[i] = i;
                last = i;
            } else if (s.charAt(i) == '*') {
                right[i] = last;
            }
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (right[queries[i][0]] == Integer.MAX_VALUE || left[queries[i][1]] == -1 || right[queries[i][0]] > left[queries[i][1]])
                res[i] = 0;
            else
                res[i] = count[left[queries[i][1]]] - count[right[queries[i][0]]];
        }
        return res;
    }

    public static void main(String[] args) {
        PlatesBetweenCandles p = new PlatesBetweenCandles();
        System.out.println(Arrays.asList(p.platesBetweenCandles("**|**|***|", new int[][]{{2, 5}, {5, 9}})));
//        System.out.println(Arrays.asList(p.platesBetweenCandles("***|**|*****|**||**|*", new int[][]{{1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}})));
    }
}
