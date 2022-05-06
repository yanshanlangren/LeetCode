package elvis.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        HashSet<String> res_set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                for (int k = nums.length - 1; k > j; k--) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        res_set.add(nums[i] + "," + nums[j] + "," + nums[k]);
                        break;
                    }
                }
            }
        }
        for (String s : res_set) {
            ArrayList<Integer> l = new ArrayList();
            for (String item : s.split(",")) {
                l.add(Integer.valueOf(item));
            }
            res.add(l);
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSum t = new ThreeSum();
//        System.out.println(t.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
//        System.out.println(t.threeSum(new int[]{}));
//        System.out.println(t.threeSum(new int[]{0}));
//        System.out.println(t.threeSum(new int[]{0, 0, 0, 0}));
        System.out.println(t.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
