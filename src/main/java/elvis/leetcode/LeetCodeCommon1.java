package elvis.leetcode;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class LeetCodeCommon1 {
    /**
     * https://leetcode.cn/problems/number-of-closed-islands/
     *
     * @param grid
     * @return
     */
    public int closedIsland(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        fa = new int[r * c];
        //初始化并查集
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                fa[i * c + j] = i * c + j;
            }
        }
        //合并相邻的节点
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                //访问陆地节点,将未访问的节点指向陆地节点
                if (grid[i][j] == 0) {
                    if (i + 1 < r && grid[i + 1][j] == 0) {
                        merge(i * c + j, (i + 1) * c + j);
                    }
                    if (i - 1 >= 0 && grid[i - 1][j] == 0) {
                        merge(i * c + j, (i - 1) * c + j);
                    }
                    if (j + 1 < c && grid[i][j + 1] == 0) {
                        merge(i * c + j, i * c + j + 1);
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == 0) {
                        merge(i * c + j, i * c + j - 1);
                    }
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (i == 0 || i == r - 1 || j == 0 || j == c - 1) {
                    int f = find(i * c + j);
                    if (f != -1) {
                        fa[f] = -1;
                    }
                }
            }
        }

        //搜索独立节点
        int count = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 0 && fa[i * c + j] == i * c + j) {
                    System.out.println("i:" + i + ",j:" + j);
                    count++;
                }
            }
        }
        return count;
    }

    int[] fa;

    private int find(int i) {
        return fa[i] == i || fa[i] == -1 ? i : (fa[i] = find(fa[i]));
    }

    private void merge(int t, int s) {
        fa[find(s)] = find(t);
    }


    /**
     * https://leetcode.cn/problems/frequency-of-the-most-frequent-element/
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxFrequency(int[] nums, int k) {
        //排序
        Arrays.sort(nums);
        //遍历数组,如果需要的数量小于k,则继续,如果大于则返回.
        int max = 0, i, l = 0, r, len = nums.length;
        for (i = 1; i < len; i++) {
//            System.out.println("i:" + i + ",nums[i]:" + nums[i] + ",k:" + k);
            k -= (nums[i] - nums[i - 1]) * i;
            if (k < 0) {
                max = i;
                break;
            }
        }
        if (i == len) {
            return len;
        }
        r = i;
        while (l < r && r < len - 1) {
            while (k < 0) {
                k += nums[r] - nums[l++];
            }

            k -= (nums[r + 1] - nums[r]) * (r - l + 1);
            r++;
            if (k >= 0) {
                max = Math.max(r - l + 1, max);
            }
        }
        return max;
    }

    /**
     * https://leetcode.cn/problems/count-good-triplets-in-an-array/
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public long goodTriplets(int[] nums1, int[] nums2) {
        int len = nums1.length;
        //数字在两个数组中的索引值
//        int[] idx1 = new int[len];
        int[] idx2 = new int[len];
        for (int i = 0; i < len; i++) {
//            idx1[nums1[i]] = i;
            idx2[nums2[i]] = i;
        }

        //设置后缀数组,数组第i个元素表示,数字i在nums1和nums2中均出现在i之后的数字的数量
        int[] suffix = new int[len];
        for (int i = 0; i < len; i++) {
            //nums1中该数字
            int n1 = nums1[i];
            //第j个数在nums1中出现于n1之后
            for (int j = i + 1; j < len; j++) {
                //如果在nums2中也出现于n1之后则后缀++;
                if (idx2[n1] < idx2[nums1[j]]) {
                    suffix[n1]++;
                }
            }
        }

        long total = 0L;
        //搜索数组1,如果数组2满足条件,则计数器++
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (idx2[nums1[i]] < idx2[nums1[j]]) {
                    total += suffix[nums1[j]];
                }
            }
        }
        return total;
    }

    /**
     * https://leetcode.cn/problems/valid-triangle-number/
     *
     * @param nums
     * @return
     */
    public int triangleNumber(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int total = 0;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
//                for (int k = j + 1; k < len; k++) {
//                    if (nums[i] + nums[j] > nums[k]) {
//                        System.out.println("i:" + i + ",j:" + j + ",k:" + k);
//                        total++;
//                    }
//                }
                int sum = nums[i] + nums[j];
                int l = j + 1, r = len - 1;
                while (l <= r) {
                    int mid = (l + r) / 2;
                    if (nums[mid] < sum) {
                        l = mid + 1;
                    } else if (nums[mid] >= sum) {
                        r = mid - 1;
                    }
                }
                System.out.println("i:" + i + ",j:" + j + ",l:" + l);
                total += l - j - 1;
            }
        }
        return total;
    }

    /**
     * @param arr
     * @return
     */
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        int len = arr.length;
        int[] order = new int[len];
        //记录每个数字的下标, 4,1,3,2 -> 1,3,2,0
        for (int i = 0; i < len; i++) {
            order[arr[i] - 1] = i;
        }
        //先把第i个数反转到第0位, 即在当前顺序第order[i]处反转, 然后再反转i将第一位的i反转到i的位置
        for (int i = len - 1; i >= 0; i--) {
            if (order[i] != i) {
                if (order[i] != 0) {
                    ans.add(order[i] + 1);
                    pancakeReverse(arr, order, order[i]);
                }

                ans.add(i + 1);
                pancakeReverse(arr, order, i);
            }
        }
        return ans;
    }

    private void pancakeReverse(int[] nums, int[] order, int idx) {
        int l = 0, r = idx;
        while (l < r) {
            order[nums[l] - 1] = r;
            order[nums[r] - 1] = l;

            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++;
            r--;
        }
    }

    /**
     * https://leetcode.cn/problems/wiggle-subsequence/
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        int len = nums.length;
        //动规数组,[0,i]表示以0到i为正摆动结尾的最大长度
        //[1,i]表示以0到i为负摆动结尾的最大长度
        int[][] dp = new int[2][len];
        dp[0][0] = 1;
        dp[1][0] = 1;
        for (int i = 1; i < len; i++) {
            //向上摆动时,从向下摆动里搜索最大
            for (int j = 0; j < i; j++) {
                //上摆
                if (nums[i] > nums[j]) {
                    dp[0][i] = Math.max(dp[0][i], dp[1][j] + 1);
                    dp[0][i] = Math.max(dp[0][i], dp[0][j]);
                }
                //下摆
                if (nums[i] < nums[j]) {
                    dp[1][i] = Math.max(dp[1][i], dp[0][j] + 1);
                    dp[1][i] = Math.max(dp[1][i], dp[1][j]);
                }
                if (nums[i] == nums[j]) {
                    dp[0][i] = Math.max(dp[0][i], dp[0][j]);
                    dp[1][i] = Math.max(dp[1][i], dp[1][j]);
                }
            }
        }
        return Math.max(dp[0][len - 1], dp[1][len - 1]);
    }

    public static void main(String[] args) {
        LeetCodeCommon1 l = new LeetCodeCommon1();
        int a = l.wiggleMaxLength(new int[]{33, 53, 12, 64, 50, 41, 45, 21, 97, 35, 47, 92, 39, 0, 93, 55, 40, 46, 69, 42, 6, 95, 51, 68, 72, 9, 32, 84, 34, 64, 6, 2, 26, 98, 3, 43, 30, 60, 3, 68, 82, 9, 97, 19, 27, 98, 99, 4, 30, 96, 37, 9, 78, 43, 64, 4, 65, 30, 84, 90, 87, 64, 18, 50, 60, 1, 40, 32, 48, 50, 76, 100, 57, 29, 63, 53, 46, 57, 93, 98, 42, 80, 82, 9, 41, 55, 69, 84, 82, 79, 30, 79, 18, 97, 67, 23, 52, 38, 74, 15});
//        int a = l.wiggleMaxLength(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
//        int a = l.wiggleMaxLength(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8});
//        int a = l.wiggleMaxLength(new int[]{1, 7, 4, 9, 2, 5});
//        int a = l.wiggleMaxLength(new int[]{3, 3, 3, 2, 5});
        System.out.println("ans:" + JSONObject.toJSON(a));
    }
}
