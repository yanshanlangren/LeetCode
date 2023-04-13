package elvis.leetcode;

import java.util.PriorityQueue;

public class KthLargest {
    int k;
    PriorityQueue<Integer> q;

    /**
     *
     * @param k
     * @param nums
     */
    public KthLargest(int k, int[] nums) {
        this.q = new PriorityQueue<>();
        this.k = k;
        for (int num : nums)
            add(num);
    }

    public int add(int val) {
        if(q.size() < k) {
            q.offer(val);
        }
        else if(q.peek() < val) {
            q.poll();
            q.offer(val);
        }
        return q.peek();
    }

    public static void main(String[] args) {
        KthLargest kl = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kl.add(3));
        System.out.println(kl.add(5));
        System.out.println(kl.add(10));
        System.out.println(kl.add(9));
        System.out.println(kl.add(4));
//        KthLargest kl = new KthLargest(1, new int[0]);
//        System.out.println(kl.add(-3));
//        System.out.println(kl.add(-2));
//        System.out.println(kl.add(-4));
//        System.out.println(kl.add(0));
//        System.out.println(kl.add(4));
    }
}
