package elvis.leetcode;

import java.util.PriorityQueue;

public class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        for (int num : nums) {
            min.offer(num);
            if (min.size() > k) min.poll();
        }
        return min.peek();
    }

    public static void main(String[] args) {
        FindKthLargest f = new FindKthLargest();
        System.out.println(f.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(f.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }
}
