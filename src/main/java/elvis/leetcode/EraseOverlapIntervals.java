package elvis.leetcode;

import java.util.Arrays;

public class EraseOverlapIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (int[] a, int[] b) -> (a[1] - a[0]) == (b[1] - b[0]) ? a[0] - b[0] : (a[1] - a[0]) - (b[1] - b[0]));
        int[][] dp = new int[1][1];
        return 0;
    }
}
