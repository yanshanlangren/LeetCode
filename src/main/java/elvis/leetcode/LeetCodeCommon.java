package elvis.leetcode;

import elvis.leetcode.model.ListNode;

import java.util.*;
import java.util.stream.Collectors;

public class LeetCodeCommon {
    public int maxProfit(int[] prices) {
        int[] dp = new int[prices.length];
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i] = Math.max(dp[i - 1], prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return dp[prices.length - 1];
    }

    public int singleNumber(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i : nums) {
            if (m.containsKey(i)) m.remove(i);
            else m.put(i, 1);
        }
        return m.keySet().stream().findAny().get();
    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /**
         定义两个指针, 第一轮让两个到达末尾的节点指向另一个链表的头部, 最后如果相遇则为交点(在第一轮移动中恰好抹除了长度差)
         两个指针等于移动了相同的距离, 有交点就返回, 无交点就是各走了两条指针的长度
         **/
        ListNode pA = headA, pB = headB;
        // 在这里第一轮体现在pA和pB第一次到达尾部会移向另一链表的表头, 而第二轮体现在如果pA或pB相交就返回交点, 不相交最后就是null==null
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public boolean isPalindrome(ListNode head) {
        Stack<Integer> s = new Stack<>();
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            s.push(slow.val);
            fast = fast.next.next;
            slow = slow.next;
        }
        while (slow != null) {
            if (s.empty() || s.pop() != slow.val) return false;
            slow = slow.next;
        }
        return true;
    }

    public void moveZeroes(int[] nums) {
        int i = 0, j = 0;
        while (i < nums.length && j < nums.length) {
            if (nums[j] != 0) {
                if (i != j) nums[i] = nums[j];
                i++;
            }
            j++;
        }
        while (i < nums.length) {
            nums[i++] = 0;
        }
    }


    public int hammingDistance(int x, int y) {
        int num = x ^ y, n = 0;
        while (num > 0) {
            if ((num & 1) == 1) n++;
            num >>= 1;
        }
        return n;
    }


    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }

    int max;

    public int maxDepth(TreeNode node) {
        if (node == null) return -1;
        int left = maxDepth(node.left) + 1;
        int right = maxDepth(node.right) + 1;
        if (max < left + right) max = left + right;
        return Math.max(left, right);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while (i > 0 && i < nums.length && nums[i] == nums[i - 1]) i++;
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < nums.length; j++) {
                if (map.containsKey(nums[j]) && map.get(nums[j]) != -1) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[map.get(nums[j])], nums[j])));
                    map.put(nums[j], -1);
                }
                if (!map.containsKey(nums[j])) map.put(-nums[i] - nums[j], j);
            }
        }
        return res;
    }

    List<String> res;
    int n;

    public List<String> generateParenthesis(int n) {
        this.n = n;
        res = new ArrayList<>();
        generate("", 0, 0);
        return res;
    }

    public void generate(String pattern, int c1, int c2) {
        if (c1 > n || c2 > n || c2 > c1) return;
        if (c1 == n && c2 == n) {
            res.add(pattern);
            return;
        }
        generate(pattern + '(', c1 + 1, c2);
        generate(pattern + ')', c1, c2 + 1);
    }

//    public void generate(String pattern, int n) {
//        if (n == 0) {
//            set.add(pattern);
//            return;
//        }
//        for (int i = 0; i < pattern.length() + 1; i++)
//            for (int j = i + 1; j <= pattern.length() + 1; j++) {
//                StringBuilder sb = new StringBuilder(pattern);
//                sb.insert(i, '(');
//                sb.insert(j, ')');
//                generate(sb.toString(), n - 1);
//            }
//    }

    public void nextPermutation(int[] nums) {
        int i, j;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.offer(nums[nums.length - 1]);
        for (i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                while (nums[i] >= q.poll()) ;
                int temp = nums[i];
                nums[i] = nums[q.size() + i + 1];
                nums[q.size() + i + 1] = temp;
                break;
            }
            q.offer(nums[i]);
        }
        for (j = i + 1; j < nums.length; j++) {
            int min = nums[j], min_ind = j;
            for (int k = j + 1; k < nums.length; k++) {
                if (min > nums[k]) {
                    min_ind = k;
                    min = nums[k];
                }
            }
            if (min_ind != j) {
                int temp = nums[min_ind];
                nums[min_ind] = nums[j];
                nums[j] = temp;
            }
        }
    }

    public void fastSort(int[] nums, int i, int j) {
        if (i >= j) return;
        int low = i + 1, high = j;
        while (low < high) {
            while (low < high && nums[high] > nums[i]) high--;
            while (low < high && nums[low] <= nums[i]) low++;
            if (high != low) {
                int temp = nums[high];
                nums[high] = nums[low];
                nums[low] = temp;
            }
        }
        int temp = nums[i];
        nums[i] = nums[low];
        nums[low] = temp;
        fastSort(nums, i, low - 1);
        fastSort(nums, low + 1, j);
    }

    public int search(int[] nums, int target) {
        int high = nums.length - 1, low = 0;
        while (high > low) {
            if (nums[(high + low) / 2] > nums[low]) low = (high + low) / 2;
            else if (nums[(high + low) / 2] < nums[high]) high = (high + low) / 2;
            else break;
        }
        int mid = high;
        high = mid - 1;
        low = mid;
        while (high != low) {
            int c = (high + (high > low ? 0 : nums.length) + low) / 2 % nums.length;
            if (nums[c] < target) low = c;
            else high = c;
        }
        return nums[high] == target ? high : -1;
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};
        int L = 0, R = nums.length - 1, start = 0, end = 0, c = -1;
        while (L < R) {
            c = (L + R) / 2;
            if (nums[c] > target) {
                R = c;
            } else if (nums[c] < target) {
                L = c + 1;
            } else {
                break;
            }
        }
        if (nums[L] != target) return new int[]{-1, -1};
        int high = c, low = L;
        while (low < high) {
            if (nums[(low + high) / 2] == target) {
                high = (low + high) / 2;
            } else {
                low = (low + high) / 2 + 1;
            }
        }
        start = low;
        low = c;
        high = R;
        while (low < high) {
            if (nums[(low + high) / 2] == target) {
                low = (low + high) / 2 + 1;
            } else {
                high = (low + high) / 2;
            }
        }
        return new int[]{start, low + (nums[low] == target ? 0 : -1)};
    }


    List<List<Integer>> list;
    int[] candidates;
    int target;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        list = new ArrayList<>();
        this.candidates = candidates;
        this.target = target;
        traceBack(0, new int[]{}, 0);
        return list;
    }

    public void traceBack(int i, int[] choice, int total) {
        if (total == target) {
            List<Integer> l = new ArrayList<>();
            for (int j = 0; j < choice.length; j++) {
                for (int k = 0; k < choice[j]; k++)
                    l.add(candidates[j]);
            }
            list.add(l);
            return;
        }
        if (i >= candidates.length) return;
        for (int j = 0; total + j * candidates[i] <= target; j++) {
            int[] x = new int[i + 1];
            System.arraycopy(choice, 0, x, 0, choice.length);
            x[i] = j;
            traceBack(i + 1, x, total + j * candidates[i]);
        }
    }

    int[] nums;

    public List<List<Integer>> permute(int[] nums) {
        list = new ArrayList<>();
        this.nums = nums;
        traceBack(0, new ArrayList<>());
        return list;
    }

    public void traceBack(int status, List<Integer> l) {
        if (status == (1 << nums.length) - 1) {
            list.add(l);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (((status >> i) & 1) == 0) {
                List<Integer> n = new ArrayList<>(l);
                n.add(nums[i]);
                traceBack(status | (1 << i), n);
            }
        }
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    public void sorted(char[] ca, int start, int end) {
        if (start >= end) return;
        int l = start + 1, r = end;
        while (l < r) {
            while (l < r && ca[l] <= ca[start]) l++;
            while (l < r && ca[r] > ca[start]) r--;
            if (l < r) {
                char tmp = ca[l];
                ca[l] = ca[r];
                ca[r] = tmp;
            }
        }
        if (ca[l] < ca[start]) {
            char tmp = ca[l];
            ca[l] = ca[start];
            ca[start] = tmp;
        }
        sorted(ca, start, l - 1);
        sorted(ca, l, end);
    }

    public boolean canJump(int[] nums) {
        boolean[] status = new boolean[nums.length];
        Queue<Integer> q = new PriorityQueue<>((x, y) -> y - x);
        status[0] = true;
        q.offer(0);
        while (!q.isEmpty()) {
            int next = q.poll();
            for (int i = 1; i <= nums[next] && next + i < nums.length; i++) {
                if (!status[next + i]) {
                    status[next + i] = true;
                    q.offer(next + i);
                }
                if (next + i == nums.length - 1) return true;
            }
        }
        return status[nums.length - 1];
    }

    public ListNode mergeKLists(ListNode[] lists) {
        // if(lists.length == 0)
        //     return null;
        // PriorityQueue<ListNode> q = new PriorityQueue<>((x,y) -> x.val-y.val);
        // Arrays.asList(lists).forEach(x -> {
        //     if(x!=null)
        //         q.offer(x);
        // });
        // ListNode head = q.poll(), node = head;
        // if(!q.isEmpty()&&head.next!=null){
        //     q.offer(head.next);
        // }
        // while(!q.isEmpty()){
        //     node.next = q.poll();
        //     if(node.next.next!=null){
        //         q.offer(node.next.next);
        //     }
        //     node = node.next;
        // }
        // return head;
        if (lists.length > 1) {
            return mergeLists(lists[0], lists, 1, lists.length);
        } else if (lists.length == 1) {
            return lists[0];
        } else {
            return null;
        }
    }

    public ListNode mergeLists(ListNode l, ListNode[] lists, int s, int e) {
        if (e - s == 0) return l;
        else if (e - s == 1) {
            ListNode l1 = l, l2 = lists[s], pre = null;
            while (l1 != null && l2 != null) {
                if (l2.val < l1.val) {
                    if (pre != null) pre.next = l2;
                    pre = l2;
                    l2 = l2.next;
                } else {
                    if (pre != null) pre.next = l1;
                    pre = l1;
                    l1 = l1.next;
                }
            }
            if (l1 != null) pre.next = l1;
            if (l2 != null) pre.next = l2;
            return l;
        } else {
            return mergeLists(mergeLists(l, lists, s, s + 1), lists, s + 1, e);
        }
    }

    public static int getMaxFreqDeviation(String s) {
        // Write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] m, tmp = new int[26];
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            m = new int[26];
            for (int j = 0; j < i; j++) {
                m[s.charAt(j) - 'a']++;
            }
            for (int j = i; j < s.length(); j++) {
                int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
                m[s.charAt(j) - 'a']++;
                for (int k = 0; k < 26; k++) {
                    if (m[k] > 0 && min > m[k]) min = m[k];
                    if (m[k] > 0 && max < m[k]) max = m[k];
                }

                if (max - min > res) res = max - min;
            }
        }
        return res;
    }

    public static int minSwapsRequired(String s) {
        // Write your code here
        int c0 = 0, c1 = 0, h0 = 0, i;
        char[] ca = s.toCharArray();
        for (i = 0; i < ca.length / 2; i++) {
            if (ca[i] == '0') {
                h0++;
                c0++;
            } else if (ca[i] == '1') {
                c1++;
            }
        }
        for (; i < ca.length; i++) {
            if (ca[i] == '0') {
                c0++;
            } else if (ca[i] == '1') {
                c1++;
            }
        }
        if (ca.length % 2 == 0) {
            if (c0 % 2 == 1 || c1 % 2 == 1) return -1;
            else return c0 / 2 - h0;
        } else {

        }
        return -1;
    }

    public int mySqrt(int x) {
        if (x == 0)
            return 0;
        int L = 1, H = x;
        while (H > L + 1) {
            int mid = (L + H) / 2;
            if (mid <= x/mid)
                L = mid;
            else
                H = mid;
        }
        return L;
    }

    public static void main(String[] args) {
        LeetCodeCommon l = new LeetCodeCommon();
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        System.out.println(list);
        list.remove(new Integer(3));
        System.out.println(list);

//        System.out.println(getMaxFreqDeviation(new String("bbacccabab")));

//        LinkedHashMap m = new LinkedHashMap<>();
//        System.out.println(l.canJump(new int[]{2, 3, 1, 1, 4}));
//        l.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6);
//        l.searchRange(new int[]{2, 2}, 2);

//        l.searchRange(new int[]{}, 8);
//        System.out.println(l.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
//        System.out.println((int) (Math.random() * Integer.MAX_VALUE));
//        System.out.println((Integer.MAX_VALUE * Math.random()));

//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < Math.pow(10, 3); i++)
//            list.add((int) (Integer.MAX_VALUE * Math.random()));
//        long t1 = System.currentTimeMillis();
//        l.fastSort(list.stream().mapToInt(Integer::valueOf).toArray(), 0, list.size() - 1);
//        int nums[] = list.stream().mapToInt(Integer::valueOf).toArray();
//        for (int i = 0; i < nums.length - 1; i++) {
//            int min = nums[i], min_ind = i;
//            for (int j = i + 1; j < nums.length; j++) {
//                if (min > nums[j]) {
//                    min_ind = j;
//                    min = nums[j];
//                }
//            }
//            if (min != i) {
//                int temp = nums[i];
//                nums[i] = nums[min_ind];
//                nums[min_ind] = temp;
//            }
//        }
//        System.out.println(System.currentTimeMillis() - t1);
//        System.out.println(1);
//        System.out.println(l.threeSum(new int[]{0, 0, 0, 0}));
//        System.out.println(l.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
//        System.out.println(l.threeSum(new int[]{-2, 0, 1, 1, 2}));
//        System.out.println(l.threeSum(new int[]{0, 2, 2, 3, 0, 1, 2, 3, -1, -4, 2}));
//        l.moveZeroes(new int[]{0, 1, 0, 3, 12});

//        System.out.println(l.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
//        System.out.println(l.maxProfit(new int[]{7, 6, 4, 3, 1}));
//        System.out.println(l.maxProfit(new int[]{1}));
//        System.out.println(l.maxProfit(new int[]{1, 2}));
//        System.out.println(l.maxProfit(new int[]{2, 1, 4}));
//        System.out.println(l.maxProfit(new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}));

//        System.out.println(l.singleNumber(new int[]{2, 2, 1}));


//        PriorityQueue<Integer> q = new PriorityQueue<>();
//        q.offer(-2);
//        q.offer(0);
//        q.offer(-3);
//        System.out.println(q.peek());
//        q.remove(-3);
//        System.out.println(q.peek());
//        int x = q.poll();
//        System.out.println(x);

//        ListNode headMerge = new ListNode(8, new ListNode(4, new ListNode(5)));
//        ListNode headA = new ListNode(4, new ListNode(1, headMerge));
//        ListNode headB = new ListNode(5, new ListNode(6, new ListNode(1, headMerge)));
//        System.out.println(l.getIntersectionNode(new ListNode(1, new ListNode(3)), new ListNode(2)));

    }
}
