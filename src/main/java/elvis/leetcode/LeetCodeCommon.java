package elvis.leetcode;

import elvis.leetcode.model.ListNode;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
            if (m.containsKey(i)) {
                m.remove(i);
            } else {
                m.put(i, 1);
            }
        }
        return m.keySet().stream().findAny().get();
    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
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
            if (s.empty() || s.pop() != slow.val) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    public void moveZeroes(int[] nums) {
        int i = 0, j = 0;
        while (i < nums.length && j < nums.length) {
            if (nums[j] != 0) {
                if (i != j) {
                    nums[i] = nums[j];
                }
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
            if ((num & 1) == 1) {
                n++;
            }
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
        if (node == null) {
            return -1;
        }
        int left = maxDepth(node.left) + 1;
        int right = maxDepth(node.right) + 1;
        if (max < left + right) {
            max = left + right;
        }
        return Math.max(left, right);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while (i > 0 && i < nums.length && nums[i] == nums[i - 1]) {
                i++;
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < nums.length; j++) {
                if (map.containsKey(nums[j]) && map.get(nums[j]) != -1) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[map.get(nums[j])], nums[j])));
                    map.put(nums[j], -1);
                }
                if (!map.containsKey(nums[j])) {
                    map.put(-nums[i] - nums[j], j);
                }
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
        if (c1 > n || c2 > n || c2 > c1) {
            return;
        }
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
        if (i >= j) {
            return;
        }
        int low = i + 1, high = j;
        while (low < high) {
            while (low < high && nums[high] > nums[i]) {
                high--;
            }
            while (low < high && nums[low] <= nums[i]) {
                low++;
            }
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
            if (nums[(high + low) / 2] > nums[low]) {
                low = (high + low) / 2;
            } else if (nums[(high + low) / 2] < nums[high]) {
                high = (high + low) / 2;
            } else {
                break;
            }
        }
        int mid = high;
        high = mid - 1;
        low = mid;
        while (high != low) {
            int c = (high + (high > low ? 0 : nums.length) + low) / 2 % nums.length;
            if (nums[c] < target) {
                low = c;
            } else {
                high = c;
            }
        }
        return nums[high] == target ? high : -1;
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
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
        if (nums[L] != target) {
            return new int[]{-1, -1};
        }
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
                for (int k = 0; k < choice[j]; k++) {
                    l.add(candidates[j]);
                }
            }
            list.add(l);
            return;
        }
        if (i >= candidates.length) {
            return;
        }
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
        if (start >= end) {
            return;
        }
        int l = start + 1, r = end;
        while (l < r) {
            while (l < r && ca[l] <= ca[start]) {
                l++;
            }
            while (l < r && ca[r] > ca[start]) {
                r--;
            }
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
                if (next + i == nums.length - 1) {
                    return true;
                }
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
        if (e - s == 0) {
            return l;
        } else if (e - s == 1) {
            ListNode l1 = l, l2 = lists[s], pre = null;
            while (l1 != null && l2 != null) {
                if (l2.val < l1.val) {
                    if (pre != null) {
                        pre.next = l2;
                    }
                    pre = l2;
                    l2 = l2.next;
                } else {
                    if (pre != null) {
                        pre.next = l1;
                    }
                    pre = l1;
                    l1 = l1.next;
                }
            }
            if (l1 != null) {
                pre.next = l1;
            }
            if (l2 != null) {
                pre.next = l2;
            }
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
                    if (m[k] > 0 && min > m[k]) {
                        min = m[k];
                    }
                    if (m[k] > 0 && max < m[k]) {
                        max = m[k];
                    }
                }

                if (max - min > res) {
                    res = max - min;
                }
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
            if (c0 % 2 == 1 || c1 % 2 == 1) {
                return -1;
            } else {
                return c0 / 2 - h0;
            }
        } else {

        }
        return -1;
    }

    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int L = 1, H = x;
        while (H > L + 1) {
            int mid = (L + H) / 2;
            if (mid <= x / mid) {
                L = mid;
            } else {
                H = mid;
            }
        }
        return L;
    }

    public String convertToTitle(int columnNumber) {
        StringBuilder col = new StringBuilder();
        while (columnNumber > 0) {
            col.append((char) ((columnNumber - 1) % 26 + 'A'));
            columnNumber /= 26;
        }
        return col.toString();
    }

    public boolean isHappy(int n) {
        int fast = n;
        int slow = n;
        do {
            fast = isHappySum(isHappySum(fast));
            slow = isHappySum(slow);
        } while (fast != slow && fast != 1 && slow != 1);
        return fast == 1 || slow == 1;
    }

    public int isHappySum(int n) {
        int sum = 0;
        while (n > 0) {
            int a = n % 10;
            sum += a * a;
            n /= 10;
        }
        return sum;
    }

    public int searchInsert(int[] nums, int target) {
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        if (target < nums[0]) {
            return 0;
        }
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int mid = (l + h) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                h = mid;
            } else {
                return mid;
            }
        }
        return l;
    }

//    public List<String> summaryRanges(int[] nums) {
//        List<String> res = new ArrayList<>();
//        if (nums.length == 0)
//            return res;
//        int tmp = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] - nums[i - 1] != 1) {
//                if (nums[i - 1] > tmp)
//                    res.add(tmp + "->" + nums[i - 1]);
//                else
//                    res.add(String.valueOf(tmp));
//                tmp = nums[i];
//            }
//        }
//        if (nums[nums.length - 1] == tmp)
//            res.add(String.valueOf(tmp));
//        else
//            res.add(tmp + "->" + nums[nums.length - 1]);
//        return res;
//    }

    List<List<Integer>> res1;

    public List<String> binaryTreePaths(TreeNode root) {
        res1 = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        res1.add(list);
        if (root != null) {
            list.add(root.val);
            traverse(list, root);
        }
        List<String> ret = new ArrayList<>();
        for (List<Integer> l : res1) {
            StringBuilder sb = new StringBuilder();
            for (Integer s : l) {
                sb.append(s);
                sb.append("->");
            }
            if (sb.length() > 0) {
                sb.delete(sb.length() - 2, sb.length());
            }
            ret.add(sb.toString());
        }
        return ret;
    }

    public void traverse(List<Integer> cur, TreeNode node) {
        if (node.left == null && node.right == null) {
            return;
        }
        if (node.left != null && node.right != null) {
            List<Integer> list = new ArrayList<>(cur);
            res1.add(list);
            list.add(node.left.val);
            cur.add(node.right.val);
            traverse(list, node.left);
            traverse(cur, node.right);
            return;
        }
        if (node.left != null) {
            cur.add(node.left.val);
            traverse(cur, node.left);
            return;
        }
        cur.add(node.right.val);
        traverse(cur, node.right);
        return;
    }

    public int[][] spiralArray(int x) {
        // find square
        // if x is a int square
        int kaifang = (int) Math.pow(x, 0.5);
        int row;
        if (kaifang * kaifang == x) {
            row = kaifang;
        } else {
            row = kaifang + 1;
        }

        //build int array
        int i = (row - 1) / 2, j = (row - 1) / 2;
        int[][] res = new int[row][row];
        int count = 1;
        int direction = 0; // 0 - right, 1 - down, 2-left, 3-up
        while (count <= x) {
            //System.out.println(count+", "+ direction+", "+i+", "+ j);
            res[i][j] = count++;
            switch (direction) {
                case 0:
                    j++;
                    if (j < row && i + 1 < row && res[i + 1][j] == 0) {
                        direction = 1;
                    }
                    break;
                case 1:
                    i++;
                    if (i < row && j - 1 >= 0 && res[i][j - 1] == 0) {
                        direction = 2;
                    }
                    break;
                case 2:
                    j--;
                    if (j >= 0 && i - 1 >= 0 && res[i - 1][j] == 0) {
                        direction = 3;
                    }
                    break;
                case 3:
                    i--;
                    if (i >= 0 && j + 1 < row && res[i][j + 1] == 0) {
                        direction = 0;
                    }
                    break;
            }
        }

        return res;
    }

    public int thirdMax(int[] nums) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.contains(num)) {
                q.offer(num);
                if (q.size() > 3) {
                    q.poll();
                }
                set.add(num);
            }
        }
        if (q.size() == 2) {
            q.poll();
            return q.peek();
        }
        return q.peek();
    }

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1;
        int c = 0;
        StringBuilder s = new StringBuilder();
        while (i >= 0 || j >= 0) {
            if (i >= 0) {
                c += num1.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                c += num2.charAt(j) - '0';
                j--;
            }
            s.append(c % 10);
            c /= 10;
        }
        if (c > 0) {
            s.append(c);
        }
        return s.reverse().toString();
    }

    public int islandPerimeter(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    count += ((i + 1 >= row || grid[i + 1][j] == 0)) ? 1 : 0;
                    count += ((j + 1 >= col || grid[i][j + 1] == 0) ? 1 : 0);
                    count += ((i - 1 < 0 || grid[i - 1][j] == 0) ? 1 : 0);
                    count += ((j - 1 < 0 || grid[i][j - 1] == 0) ? 1 : 0);
                }
            }
        }
        return count;
    }

    public int findComplement(int num) {
        //有效位数
        int n = 0;
        int t = num;
        while (t != 0) {
            t >>= 1;
            n++;
        }
        num = ~num;
        num <<= (32 - n);
        num >>= (32 - n);
        return num;
    }

    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder(s.toUpperCase(Locale.ROOT));
        int count = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '-') {
                if (count <= k) {
                    sb.deleteCharAt(i);
                    i--;
                } else {
                    count = 0;
                }
            } else {
                count++;
            }
        }
        return sb.toString();
    }

    public static void plusMinus(List<Integer> arr) {
        // Write your code here
        int[] res = new int[3];
        arr.forEach(x -> {
            if (x > 0) {
                res[2]++;
            } else if (x < 0) {
                res[0]++;
            } else {
                res[1]++;
            }
        });
        double total = res[0] + res[1] + res[2];
        System.out.println((double) res[0] / total);
        System.out.println((double) res[1] / total);
        System.out.println((double) res[2] / total);
    }

    public static void miniMaxSum(List<Integer> arr) {
        // Write your code here
        AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
        long total = arr.stream().peek(x -> {
            max.set(Math.max(max.get(), x));
            min.set(Math.min(min.get(), x));
        }).map(x -> (long) x).reduce(0L, Long::sum);
        System.out.println((total - max.get()) + " " + (total - min.get()));
    }

    public static String timeConversion(String s) {
        // Write your code here
        String AP = s.substring(s.length() - 2, s.length());
        int hour = Integer.parseInt(s.substring(0, 2));
        if (AP.equals("AM")) {
            if (hour == 12) {
                hour = 0;
            }
        } else {
            if (hour != 12) {
                hour += 12;
            }
        }
        return (hour < 10 ? "0" : "") + hour + s.substring(2, s.length() - 2);
    }

    public String[] findWords(String[] words) {
        //qwertyuiop
        //asdfghjkl
        //zxcvbnm
        List<String> l = new ArrayList<>();
        int[] idx = new int[]{2, 3, 3, 2, 1, 2, 2, 2, 1, 2, 2, 2, 3, 3, 1, 1, 1, 1, 2, 1, 1, 3, 1, 3, 1, 3};
        for (String word : words) {
            String wd = word.toLowerCase(Locale.ROOT);
            boolean flag = true;
            char[] ca = wd.toCharArray();
            for (int i = 1; i < wd.length(); i++) {
                if (idx[ca[0] - 'a'] != idx[ca[i] - 'a']) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                l.add(word);
            }
//            System.out.println(word);
        }
        return l.toArray(new String[0]);
    }

    HashMap<Integer, Integer> map;

    public int[] findMode(TreeNode root) {
        map = new HashMap<>();
//        traverse(root);
        int max = 0;
        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        List<Integer> l = null;
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> entry = it.next();
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (value > max) {
                l = new ArrayList<>();
                l.add(key);
            }
        }
        return l.stream().mapToInt(Integer::valueOf).toArray();

    }

    public String convertToBase7(int num) {
        StringBuilder sb = new StringBuilder();
        int x = num;
        while (x != 0) {
            sb.append(x % 7);
            x /= 7;
        }
        if (num < 0) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }

    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int count = 1;
        int i = 2;
        while (i < num) {
            if ((num / i) * i == num) {
                count += i;
            }
            i++;
        }
        return count == num;
    }

    public String[] findRelativeRanks(int[] score) {
        int[] sort = new int[score.length];
        int count = 1;

        return null;
    }

    public void fastSort(int[] score, int start, int end, int[] sort) {
        int h = end, l = start;
        while (l < h) {

        }
    }

    Map<String, String> fa;
    Map<String, Double> cal;

    public String find(String a) {
        return fa.get(a).equals(a) ? a : find(fa.get(a));
    }

    public double calc(String a) {
        return fa.get(a).equals(a) ? 1.0 : cal.get(a) * calc(fa.get(a));
    }

    public void merge(String a, String b) {

    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        fa = new HashMap<>();
        cal = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> eq = equations.get(i);
            String s0 = eq.get(0);
            String s1 = eq.get(1);
            if (!fa.containsKey(s1)) {
                fa.put(s1, s0);
                cal.put(s0, values[i]);
            } else {

            }
            if (!fa.containsKey(s0)) {
                fa.put(s0, s0);
                cal.put(s0, 1.0);
            }
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String s0 = query.get(0);
            String s1 = query.get(1);
            if (!fa.containsKey(s0) || !fa.containsKey(s1)) {
                res[i] = -1.0;
            }
            if (find(s0).equals(find(s1))) {
                res[i] = calc(s0) / calc(s1);
            }
        }
        return res;
    }

    public int backpack01(int[][] demands, int cpu, int mem) {
        int[][] dp = new int[cpu + 1][mem + 1];
        for (int i = 0; i <= cpu; i++) {
            for (int j = 0; j <= mem; j++) {
                for (int k = 0; k < demands.length; k++) {
                    if (demands[k][0] + i <= cpu && demands[k][1] + j < mem) {
                        for (int l = demands[k][0] + i; l <= cpu; l++) {
                            for (int m = demands[k][1] + j; m <= mem; m++) {
                                dp[l][m] = Math.max(dp[i][j] + 1, dp[l][m]);
                            }
                        }
                    }
    /**
     * 农行编码比赛原题
     * 云主机申请请求，给定cpu个数和内存最大数量，返回最多能满足多少个云主机新建请求
     *
     * @param demands 云主机创建请求，demand[i][0]表示需要cpu数量，demand[i][1]表示需要内存数量
     * @param cpu     cpu核数
     * @param mem     内存最大数量
     * @return 最多能满足多少个请求
     */
    public int backPack01(int[][] demands, int cpu, int mem) {
        int num = demands.length;
        int[][] dp = new int[cpu + 1][mem + 1];
        for (int n = 1; n <= num; n++) {
            int c = demands[n - 1][0];
            int m = demands[n - 1][1];
            for (int i = cpu; i >= c; i--) {
                for (int j = mem; j >= m; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - c][j - m] + 1);
                }
            }
        }
        return dp[cpu][mem];
    }

    public int backPack01Origin(int[][] demands, int cpu, int mem) {
        int num = demands.length;
        int[][][] dp = new int[num + 1][cpu + 1][mem + 1];
        for (int n = 1; n <= num; n++) {
            int c = demands[n - 1][0];
            int m = demands[n - 1][1];
            for (int i = cpu; i >= c; i--) {
                for (int j = mem; j >= m; j--) {
                    dp[n][i][j] = Math.max(dp[n - 1][i][j], dp[n - 1][i - c][j - m] + 1);
                }
            }
        }
        return dp[num][cpu][mem];
    }

    int cpu;
    int mem;
    int[][] demands;

    public int backPack01TraceBack(int[][] demands, int cpu, int mem) {
        max = 0;
        this.cpu = cpu;
        this.mem = mem;
        this.demands = demands;
        backPack01TB(0, 0, 0, 0);
        return max;
    }

    public void backPack01TB(int n, int tDmands, int tCpu, int tMem) {
        if (n >= demands.length) {
            return;
        }
        int c = demands[n][0];
        int m = demands[n][1];
        if (tCpu + c <= cpu && tMem + m <= mem) {
            max = Math.max(tDmands + 1, max);
            backPack01TB(n + 1, tDmands + 1, tCpu + c, tMem + m);
        }
        backPack01TB(n + 1, tDmands, tCpu, tMem);
    }

    /**
     * https://leetcode.cn/problems/partition-equal-subset-sum/
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        sum /= 2;

        return false;
    }

    /**
     * https://leetcode.cn/problems/coin-lcci/
     *
     * @param n
     * @return
     */
    public int waysToChange(int n) {
        int[] coins = new int[]{1, 5, 10, 25};
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j <= n; j++) {
                if (i - coins[i] > 0) {
                    dp[j] = dp[i - coins[i]] + dp[j];
                }
        int[] dp = new int[n + 1];
//        int[] coins = new int[]{1, 5, 10, 25};
        int[] coins = new int[]{25, 10, 5, 1};
        dp[0] = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = coins[i]; j < n + 1; j++) {
                dp[j] += dp[j - coins[i]];
                dp[j] %= 1000000007;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        LeetCodeCommon l = new LeetCodeCommon();
//        int i = l.backpack01(new int[][]{{1, 1}, {3, 1}, {2, 4}, {0, 1}, {1, 0}}, 5, 3);
//        int i = l.waysToChange(900750);
        int i = l.waysToChange(10);
        System.out.println(i);
//        List<List<String>> equations = new ArrayList<>();
//        equations.add(Arrays.asList("a", "b"));
//        equations.add(Arrays.asList("b", "c"));
//        double[] values = new double[]{2.0, 3.0};
//        List<List<String>> queries = new ArrayList<>();
////        ["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]
//        equations.add(Arrays.asList("a", "b"));
//        equations.add(Arrays.asList("b", "a"));
//        equations.add(Arrays.asList("a", "e"));
//        equations.add(Arrays.asList("a", "e"));
//        equations.add(Arrays.asList("x", "x"));
//        double[] res = l.calcEquation(equations, values, queries);
////        String[] res = l.findRelativeRanks(new int[]{10, 3, 8, 9, 4});
    /**
     * https://leetcode.cn/problems/coin-change/
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int j = 1; j <= amount; j++) {
            dp[j] = Integer.MAX_VALUE - 1;
        }
        for (int i = 1; i <= coins.length; i++) {
            int coin = coins[i - 1];
            for (int j = coin; j <= amount; j++) {
                dp[j] = Math.min(dp[j - coins[i - 1]] + 1, dp[j]);
            }
        }
        return dp[amount] >= Integer.MAX_VALUE - 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        LeetCodeCommon l = new LeetCodeCommon();


        int x = l.coinChange(new int[]{1, 2, 5}, 11);
//        int x = l.coinChange(new int[]{1}, 0);
        System.out.println(x);
//        int x = l.waysToChange(10);
////        int x = l.waysToChange(900750);
//        System.out.println(x);

//        boolean max = l.canPartition(new int[]{1, 5, 11, 5});
//        boolean max = l.canPartition(new int[]{1, 2, 3, 5});


//        int max = l.backPack01Origin(new int[][]{{1, 1}, {3, 1}, {2, 2}, {2, 1}}, 6, 3);
//        System.out.println(max);


//        String[] res = l.findRelativeRanks(new int[]{10, 3, 8, 9, 4});
//        System.out.println(res);
//        for (int i = 0; i < Integer.MAX_VALUE; i++) {
//        long t0 = System.currentTimeMillis();
//        if (l.checkPerfectNumber(33550336)) System.out.println(33550336);
//        long t1 = System.currentTimeMillis();
//        System.out.println(t1 - t0);
//        }
//        l.convertToBase7(-7);


//        HashMap<Integer, Integer> map = new HashMap<>();
//        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
//        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2));
//        while (it.hasNext()) {
//            it.next();
//        }
//        String[] x = list.stream().map(String::valueOf).toArray(String[]::new);
//        System.out.println(x);
//        String a1 = "qwertyuiop";
//        String a2 = "asdfghjkl";
//        String a3 = "zxcvbnm";
//        for (char i = 'a'; i <= 'z'; i++) {
//            if (a1.indexOf(i) != -1) System.out.print(1 + ",");
//            else if (a2.indexOf(i) != -1) System.out.print(2 + ",");
//            else if (a3.indexOf(i) != -1) System.out.print(3 + ",");
//        }
//        System.out.println(timeConversion("12:00:00AM"));
//        miniMaxSum(new ArrayList<>(Arrays.asList(256741038, 623958417, 467905213, 714532089, 938071625)));
//        LeetCodeCommon l = new LeetCodeCommon();
//        int a = (int) (Math.sqrt(10) +1);
//        System.out.println(l.addStrings("999", "1"));
//        System.out.println(l.islandPerimeter(new int[][]{{1, 1}}));
//        System.out.println(l.summaryRanges(new int[]{-2147483648, -2147483647, 2147483647}));
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println(Integer.MAX_VALUE - Integer.MIN_VALUE);

//        StringBuilder sb = new StringBuilder();
//        sb.delete(sb.length() - 2, sb.length() - 1);
//        System.out.println(Integer.MIN_VALUE);

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
