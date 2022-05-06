package elvis.leetcode;

public class RemoveDuplicates_Integer {
    public int removeDuplicates(int[] nums) {
        int i = 0, j = 1;
        for (; j < nums.length; ) {
            if (nums[i] == nums[j]) {
                j++;
            } else {
                i++;
                nums[i] = nums[j];
                j++;
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        RemoveDuplicates_Integer r = new RemoveDuplicates_Integer();
//        System.out.println(r.removeDuplicates(new int[]{1, 1, 2}));
        System.out.println(r.removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
    }
}
