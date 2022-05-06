package elvis.leetcode;

public class RemoveDuplicatesII {
    public int removeDuplicates_Mine(int[] nums) {
        int len = nums.length;
        int count = 1;
        int ele = nums[0];
        for (int i = 1; i < len; ) {
            if (nums[i] == ele) {
                if (count > 1) {
                    int j = i + 1;
                    while (j < len && nums[j] == ele) {
                        j++;
                    }
                    System.arraycopy(nums, j, nums, i, len - j);
                    len -= j - i;
                } else {
                    count++;
                    i++;
                }
            } else {
                ele = nums[i];
                count = 1;
                i++;
            }
        }
        return len;
    }
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i-2])
                nums[i++] = n;
        return i;
    }

    public static void main(String[] args) {
        RemoveDuplicatesII r = new RemoveDuplicatesII();
//        System.out.println(r.removeDuplicates(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}));
        System.out.println(r.removeDuplicates(new int[]{1, 1, 1, 2, 2, 2, 3, 3}));
    }
}
