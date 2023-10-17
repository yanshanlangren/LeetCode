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

    int[][] demands;
    int cpu;
    int mem;

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

    /**
     * https://leetcode.cn/problems/container-with-most-water/
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int L = 0;
        int R = height.length - 1;
        int max = Integer.MIN_VALUE;
        while (L < R) {
            int v = (R - L) * Math.min(height[R], height[L]);
            max = Math.max(v, max);
            if (height[L] <= height[R]) {
                L++;
            } else {
                R--;
            }
        }
        return max;
    }

    /**
     * https://leetcode.cn/problems/trapping-rain-water/
     *
     * @param height
     * @return
     */
    int[] h;
    int[] sum;

    public int trap(int[] height) {
        h = height;
        sum = new int[height.length + 1];
        sum[0] = 0;
        for (int i = 0; i < height.length; i++) {
            sum[i + 1] = sum[i] + h[i];
        }
        return trapBy2(0, height.length - 1);
    }

    private int trapBy2(int start, int end) {
        if (start == end) {
            return 0;
        }
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (int i = start; i <= end; i++) {
            q.offer(new int[]{i, h[i]});
            if (q.size() > 2) {
                q.poll();
            }
        }
        int[] second = q.poll();
        int[] first = q.poll();
        int big = Math.max(second[0], first[0]);
        int small = Math.min(second[0], first[0]);
        int total = (big - small + 1) * second[1] - sum[big + 1] + sum[small] + first[1] - second[1];
        return trapBy2(start, small) + total + trapBy2(big, end);
    }

    public static void main(String[] args) {
        LeetCodeCommon l = new LeetCodeCommon();
//        int a = l.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
//        int a = l.trap(new int[]{4, 2, 0, 3, 2, 5});
        int a= l.trap(new int[]{100000, 0, 99999, 0, 99998, 0, 99997, 0, 99996, 0, 99995, 0, 99994, 0, 99993, 0, 99992, 0, 99991, 0, 99990, 0, 99989, 0, 99988, 0, 99987, 0, 99986, 0, 99985, 0, 99984, 0, 99983, 0, 99982, 0, 99981, 0, 99980, 0, 99979, 0, 99978, 0, 99977, 0, 99976, 0, 99975, 0, 99974, 0, 99973, 0, 99972, 0, 99971, 0, 99970, 0, 99969, 0, 99968, 0, 99967, 0, 99966, 0, 99965, 0, 99964, 0, 99963, 0, 99962, 0, 99961, 0, 99960, 0, 99959, 0, 99958, 0, 99957, 0, 99956, 0, 99955, 0, 99954, 0, 99953, 0, 99952, 0, 99951, 0, 99950, 0, 99949, 0, 99948, 0, 99947, 0, 99946, 0, 99945, 0, 99944, 0, 99943, 0, 99942, 0, 99941, 0, 99940, 0, 99939, 0, 99938, 0, 99937, 0, 99936, 0, 99935, 0, 99934, 0, 99933, 0, 99932, 0, 99931, 0, 99930, 0, 99929, 0, 99928, 0, 99927, 0, 99926, 0, 99925, 0, 99924, 0, 99923, 0, 99922, 0, 99921, 0, 99920, 0, 99919, 0, 99918, 0, 99917, 0, 99916, 0, 99915, 0, 99914, 0, 99913, 0, 99912, 0, 99911, 0, 99910, 0, 99909, 0, 99908, 0, 99907, 0, 99906, 0, 99905, 0, 99904, 0, 99903, 0, 99902, 0, 99901, 0, 99900, 0, 99899, 0, 99898, 0, 99897, 0, 99896, 0, 99895, 0, 99894, 0, 99893, 0, 99892, 0, 99891, 0, 99890, 0, 99889, 0, 99888, 0, 99887, 0, 99886, 0, 99885, 0, 99884, 0, 99883, 0, 99882, 0, 99881, 0, 99880, 0, 99879, 0, 99878, 0, 99877, 0, 99876, 0, 99875, 0, 99874, 0, 99873, 0, 99872, 0, 99871, 0, 99870, 0, 99869, 0, 99868, 0, 99867, 0, 99866, 0, 99865, 0, 99864, 0, 99863, 0, 99862, 0, 99861, 0, 99860, 0, 99859, 0, 99858, 0, 99857, 0, 99856, 0, 99855, 0, 99854, 0, 99853, 0, 99852, 0, 99851, 0, 99850, 0, 99849, 0, 99848, 0, 99847, 0, 99846, 0, 99845, 0, 99844, 0, 99843, 0, 99842, 0, 99841, 0, 99840, 0, 99839, 0, 99838, 0, 99837, 0, 99836, 0, 99835, 0, 99834, 0, 99833, 0, 99832, 0, 99831, 0, 99830, 0, 99829, 0, 99828, 0, 99827, 0, 99826, 0, 99825, 0, 99824, 0, 99823, 0, 99822, 0, 99821, 0, 99820, 0, 99819, 0, 99818, 0, 99817, 0, 99816, 0, 99815, 0, 99814, 0, 99813, 0, 99812, 0, 99811, 0, 99810, 0, 99809, 0, 99808, 0, 99807, 0, 99806, 0, 99805, 0, 99804, 0, 99803, 0, 99802, 0, 99801, 0, 99800, 0, 99799, 0, 99798, 0, 99797, 0, 99796, 0, 99795, 0, 99794, 0, 99793, 0, 99792, 0, 99791, 0, 99790, 0, 99789, 0, 99788, 0, 99787, 0, 99786, 0, 99785, 0, 99784, 0, 99783, 0, 99782, 0, 99781, 0, 99780, 0, 99779, 0, 99778, 0, 99777, 0, 99776, 0, 99775, 0, 99774, 0, 99773, 0, 99772, 0, 99771, 0, 99770, 0, 99769, 0, 99768, 0, 99767, 0, 99766, 0, 99765, 0, 99764, 0, 99763, 0, 99762, 0, 99761, 0, 99760, 0, 99759, 0, 99758, 0, 99757, 0, 99756, 0, 99755, 0, 99754, 0, 99753, 0, 99752, 0, 99751, 0, 99750, 0, 99749, 0, 99748, 0, 99747, 0, 99746, 0, 99745, 0, 99744, 0, 99743, 0, 99742, 0, 99741, 0, 99740, 0, 99739, 0, 99738, 0, 99737, 0, 99736, 0, 99735, 0, 99734, 0, 99733, 0, 99732, 0, 99731, 0, 99730, 0, 99729, 0, 99728, 0, 99727, 0, 99726, 0, 99725, 0, 99724, 0, 99723, 0, 99722, 0, 99721, 0, 99720, 0, 99719, 0, 99718, 0, 99717, 0, 99716, 0, 99715, 0, 99714, 0, 99713, 0, 99712, 0, 99711, 0, 99710, 0, 99709, 0, 99708, 0, 99707, 0, 99706, 0, 99705, 0, 99704, 0, 99703, 0, 99702, 0, 99701, 0, 99700, 0, 99699, 0, 99698, 0, 99697, 0, 99696, 0, 99695, 0, 99694, 0, 99693, 0, 99692, 0, 99691, 0, 99690, 0, 99689, 0, 99688, 0, 99687, 0, 99686, 0, 99685, 0, 99684, 0, 99683, 0, 99682, 0, 99681, 0, 99680, 0, 99679, 0, 99678, 0, 99677, 0, 99676, 0, 99675, 0, 99674, 0, 99673, 0, 99672, 0, 99671, 0, 99670, 0, 99669, 0, 99668, 0, 99667, 0, 99666, 0, 99665, 0, 99664, 0, 99663, 0, 99662, 0, 99661, 0, 99660, 0, 99659, 0, 99658, 0, 99657, 0, 99656, 0, 99655, 0, 99654, 0, 99653, 0, 99652, 0, 99651, 0, 99650, 0, 99649, 0, 99648, 0, 99647, 0, 99646, 0, 99645, 0, 99644, 0, 99643, 0, 99642, 0, 99641, 0, 99640, 0, 99639, 0, 99638, 0, 99637, 0, 99636, 0, 99635, 0, 99634, 0, 99633, 0, 99632, 0, 99631, 0, 99630, 0, 99629, 0, 99628, 0, 99627, 0, 99626, 0, 99625, 0, 99624, 0, 99623, 0, 99622, 0, 99621, 0, 99620, 0, 99619, 0, 99618, 0, 99617, 0, 99616, 0, 99615, 0, 99614, 0, 99613, 0, 99612, 0, 99611, 0, 99610, 0, 99609, 0, 99608, 0, 99607, 0, 99606, 0, 99605, 0, 99604, 0, 99603, 0, 99602, 0, 99601, 0, 99600, 0, 99599, 0, 99598, 0, 99597, 0, 99596, 0, 99595, 0, 99594, 0, 99593, 0, 99592, 0, 99591, 0, 99590, 0, 99589, 0, 99588, 0, 99587, 0, 99586, 0, 99585, 0, 99584, 0, 99583, 0, 99582, 0, 99581, 0, 99580, 0, 99579, 0, 99578, 0, 99577, 0, 99576, 0, 99575, 0, 99574, 0, 99573, 0, 99572, 0, 99571, 0, 99570, 0, 99569, 0, 99568, 0, 99567, 0, 99566, 0, 99565, 0, 99564, 0, 99563, 0, 99562, 0, 99561, 0, 99560, 0, 99559, 0, 99558, 0, 99557, 0, 99556, 0, 99555, 0, 99554, 0, 99553, 0, 99552, 0, 99551, 0, 99550, 0, 99549, 0, 99548, 0, 99547, 0, 99546, 0, 99545, 0, 99544, 0, 99543, 0, 99542, 0, 99541, 0, 99540, 0, 99539, 0, 99538, 0, 99537, 0, 99536, 0, 99535, 0, 99534, 0, 99533, 0, 99532, 0, 99531, 0, 99530, 0, 99529, 0, 99528, 0, 99527, 0, 99526, 0, 99525, 0, 99524, 0, 99523, 0, 99522, 0, 99521, 0, 99520, 0, 99519, 0, 99518, 0, 99517, 0, 99516, 0, 99515, 0, 99514, 0, 99513, 0, 99512, 0, 99511, 0, 99510, 0, 99509, 0, 99508, 0, 99507, 0, 99506, 0, 99505, 0, 99504, 0, 99503, 0, 99502, 0, 99501, 0, 99500, 0, 99499, 0, 99498, 0, 99497, 0, 99496, 0, 99495, 0, 99494, 0, 99493, 0, 99492, 0, 99491, 0, 99490, 0, 99489, 0, 99488, 0, 99487, 0, 99486, 0, 99485, 0, 99484, 0, 99483, 0, 99482, 0, 99481, 0, 99480, 0, 99479, 0, 99478, 0, 99477, 0, 99476, 0, 99475, 0, 99474, 0, 99473, 0, 99472, 0, 99471, 0, 99470, 0, 99469, 0, 99468, 0, 99467, 0, 99466, 0, 99465, 0, 99464, 0, 99463, 0, 99462, 0, 99461, 0, 99460, 0, 99459, 0, 99458, 0, 99457, 0, 99456, 0, 99455, 0, 99454, 0, 99453, 0, 99452, 0, 99451, 0, 99450, 0, 99449, 0, 99448, 0, 99447, 0, 99446, 0, 99445, 0, 99444, 0, 99443, 0, 99442, 0, 99441, 0, 99440, 0, 99439, 0, 99438, 0, 99437, 0, 99436, 0, 99435, 0, 99434, 0, 99433, 0, 99432, 0, 99431, 0, 99430, 0, 99429, 0, 99428, 0, 99427, 0, 99426, 0, 99425, 0, 99424, 0, 99423, 0, 99422, 0, 99421, 0, 99420, 0, 99419, 0, 99418, 0, 99417, 0, 99416, 0, 99415, 0, 99414, 0, 99413, 0, 99412, 0, 99411, 0, 99410, 0, 99409, 0, 99408, 0, 99407, 0, 99406, 0, 99405, 0, 99404, 0, 99403, 0, 99402, 0, 99401, 0, 99400, 0, 99399, 0, 99398, 0, 99397, 0, 99396, 0, 99395, 0, 99394, 0, 99393, 0, 99392, 0, 99391, 0, 99390, 0, 99389, 0, 99388, 0, 99387, 0, 99386, 0, 99385, 0, 99384, 0, 99383, 0, 99382, 0, 99381, 0, 99380, 0, 99379, 0, 99378, 0, 99377, 0, 99376, 0, 99375, 0, 99374, 0, 99373, 0, 99372, 0, 99371, 0, 99370, 0, 99369, 0, 99368, 0, 99367, 0, 99366, 0, 99365, 0, 99364, 0, 99363, 0, 99362, 0, 99361, 0, 99360, 0, 99359, 0, 99358, 0, 99357, 0, 99356, 0, 99355, 0, 99354, 0, 99353, 0, 99352, 0, 99351, 0, 99350, 0, 99349, 0, 99348, 0, 99347, 0, 99346, 0, 99345, 0, 99344, 0, 99343, 0, 99342, 0, 99341, 0, 99340, 0, 99339, 0, 99338, 0, 99337, 0, 99336, 0, 99335, 0, 99334, 0, 99333, 0, 99332, 0, 99331, 0, 99330, 0, 99329, 0, 99328, 0, 99327, 0, 99326, 0, 99325, 0, 99324, 0, 99323, 0, 99322, 0, 99321, 0, 99320, 0, 99319, 0, 99318, 0, 99317, 0, 99316, 0, 99315, 0, 99314, 0, 99313, 0, 99312, 0, 99311, 0, 99310, 0, 99309, 0, 99308, 0, 99307, 0, 99306, 0, 99305, 0, 99304, 0, 99303, 0, 99302, 0, 99301, 0, 99300, 0, 99299, 0, 99298, 0, 99297, 0, 99296, 0, 99295, 0, 99294, 0, 99293, 0, 99292, 0, 99291, 0, 99290, 0, 99289, 0, 99288, 0, 99287, 0, 99286, 0, 99285, 0, 99284, 0, 99283, 0, 99282, 0, 99281, 0, 99280, 0, 99279, 0, 99278, 0, 99277, 0, 99276, 0, 99275, 0, 99274, 0, 99273, 0, 99272, 0, 99271, 0, 99270, 0, 99269, 0, 99268, 0, 99267, 0, 99266, 0, 99265, 0, 99264, 0, 99263, 0, 99262, 0, 99261, 0, 99260, 0, 99259, 0, 99258, 0, 99257, 0, 99256, 0, 99255, 0, 99254, 0, 99253, 0, 99252, 0, 99251, 0, 99250, 0, 99249, 0, 99248, 0, 99247, 0, 99246, 0, 99245, 0, 99244, 0, 99243, 0, 99242, 0, 99241, 0, 99240, 0, 99239, 0, 99238, 0, 99237, 0, 99236, 0, 99235, 0, 99234, 0, 99233, 0, 99232, 0, 99231, 0, 99230, 0, 99229, 0, 99228, 0, 99227, 0, 99226, 0, 99225, 0, 99224, 0, 99223, 0, 99222, 0, 99221, 0, 99220, 0, 99219, 0, 99218, 0, 99217, 0, 99216, 0, 99215, 0, 99214, 0, 99213, 0, 99212, 0, 99211, 0, 99210, 0, 99209, 0, 99208, 0, 99207, 0, 99206, 0, 99205, 0, 99204, 0, 99203, 0, 99202, 0, 99201, 0, 99200, 0, 99199, 0, 99198, 0, 99197, 0, 99196, 0, 99195, 0, 99194, 0, 99193, 0, 99192, 0, 99191, 0, 99190, 0, 99189, 0, 99188, 0, 99187, 0, 99186, 0, 99185, 0, 99184, 0, 99183, 0, 99182, 0, 99181, 0, 99180, 0, 99179, 0, 99178, 0, 99177, 0, 99176, 0, 99175, 0, 99174, 0, 99173, 0, 99172, 0, 99171, 0, 99170, 0, 99169, 0, 99168, 0, 99167, 0, 99166, 0, 99165, 0, 99164, 0, 99163, 0, 99162, 0, 99161, 0, 99160, 0, 99159, 0, 99158, 0, 99157, 0, 99156, 0, 99155, 0, 99154, 0, 99153, 0, 99152, 0, 99151, 0, 99150, 0, 99149, 0, 99148, 0, 99147, 0, 99146, 0, 99145, 0, 99144, 0, 99143, 0, 99142, 0, 99141, 0, 99140, 0, 99139, 0, 99138, 0, 99137, 0, 99136, 0, 99135, 0, 99134, 0, 99133, 0, 99132, 0, 99131, 0, 99130, 0, 99129, 0, 99128, 0, 99127, 0, 99126, 0, 99125, 0, 99124, 0, 99123, 0, 99122, 0, 99121, 0, 99120, 0, 99119, 0, 99118, 0, 99117, 0, 99116, 0, 99115, 0, 99114, 0, 99113, 0, 99112, 0, 99111, 0, 99110, 0, 99109, 0, 99108, 0, 99107, 0, 99106, 0, 99105, 0, 99104, 0, 99103, 0, 99102, 0, 99101, 0, 99100, 0, 99099, 0, 99098, 0, 99097, 0, 99096, 0, 99095, 0, 99094, 0, 99093, 0, 99092, 0, 99091, 0, 99090, 0, 99089, 0, 99088, 0, 99087, 0, 99086, 0, 99085, 0, 99084, 0, 99083, 0, 99082, 0, 99081, 0, 99080, 0, 99079, 0, 99078, 0, 99077, 0, 99076, 0, 99075, 0, 99074, 0, 99073, 0, 99072, 0, 99071, 0, 99070, 0, 99069, 0, 99068, 0, 99067, 0, 99066, 0, 99065, 0, 99064, 0, 99063, 0, 99062, 0, 99061, 0, 99060, 0, 99059, 0, 99058, 0, 99057, 0, 99056, 0, 99055, 0, 99054, 0, 99053, 0, 99052, 0, 99051, 0, 99050, 0, 99049, 0, 99048, 0, 99047, 0, 99046, 0, 99045, 0, 99044, 0, 99043, 0, 99042, 0, 99041, 0, 99040, 0, 99039, 0, 99038, 0, 99037, 0, 99036, 0, 99035, 0, 99034, 0, 99033, 0, 99032, 0, 99031, 0, 99030, 0, 99029, 0, 99028, 0, 99027, 0, 99026, 0, 99025, 0, 99024, 0, 99023, 0, 99022, 0, 99021, 0, 99020, 0, 99019, 0, 99018, 0, 99017, 0, 99016, 0, 99015, 0, 99014, 0, 99013, 0, 99012, 0, 99011, 0, 99010, 0, 99009, 0, 99008, 0, 99007, 0, 99006, 0, 99005, 0, 99004, 0, 99003, 0, 99002, 0, 99001, 0, 99000, 0, 98999, 0, 98998, 0, 98997, 0, 98996, 0, 98995, 0, 98994, 0, 98993, 0, 98992, 0, 98991, 0, 98990, 0, 98989, 0, 98988, 0, 98987, 0, 98986, 0, 98985, 0, 98984, 0, 98983, 0, 98982, 0, 98981, 0, 98980, 0, 98979, 0, 98978, 0, 98977, 0, 98976, 0, 98975, 0, 98974, 0, 98973, 0, 98972, 0, 98971, 0, 98970, 0, 98969, 0, 98968, 0, 98967, 0, 98966, 0, 98965, 0, 98964, 0, 98963, 0, 98962, 0, 98961, 0, 98960, 0, 98959, 0, 98958, 0, 98957, 0, 98956, 0, 98955, 0, 98954, 0, 98953, 0, 98952, 0, 98951, 0, 98950, 0, 98949, 0, 98948, 0, 98947, 0, 98946, 0, 98945, 0, 98944, 0, 98943, 0, 98942, 0, 98941, 0, 98940, 0, 98939, 0, 98938, 0, 98937, 0, 98936, 0, 98935, 0, 98934, 0, 98933, 0, 98932, 0, 98931, 0, 98930, 0, 98929, 0, 98928, 0, 98927, 0, 98926, 0, 98925, 0, 98924, 0, 98923, 0, 98922, 0, 98921, 0, 98920, 0, 98919, 0, 98918, 0, 98917, 0, 98916, 0, 98915, 0, 98914, 0, 98913, 0, 98912, 0, 98911, 0, 98910, 0, 98909, 0, 98908, 0, 98907, 0, 98906, 0, 98905, 0, 98904, 0, 98903, 0, 98902, 0, 98901, 0, 98900, 0, 98899, 0, 98898, 0, 98897, 0, 98896, 0, 98895, 0, 98894, 0, 98893, 0, 98892, 0, 98891, 0, 98890, 0, 98889, 0, 98888, 0, 98887, 0, 98886, 0, 98885, 0, 98884, 0, 98883, 0, 98882, 0, 98881, 0, 98880, 0, 98879, 0, 98878, 0, 98877, 0, 98876, 0, 98875, 0, 98874, 0, 98873, 0, 98872, 0, 98871, 0, 98870, 0, 98869, 0, 98868, 0, 98867, 0, 98866, 0, 98865, 0, 98864, 0, 98863, 0, 98862, 0, 98861, 0, 98860, 0, 98859, 0, 98858, 0, 98857, 0, 98856, 0, 98855, 0, 98854, 0, 98853, 0, 98852, 0, 98851, 0, 98850, 0, 98849, 0, 98848, 0, 98847, 0, 98846, 0, 98845, 0, 98844, 0, 98843, 0, 98842, 0, 98841, 0, 98840, 0, 98839, 0, 98838, 0, 98837, 0, 98836, 0, 98835, 0, 98834, 0, 98833, 0, 98832, 0, 98831, 0, 98830, 0, 98829, 0, 98828, 0, 98827, 0, 98826, 0, 98825, 0, 98824, 0, 98823, 0, 98822, 0, 98821, 0, 98820, 0, 98819, 0, 98818, 0, 98817, 0, 98816, 0, 98815, 0, 98814, 0, 98813, 0, 98812, 0, 98811, 0, 98810, 0, 98809, 0, 98808, 0, 98807, 0, 98806, 0, 98805, 0, 98804, 0, 98803, 0, 98802, 0, 98801, 0, 98800, 0, 98799, 0, 98798, 0, 98797, 0, 98796, 0, 98795, 0, 98794, 0, 98793, 0, 98792, 0, 98791, 0, 98790, 0, 98789, 0, 98788, 0, 98787, 0, 98786, 0, 98785, 0, 98784, 0, 98783, 0, 98782, 0, 98781, 0, 98780, 0, 98779, 0, 98778, 0, 98777, 0, 98776, 0, 98775, 0, 98774, 0, 98773, 0, 98772, 0, 98771, 0, 98770, 0, 98769, 0, 98768, 0, 98767, 0, 98766, 0, 98765, 0, 98764, 0, 98763, 0, 98762, 0, 98761, 0, 98760, 0, 98759, 0, 98758, 0, 98757, 0, 98756, 0, 98755, 0, 98754, 0, 98753, 0, 98752, 0, 98751, 0, 98750, 0, 98749, 0, 98748, 0, 98747, 0, 98746, 0, 98745, 0, 98744, 0, 98743, 0, 98742, 0, 98741, 0, 98740, 0, 98739, 0, 98738, 0, 98737, 0, 98736, 0, 98735, 0, 98734, 0, 98733, 0, 98732, 0, 98731, 0, 98730, 0, 98729, 0, 98728, 0, 98727, 0, 98726, 0, 98725, 0, 98724, 0, 98723, 0, 98722, 0, 98721, 0, 98720, 0, 98719, 0, 98718, 0, 98717, 0, 98716, 0, 98715, 0, 98714, 0, 98713, 0, 98712, 0, 98711, 0, 98710, 0, 98709, 0, 98708, 0, 98707, 0, 98706, 0, 98705, 0, 98704, 0, 98703, 0, 98702, 0, 98701, 0, 98700, 0, 98699, 0, 98698, 0, 98697, 0, 98696, 0, 98695, 0, 98694, 0, 98693, 0, 98692, 0, 98691, 0, 98690, 0, 98689, 0, 98688, 0, 98687, 0, 98686, 0, 98685, 0, 98684, 0, 98683, 0, 98682, 0, 98681, 0, 98680, 0, 98679, 0, 98678, 0, 98677, 0, 98676, 0, 98675, 0, 98674, 0, 98673, 0, 98672, 0, 98671, 0, 98670, 0, 98669, 0, 98668, 0, 98667, 0, 98666, 0, 98665, 0, 98664, 0, 98663, 0, 98662, 0, 98661, 0, 98660, 0, 98659, 0, 98658, 0, 98657, 0, 98656, 0, 98655, 0, 98654, 0, 98653, 0, 98652, 0, 98651, 0, 98650, 0, 98649, 0, 98648, 0, 98647, 0, 98646, 0, 98645, 0, 98644, 0, 98643, 0, 98642, 0, 98641, 0, 98640, 0, 98639, 0, 98638, 0, 98637, 0, 98636, 0, 98635, 0, 98634, 0, 98633, 0, 98632, 0, 98631, 0, 98630, 0, 98629, 0, 98628, 0, 98627, 0, 98626, 0, 98625, 0, 98624, 0, 98623, 0, 98622, 0, 98621, 0, 98620, 0, 98619, 0, 98618, 0, 98617, 0, 98616, 0, 98615, 0, 98614, 0, 98613, 0, 98612, 0, 98611, 0, 98610, 0, 98609, 0, 98608, 0, 98607, 0, 98606, 0, 98605, 0, 98604, 0, 98603, 0, 98602, 0, 98601, 0, 98600, 0, 98599, 0, 98598, 0, 98597, 0, 98596, 0, 98595, 0, 98594, 0, 98593, 0, 98592, 0, 98591, 0, 98590, 0, 98589, 0, 98588, 0, 98587, 0, 98586, 0, 98585, 0, 98584, 0, 98583, 0, 98582, 0, 98581, 0, 98580, 0, 98579, 0, 98578, 0, 98577, 0, 98576, 0, 98575, 0, 98574, 0, 98573, 0, 98572, 0, 98571, 0, 98570, 0, 98569, 0, 98568, 0, 98567, 0, 98566, 0, 98565, 0, 98564, 0, 98563, 0, 98562, 0, 98561, 0, 98560, 0, 98559, 0, 98558, 0, 98557, 0, 98556, 0, 98555, 0, 98554, 0, 98553, 0, 98552, 0, 98551, 0, 98550, 0, 98549, 0, 98548, 0, 98547, 0, 98546, 0, 98545, 0, 98544, 0, 98543, 0, 98542, 0, 98541, 0, 98540, 0, 98539, 0, 98538, 0, 98537, 0, 98536, 0, 98535, 0, 98534, 0, 98533, 0, 98532, 0, 98531, 0, 98530, 0, 98529, 0, 98528, 0, 98527, 0, 98526, 0, 98525, 0, 98524, 0, 98523, 0, 98522, 0, 98521, 0, 98520, 0, 98519, 0, 98518, 0, 98517, 0, 98516, 0, 98515, 0, 98514, 0, 98513, 0, 98512, 0, 98511, 0, 98510, 0, 98509, 0, 98508, 0, 98507, 0, 98506, 0, 98505, 0, 98504, 0, 98503, 0, 98502, 0, 98501, 0, 98500, 0, 98499, 0, 98498, 0, 98497, 0, 98496, 0, 98495, 0, 98494, 0, 98493, 0, 98492, 0, 98491, 0, 98490, 0, 98489, 0, 98488, 0, 98487, 0, 98486, 0, 98485, 0, 98484, 0, 98483, 0, 98482, 0, 98481, 0, 98480, 0, 98479, 0, 98478, 0, 98477, 0, 98476, 0, 98475, 0, 98474, 0, 98473, 0, 98472, 0, 98471, 0, 98470, 0, 98469, 0, 98468, 0, 98467, 0, 98466, 0, 98465, 0, 98464, 0, 98463, 0, 98462, 0, 98461, 0, 98460, 0, 98459, 0, 98458, 0, 98457, 0, 98456, 0, 98455, 0, 98454, 0, 98453, 0, 98452, 0, 98451, 0, 98450, 0, 98449, 0, 98448, 0, 98447, 0, 98446, 0, 98445, 0, 98444, 0, 98443, 0, 98442, 0, 98441, 0, 98440, 0, 98439, 0, 98438, 0, 98437, 0, 98436, 0, 98435, 0, 98434, 0, 98433, 0, 98432, 0, 98431, 0, 98430, 0, 98429, 0, 98428, 0, 98427, 0, 98426, 0, 98425, 0, 98424, 0, 98423, 0, 98422, 0, 98421, 0, 98420, 0, 98419, 0, 98418, 0, 98417, 0, 98416, 0, 98415, 0, 98414, 0, 98413, 0, 98412, 0, 98411, 0, 98410, 0, 98409, 0, 98408, 0, 98407, 0, 98406, 0, 98405, 0, 98404, 0, 98403, 0, 98402, 0, 98401, 0, 98400, 0, 98399, 0, 98398, 0, 98397, 0, 98396, 0, 98395, 0, 98394, 0, 98393, 0, 98392, 0, 98391, 0, 98390, 0, 98389, 0, 98388, 0, 98387, 0, 98386, 0, 98385, 0, 98384, 0, 98383, 0, 98382, 0, 98381, 0, 98380, 0, 98379, 0, 98378, 0, 98377, 0, 98376, 0, 98375, 0, 98374, 0, 98373, 0, 98372, 0, 98371, 0, 98370, 0, 98369, 0, 98368, 0, 98367, 0, 98366, 0, 98365, 0, 98364, 0, 98363, 0, 98362, 0, 98361, 0, 98360, 0, 98359, 0, 98358, 0, 98357, 0, 98356, 0, 98355, 0, 98354, 0, 98353, 0, 98352, 0, 98351, 0, 98350, 0, 98349, 0, 98348, 0, 98347, 0, 98346, 0, 98345, 0, 98344, 0, 98343, 0, 98342, 0, 98341, 0, 98340, 0, 98339, 0, 98338, 0, 98337, 0, 98336, 0, 98335, 0, 98334, 0, 98333, 0, 98332, 0, 98331, 0, 98330, 0, 98329, 0, 98328, 0, 98327, 0, 98326, 0, 98325, 0, 98324, 0, 98323, 0, 98322, 0, 98321, 0, 98320, 0, 98319, 0, 98318, 0, 98317, 0, 98316, 0, 98315, 0, 98314, 0, 98313, 0, 98312, 0, 98311, 0, 98310, 0, 98309, 0, 98308, 0, 98307, 0, 98306, 0, 98305, 0, 98304, 0, 98303, 0, 98302, 0, 98301, 0, 98300, 0, 98299, 0, 98298, 0, 98297, 0, 98296, 0, 98295, 0, 98294, 0, 98293, 0, 98292, 0, 98291, 0, 98290, 0, 98289, 0, 98288, 0, 98287, 0, 98286, 0, 98285, 0, 98284, 0, 98283, 0, 98282, 0, 98281, 0, 98280, 0, 98279, 0, 98278, 0, 98277, 0, 98276, 0, 98275, 0, 98274, 0, 98273, 0, 98272, 0, 98271, 0, 98270, 0, 98269, 0, 98268, 0, 98267, 0, 98266, 0, 98265, 0, 98264, 0, 98263, 0, 98262, 0, 98261, 0, 98260, 0, 98259, 0, 98258, 0, 98257, 0, 98256, 0, 98255, 0, 98254, 0, 98253, 0, 98252, 0, 98251, 0, 98250, 0, 98249, 0, 98248, 0, 98247, 0, 98246, 0, 98245, 0, 98244, 0, 98243, 0, 98242, 0, 98241, 0, 98240, 0, 98239, 0, 98238, 0, 98237, 0, 98236, 0, 98235, 0, 98234, 0, 98233, 0, 98232, 0, 98231, 0, 98230, 0, 98229, 0, 98228, 0, 98227, 0, 98226, 0, 98225, 0, 98224, 0, 98223, 0, 98222, 0, 98221, 0, 98220, 0, 98219, 0, 98218, 0, 98217, 0, 98216, 0, 98215, 0, 98214, 0, 98213, 0, 98212, 0, 98211, 0, 98210, 0, 98209, 0, 98208, 0, 98207, 0, 98206, 0, 98205, 0, 98204, 0, 98203, 0, 98202, 0, 98201, 0, 98200, 0, 98199, 0, 98198, 0, 98197, 0, 98196, 0, 98195, 0, 98194, 0, 98193, 0, 98192, 0, 98191, 0, 98190, 0, 98189, 0, 98188, 0, 98187, 0, 98186, 0, 98185, 0, 98184, 0, 98183, 0, 98182, 0, 98181, 0, 98180, 0, 98179, 0, 98178, 0, 98177, 0, 98176, 0, 98175, 0, 98174, 0, 98173, 0, 98172, 0, 98171, 0, 98170, 0, 98169, 0, 98168, 0, 98167, 0, 98166, 0, 98165, 0, 98164, 0, 98163, 0, 98162, 0, 98161, 0, 98160, 0, 98159, 0, 98158, 0, 98157, 0, 98156, 0, 98155, 0, 98154, 0, 98153, 0, 98152, 0, 98151, 0, 98150, 0, 98149, 0, 98148, 0, 98147, 0, 98146, 0, 98145, 0, 98144, 0, 98143, 0, 98142, 0, 98141, 0, 98140, 0, 98139, 0, 98138, 0, 98137, 0, 98136, 0, 98135, 0, 98134, 0, 98133, 0, 98132, 0, 98131, 0, 98130, 0, 98129, 0, 98128, 0, 98127, 0, 98126, 0, 98125, 0, 98124, 0, 98123, 0, 98122, 0, 98121, 0, 98120, 0, 98119, 0, 98118, 0, 98117, 0, 98116, 0, 98115, 0, 98114, 0, 98113, 0, 98112, 0, 98111, 0, 98110, 0, 98109, 0, 98108, 0, 98107, 0, 98106, 0, 98105, 0, 98104, 0, 98103, 0, 98102, 0, 98101, 0, 98100, 0, 98099, 0, 98098, 0, 98097, 0, 98096, 0, 98095, 0, 98094, 0, 98093, 0, 98092, 0, 98091, 0, 98090, 0, 98089, 0, 98088, 0, 98087, 0, 98086, 0, 98085, 0, 98084, 0, 98083, 0, 98082, 0, 98081, 0, 98080, 0, 98079, 0, 98078, 0, 98077, 0, 98076, 0, 98075, 0, 98074, 0, 98073, 0, 98072, 0, 98071, 0, 98070, 0, 98069, 0, 98068, 0, 98067, 0, 98066, 0, 98065, 0, 98064, 0, 98063, 0, 98062, 0, 98061, 0, 98060, 0, 98059, 0, 98058, 0, 98057, 0, 98056, 0, 98055, 0, 98054, 0, 98053, 0, 98052, 0, 98051, 0, 98050, 0, 98049, 0, 98048, 0, 98047, 0, 98046, 0, 98045, 0, 98044, 0, 98043, 0, 98042, 0, 98041, 0, 98040, 0, 98039, 0, 98038, 0, 98037, 0, 98036, 0, 98035, 0, 98034, 0, 98033, 0, 98032, 0, 98031, 0, 98030, 0, 98029, 0, 98028, 0, 98027, 0, 98026, 0, 98025, 0, 98024, 0, 98023, 0, 98022, 0, 98021, 0, 98020, 0, 98019, 0, 98018, 0, 98017, 0, 98016, 0, 98015, 0, 98014, 0, 98013, 0, 98012, 0, 98011, 0, 98010, 0, 98009, 0, 98008, 0, 98007, 0, 98006, 0, 98005, 0, 98004, 0, 98003, 0, 98002, 0, 98001, 0, 98000, 0, 97999, 0, 97998, 0, 97997, 0, 97996, 0, 97995, 0, 97994, 0, 97993, 0, 97992, 0, 97991, 0, 97990, 0, 97989, 0, 97988, 0, 97987, 0, 97986, 0, 97985, 0, 97984, 0, 97983, 0, 97982, 0, 97981, 0, 97980, 0, 97979, 0, 97978, 0, 97977, 0, 97976, 0, 97975, 0, 97974, 0, 97973, 0, 97972, 0, 97971, 0, 97970, 0, 97969, 0, 97968, 0, 97967, 0, 97966, 0, 97965, 0, 97964, 0, 97963, 0, 97962, 0, 97961, 0, 97960, 0, 97959, 0, 97958, 0, 97957, 0, 97956, 0, 97955, 0, 97954, 0, 97953, 0, 97952, 0, 97951, 0, 97950, 0, 97949, 0, 97948, 0, 97947, 0, 97946, 0, 97945, 0, 97944, 0, 97943, 0, 97942, 0, 97941, 0, 97940, 0, 97939, 0, 97938, 0, 97937, 0, 97936, 0, 97935, 0, 97934, 0, 97933, 0, 97932, 0, 97931, 0, 97930, 0, 97929, 0, 97928, 0, 97927, 0, 97926, 0, 97925, 0, 97924, 0, 97923, 0, 97922, 0, 97921, 0, 97920, 0, 97919, 0, 97918, 0, 97917, 0, 97916, 0, 97915, 0, 97914, 0, 97913, 0, 97912, 0, 97911, 0, 97910, 0, 97909, 0, 97908, 0, 97907, 0, 97906, 0, 97905, 0, 97904, 0, 97903, 0, 97902, 0, 97901, 0, 97900, 0, 97899, 0, 97898, 0, 97897, 0, 97896, 0, 97895, 0, 97894, 0, 97893, 0, 97892, 0, 97891, 0, 97890, 0, 97889, 0, 97888, 0, 97887, 0, 97886, 0, 97885, 0, 97884, 0, 97883, 0, 97882, 0, 97881, 0, 97880, 0, 97879, 0, 97878, 0, 97877, 0, 97876, 0, 97875, 0, 97874, 0, 97873, 0, 97872, 0, 97871, 0, 97870, 0, 97869, 0, 97868, 0, 97867, 0, 97866, 0, 97865, 0, 97864, 0, 97863, 0, 97862, 0, 97861, 0, 97860, 0, 97859, 0, 97858, 0, 97857, 0, 97856, 0, 97855, 0, 97854, 0, 97853, 0, 97852, 0, 97851, 0, 97850, 0, 97849, 0, 97848, 0, 97847, 0, 97846, 0, 97845, 0, 97844, 0, 97843, 0, 97842, 0, 97841, 0, 97840, 0, 97839, 0, 97838, 0, 97837, 0, 97836, 0, 97835, 0, 97834, 0, 97833, 0, 97832, 0, 97831, 0, 97830, 0, 97829, 0, 97828, 0, 97827, 0, 97826, 0, 97825, 0, 97824, 0, 97823, 0, 97822, 0, 97821, 0, 97820, 0, 97819, 0, 97818, 0, 97817, 0, 97816, 0, 97815, 0, 97814, 0, 97813, 0, 97812, 0, 97811, 0, 97810, 0, 97809, 0, 97808, 0, 97807, 0, 97806, 0, 97805, 0, 97804, 0, 97803, 0, 97802, 0, 97801, 0, 97800, 0, 97799, 0, 97798, 0, 97797, 0, 97796, 0, 97795, 0, 97794, 0, 97793, 0, 97792, 0, 97791, 0, 97790, 0, 97789, 0, 97788, 0, 97787, 0, 97786, 0, 97785, 0, 97784, 0, 97783, 0, 97782, 0, 97781, 0, 97780, 0, 97779, 0, 97778, 0, 97777, 0, 97776, 0, 97775, 0, 97774, 0, 97773, 0, 97772, 0, 97771, 0, 97770, 0, 97769, 0, 97768, 0, 97767, 0, 97766, 0, 97765, 0, 97764, 0, 97763, 0, 97762, 0, 97761, 0, 97760, 0, 97759, 0, 97758, 0, 97757, 0, 97756, 0, 97755, 0, 97754, 0, 97753, 0, 97752, 0, 97751, 0, 97750, 0, 97749, 0, 97748, 0, 97747, 0, 97746, 0, 97745, 0, 97744, 0, 97743, 0, 97742, 0, 97741, 0, 97740, 0, 97739, 0, 97738, 0, 97737, 0, 97736, 0, 97735, 0, 97734, 0, 97733, 0, 97732, 0, 97731, 0, 97730, 0, 97729, 0, 97728, 0, 97727, 0, 97726, 0, 97725, 0, 97724, 0, 97723, 0, 97722, 0, 97721, 0, 97720, 0, 97719, 0, 97718, 0, 97717, 0, 97716, 0, 97715, 0, 97714, 0, 97713, 0, 97712, 0, 97711, 0, 97710, 0, 97709, 0, 97708, 0, 97707, 0, 97706, 0, 97705, 0, 97704, 0, 97703, 0, 97702, 0, 97701, 0, 97700, 0, 97699, 0, 97698, 0, 97697, 0, 97696, 0, 97695, 0, 97694, 0, 97693, 0, 97692, 0, 97691, 0, 97690, 0, 97689, 0, 97688, 0, 97687, 0, 97686, 0, 97685, 0, 97684, 0, 97683, 0, 97682, 0, 97681, 0, 97680, 0, 97679, 0, 97678, 0, 97677, 0, 97676, 0, 97675, 0, 97674, 0, 97673, 0, 97672, 0, 97671, 0, 97670, 0, 97669, 0, 97668, 0, 97667, 0, 97666, 0, 97665, 0, 97664, 0, 97663, 0, 97662, 0, 97661, 0, 97660, 0, 97659, 0, 97658, 0, 97657, 0, 97656, 0, 97655, 0, 97654, 0, 97653, 0, 97652, 0, 97651, 0, 97650, 0, 97649, 0, 97648, 0, 97647, 0, 97646, 0, 97645, 0, 97644, 0, 97643, 0, 97642, 0, 97641, 0, 97640, 0, 97639, 0, 97638, 0, 97637, 0, 97636, 0, 97635, 0, 97634, 0, 97633, 0, 97632, 0, 97631, 0, 97630, 0, 97629, 0, 97628, 0, 97627, 0, 97626, 0, 97625, 0, 97624, 0, 97623, 0, 97622, 0, 97621, 0, 97620, 0, 97619, 0, 97618, 0, 97617, 0, 97616, 0, 97615, 0, 97614, 0, 97613, 0, 97612, 0, 97611, 0, 97610, 0, 97609, 0, 97608, 0, 97607, 0, 97606, 0, 97605, 0, 97604, 0, 97603, 0, 97602, 0, 97601, 0, 97600, 0, 97599, 0, 97598, 0, 97597, 0, 97596, 0, 97595, 0, 97594, 0, 97593, 0, 97592, 0, 97591, 0, 97590, 0, 97589, 0, 97588, 0, 97587, 0, 97586, 0, 97585, 0, 97584, 0, 97583, 0, 97582, 0, 97581, 0, 97580, 0, 97579, 0, 97578, 0, 97577, 0, 97576, 0, 97575, 0, 97574, 0, 97573, 0, 97572, 0, 97571, 0, 97570, 0, 97569, 0, 97568, 0, 97567, 0, 97566, 0, 97565, 0, 97564, 0, 97563, 0, 97562, 0, 97561, 0, 97560, 0, 97559, 0, 97558, 0, 97557, 0, 97556, 0, 97555, 0, 97554, 0, 97553, 0, 97552, 0, 97551, 0, 97550, 0, 97549, 0, 97548, 0, 97547, 0, 97546, 0, 97545, 0, 97544, 0, 97543, 0, 97542, 0, 97541, 0, 97540, 0, 97539, 0, 97538, 0, 97537, 0, 97536, 0, 97535, 0, 97534, 0, 97533, 0, 97532, 0, 97531, 0, 97530, 0, 97529, 0, 97528, 0, 97527, 0, 97526, 0, 97525, 0, 97524, 0, 97523, 0, 97522, 0, 97521, 0, 97520, 0, 97519, 0, 97518, 0, 97517, 0, 97516, 0, 97515, 0, 97514, 0, 97513, 0, 97512, 0, 97511, 0, 97510, 0, 97509, 0, 97508, 0, 97507, 0, 97506, 0, 97505, 0, 97504, 0, 97503, 0, 97502, 0, 97501, 0, 97500, 0, 97499, 0, 97498, 0, 97497, 0, 97496, 0, 97495, 0, 97494, 0, 97493, 0, 97492, 0, 97491, 0, 97490, 0, 97489, 0, 97488, 0, 97487, 0, 97486, 0, 97485, 0, 97484, 0, 97483, 0, 97482, 0, 97481, 0, 97480, 0, 97479, 0, 97478, 0, 97477, 0, 97476, 0, 97475, 0, 97474, 0, 97473, 0, 97472, 0, 97471, 0, 97470, 0, 97469, 0, 97468, 0, 97467, 0, 97466, 0, 97465, 0, 97464, 0, 97463, 0, 97462, 0, 97461, 0, 97460, 0, 97459, 0, 97458, 0, 97457, 0, 97456, 0, 97455, 0, 97454, 0, 97453, 0, 97452, 0, 97451, 0, 97450, 0, 97449, 0, 97448, 0, 97447, 0, 97446, 0, 97445, 0, 97444, 0, 97443, 0, 97442, 0, 97441, 0, 97440, 0, 97439, 0, 97438, 0, 97437, 0, 97436, 0, 97435, 0, 97434, 0, 97433, 0, 97432, 0, 97431, 0, 97430, 0, 97429, 0, 97428, 0, 97427, 0, 97426, 0, 97425, 0, 97424, 0, 97423, 0, 97422, 0, 97421, 0, 97420, 0, 97419, 0, 97418, 0, 97417, 0, 97416, 0, 97415, 0, 97414, 0, 97413, 0, 97412, 0, 97411, 0, 97410, 0, 97409, 0, 97408, 0, 97407, 0, 97406, 0, 97405, 0, 97404, 0, 97403, 0, 97402, 0, 97401, 0, 97400, 0, 97399, 0, 97398, 0, 97397, 0, 97396, 0, 97395, 0, 97394, 0, 97393, 0, 97392, 0, 97391, 0, 97390, 0, 97389, 0, 97388, 0, 97387, 0, 97386, 0, 97385, 0, 97384, 0, 97383, 0, 97382, 0, 97381, 0, 97380, 0, 97379, 0, 97378, 0, 97377, 0, 97376, 0, 97375, 0, 97374, 0, 97373, 0, 97372, 0, 97371, 0, 97370, 0, 97369, 0, 97368, 0, 97367, 0, 97366, 0, 97365, 0, 97364, 0, 97363, 0, 97362, 0, 97361, 0, 97360, 0, 97359, 0, 97358, 0, 97357, 0, 97356, 0, 97355, 0, 97354, 0, 97353, 0, 97352, 0, 97351, 0, 97350, 0, 97349, 0, 97348, 0, 97347, 0, 97346, 0, 97345, 0, 97344, 0, 97343, 0, 97342, 0, 97341, 0, 97340, 0, 97339, 0, 97338, 0, 97337, 0, 97336, 0, 97335, 0, 97334, 0, 97333, 0, 97332, 0, 97331, 0, 97330, 0, 97329, 0, 97328, 0, 97327, 0, 97326, 0, 97325, 0, 97324, 0, 97323, 0, 97322, 0, 97321, 0, 97320, 0, 97319, 0, 97318, 0, 97317, 0, 97316, 0, 97315, 0, 97314, 0, 97313, 0, 97312, 0, 97311, 0, 97310, 0, 97309, 0, 97308, 0, 97307, 0, 97306, 0, 97305, 0, 97304, 0, 97303, 0, 97302, 0, 97301, 0, 97300, 0, 97299, 0, 97298, 0, 97297, 0, 97296, 0, 97295, 0, 97294, 0, 97293, 0, 97292, 0, 97291, 0, 97290, 0, 97289, 0, 97288, 0, 97287, 0, 97286, 0, 97285, 0, 97284, 0, 97283, 0, 97282, 0, 97281, 0, 97280, 0, 97279, 0, 97278, 0, 97277, 0, 97276, 0, 97275, 0, 97274, 0, 97273, 0, 97272, 0, 97271, 0, 97270, 0, 97269, 0, 97268, 0, 97267, 0, 97266, 0, 97265, 0, 97264, 0, 97263, 0, 97262, 0, 97261, 0, 97260, 0, 97259, 0, 97258, 0, 97257, 0, 97256, 0, 97255, 0, 97254, 0, 97253, 0, 97252, 0, 97251, 0, 97250, 0, 97249, 0, 97248, 0, 97247, 0, 97246, 0, 97245, 0, 97244, 0, 97243, 0, 97242, 0, 97241, 0, 97240, 0, 97239, 0, 97238, 0, 97237, 0, 97236, 0, 97235, 0, 97234, 0, 97233, 0, 97232, 0, 97231, 0, 97230, 0, 97229, 0, 97228, 0, 97227, 0, 97226, 0, 97225, 0, 97224, 0, 97223, 0, 97222, 0, 97221, 0, 97220, 0, 97219, 0, 97218, 0, 97217, 0, 97216, 0, 97215, 0, 97214, 0, 97213, 0, 97212, 0, 97211, 0, 97210, 0, 97209, 0, 97208, 0, 97207, 0, 97206, 0, 97205, 0, 97204, 0, 97203, 0, 97202, 0, 97201, 0, 97200, 0, 97199, 0, 97198, 0, 97197, 0, 97196, 0, 97195, 0, 97194, 0, 97193, 0, 97192, 0, 97191, 0, 97190, 0, 97189, 0, 97188, 0, 97187, 0, 97186, 0, 97185, 0, 97184, 0, 97183, 0, 97182, 0, 97181, 0, 97180, 0, 97179, 0, 97178, 0, 97177, 0, 97176, 0, 97175, 0, 97174, 0, 97173, 0, 97172, 0, 97171, 0, 97170, 0, 97169, 0, 97168, 0, 97167, 0, 97166, 0, 97165, 0, 97164, 0, 97163, 0, 97162, 0, 97161, 0, 97160, 0, 97159, 0, 97158, 0, 97157, 0, 97156, 0, 97155, 0, 97154, 0, 97153, 0, 97152, 0, 97151, 0, 97150, 0, 97149, 0, 97148, 0, 97147, 0, 97146, 0, 97145, 0, 97144, 0, 97143, 0, 97142, 0, 97141, 0, 97140, 0, 97139, 0, 97138, 0, 97137, 0, 97136, 0, 97135, 0, 97134, 0, 97133, 0, 97132, 0, 97131, 0, 97130, 0, 97129, 0, 97128, 0, 97127, 0, 97126, 0, 97125, 0, 97124, 0, 97123, 0, 97122, 0, 97121, 0, 97120, 0, 97119, 0, 97118, 0, 97117, 0, 97116, 0, 97115, 0, 97114, 0, 97113, 0, 97112, 0, 97111, 0, 97110, 0, 97109, 0, 97108, 0, 97107, 0, 97106, 0, 97105, 0, 97104, 0, 97103, 0, 97102, 0, 97101, 0, 97100, 0, 97099, 0, 97098, 0, 97097, 0, 97096, 0, 97095, 0, 97094, 0, 97093, 0, 97092, 0, 97091, 0, 97090, 0, 97089, 0, 97088, 0, 97087, 0, 97086, 0, 97085, 0, 97084, 0, 97083, 0, 97082, 0, 97081, 0, 97080, 0, 97079, 0, 97078, 0, 97077, 0, 97076, 0, 97075, 0, 97074, 0, 97073, 0, 97072, 0, 97071, 0, 97070, 0, 97069, 0, 97068, 0, 97067, 0, 97066, 0, 97065, 0, 97064, 0, 97063, 0, 97062, 0, 97061, 0, 97060, 0, 97059, 0, 97058, 0, 97057, 0, 97056, 0, 97055, 0, 97054, 0, 97053, 0, 97052, 0, 97051, 0, 97050, 0, 97049, 0, 97048, 0, 97047, 0, 97046, 0, 97045, 0, 97044, 0, 97043, 0, 97042, 0, 97041, 0, 97040, 0, 97039, 0, 97038, 0, 97037, 0, 97036, 0, 97035, 0, 97034, 0, 97033, 0, 97032, 0, 97031, 0, 97030, 0, 97029, 0, 97028, 0, 97027, 0, 97026, 0, 97025, 0, 97024, 0, 97023, 0, 97022, 0, 97021, 0, 97020, 0, 97019, 0, 97018, 0, 97017, 0, 97016, 0, 97015, 0, 97014, 0, 97013, 0, 97012, 0, 97011, 0, 97010, 0, 97009, 0, 97008, 0, 97007, 0, 97006, 0, 97005, 0, 97004, 0, 97003, 0, 97002, 0, 97001, 0, 97000, 0, 96999, 0, 96998, 0, 96997, 0, 96996, 0, 96995, 0, 96994, 0, 96993, 0, 96992, 0, 96991, 0, 96990, 0, 96989, 0, 96988, 0, 96987, 0, 96986, 0, 96985, 0, 96984, 0, 96983, 0, 96982, 0, 96981, 0, 96980, 0, 96979, 0, 96978, 0, 96977, 0, 96976, 0, 96975, 0, 96974, 0, 96973, 0, 96972, 0, 96971, 0, 96970, 0, 96969, 0, 96968, 0, 96967, 0, 96966, 0, 96965, 0, 96964, 0, 96963, 0, 96962, 0, 96961, 0, 96960, 0, 96959, 0, 96958, 0, 96957, 0, 96956, 0, 96955, 0, 96954, 0, 96953, 0, 96952, 0, 96951, 0, 96950, 0, 96949, 0, 96948, 0, 96947, 0, 96946, 0, 96945, 0, 96944, 0, 96943, 0, 96942, 0, 96941, 0, 96940, 0, 96939, 0, 96938, 0, 96937, 0, 96936, 0, 96935, 0, 96934, 0, 96933, 0, 96932, 0, 96931, 0, 96930, 0, 96929, 0, 96928, 0, 96927, 0, 96926, 0, 96925, 0, 96924, 0, 96923, 0, 96922, 0, 96921, 0, 96920, 0, 96919, 0, 96918, 0, 96917, 0, 96916, 0, 96915, 0, 96914, 0, 96913, 0, 96912, 0, 96911, 0, 96910, 0, 96909, 0, 96908, 0, 96907, 0, 96906, 0, 96905, 0, 96904, 0, 96903, 0, 96902, 0, 96901, 0, 96900, 0, 96899, 0, 96898, 0, 96897, 0, 96896, 0, 96895, 0, 96894, 0, 96893, 0, 96892, 0, 96891, 0, 96890, 0, 96889, 0, 96888, 0, 96887, 0, 96886, 0, 96885, 0, 96884, 0, 96883, 0, 96882, 0, 96881, 0, 96880, 0, 96879, 0, 96878, 0, 96877, 0, 96876, 0, 96875, 0, 96874, 0, 96873, 0, 96872, 0, 96871, 0, 96870, 0, 96869, 0, 96868, 0, 96867, 0, 96866, 0, 96865, 0, 96864, 0, 96863, 0, 96862, 0, 96861, 0, 96860, 0, 96859, 0, 96858, 0, 96857, 0, 96856, 0, 96855, 0, 96854, 0, 96853, 0, 96852, 0, 96851, 0, 96850, 0, 96849, 0, 96848, 0, 96847, 0, 96846, 0, 96845, 0, 96844, 0, 96843, 0, 96842, 0, 96841, 0, 96840, 0, 96839, 0, 96838, 0, 96837, 0, 96836, 0, 96835, 0, 96834, 0, 96833, 0, 96832, 0, 96831, 0, 96830, 0, 96829, 0, 96828, 0, 96827, 0, 96826, 0, 96825, 0, 96824, 0, 96823, 0, 96822, 0, 96821, 0, 96820, 0, 96819, 0, 96818, 0, 96817, 0, 96816, 0, 96815, 0, 96814, 0, 96813, 0, 96812, 0, 96811, 0, 96810, 0, 96809, 0, 96808, 0, 96807, 0, 96806, 0, 96805, 0, 96804, 0, 96803, 0, 96802, 0, 96801, 0, 96800, 0, 96799, 0, 96798, 0, 96797, 0, 96796, 0, 96795, 0, 96794, 0, 96793, 0, 96792, 0, 96791, 0, 96790, 0, 96789, 0, 96788, 0, 96787, 0, 96786, 0, 96785, 0, 96784, 0, 96783, 0, 96782, 0, 96781, 0, 96780, 0, 96779, 0, 96778, 0, 96777, 0, 96776, 0, 96775, 0, 96774, 0, 96773, 0, 96772, 0, 96771, 0, 96770, 0, 96769, 0, 96768, 0, 96767, 0, 96766, 0, 96765, 0, 96764, 0, 96763, 0, 96762, 0, 96761, 0, 96760, 0, 96759, 0, 96758, 0, 96757, 0, 96756, 0, 96755, 0, 96754, 0, 96753, 0, 96752, 0, 96751, 0, 96750, 0, 96749, 0, 96748, 0, 96747, 0, 96746, 0, 96745, 0, 96744, 0, 96743, 0, 96742, 0, 96741, 0, 96740, 0, 96739, 0, 96738, 0, 96737, 0, 96736, 0, 96735, 0, 96734, 0, 96733, 0, 96732, 0, 96731, 0, 96730, 0, 96729, 0, 96728, 0, 96727, 0, 96726, 0, 96725, 0, 96724, 0, 96723, 0, 96722, 0, 96721, 0, 96720, 0, 96719, 0, 96718, 0, 96717, 0, 96716, 0, 96715, 0, 96714, 0, 96713, 0, 96712, 0, 96711, 0, 96710, 0, 96709, 0, 96708, 0, 96707, 0, 96706, 0, 96705, 0, 96704, 0, 96703, 0, 96702, 0, 96701, 0, 96700, 0, 96699, 0, 96698, 0, 96697, 0, 96696, 0, 96695, 0, 96694, 0, 96693, 0, 96692, 0, 96691, 0, 96690, 0, 96689, 0, 96688, 0, 96687, 0, 96686, 0, 96685, 0, 96684, 0, 96683, 0, 96682, 0, 96681, 0, 96680, 0, 96679, 0, 96678, 0, 96677, 0, 96676, 0, 96675, 0, 96674, 0, 96673, 0, 96672, 0, 96671, 0, 96670, 0, 96669, 0, 96668, 0, 96667, 0, 96666, 0, 96665, 0, 96664, 0, 96663, 0, 96662, 0, 96661, 0, 96660, 0, 96659, 0, 96658, 0, 96657, 0, 96656, 0, 96655, 0, 96654, 0, 96653, 0, 96652, 0, 96651, 0, 96650, 0, 96649, 0, 96648, 0, 96647, 0, 96646, 0, 96645, 0, 96644, 0, 96643, 0, 96642, 0, 96641, 0, 96640, 0, 96639, 0, 96638, 0, 96637, 0, 96636, 0, 96635, 0, 96634, 0, 96633, 0, 96632, 0, 96631, 0, 96630, 0, 96629, 0, 96628, 0, 96627, 0, 96626, 0, 96625, 0, 96624, 0, 96623, 0, 96622, 0, 96621, 0, 96620, 0, 96619, 0, 96618, 0, 96617, 0, 96616, 0, 96615, 0, 96614, 0, 96613, 0, 96612, 0, 96611, 0, 96610, 0, 96609, 0, 96608, 0, 96607, 0, 96606, 0, 96605, 0, 96604, 0, 96603, 0, 96602, 0, 96601, 0, 96600, 0, 96599, 0, 96598, 0, 96597, 0, 96596, 0, 96595, 0, 96594, 0, 96593, 0, 96592, 0, 96591, 0, 96590, 0, 96589, 0, 96588, 0, 96587, 0, 96586, 0, 96585, 0, 96584, 0, 96583, 0, 96582, 0, 96581, 0, 96580, 0, 96579, 0, 96578, 0, 96577, 0, 96576, 0, 96575, 0, 96574, 0, 96573, 0, 96572, 0, 96571, 0, 96570, 0, 96569, 0, 96568, 0, 96567, 0, 96566, 0, 96565, 0, 96564, 0, 96563, 0, 96562, 0, 96561, 0, 96560, 0, 96559, 0, 96558, 0, 96557, 0, 96556, 0, 96555, 0, 96554, 0, 96553, 0, 96552, 0, 96551, 0, 96550, 0, 96549, 0, 96548, 0, 96547, 0, 96546, 0, 96545, 0, 96544, 0, 96543, 0, 96542, 0, 96541, 0, 96540, 0, 96539, 0, 96538, 0, 96537, 0, 96536, 0, 96535, 0, 96534, 0, 96533, 0, 96532, 0, 96531, 0, 96530, 0, 96529, 0, 96528, 0, 96527, 0, 96526, 0, 96525, 0, 96524, 0, 96523, 0, 96522, 0, 96521, 0, 96520, 0, 96519, 0, 96518, 0, 96517, 0, 96516, 0, 96515, 0, 96514, 0, 96513, 0, 96512, 0, 96511, 0, 96510, 0, 96509, 0, 96508, 0, 96507, 0, 96506, 0, 96505, 0, 96504, 0, 96503, 0, 96502, 0, 96501, 0, 96500, 0, 96499, 0, 96498, 0, 96497, 0, 96496, 0, 96495, 0, 96494, 0, 96493, 0, 96492, 0, 96491, 0, 96490, 0, 96489, 0, 96488, 0, 96487, 0, 96486, 0, 96485, 0, 96484, 0, 96483, 0, 96482, 0, 96481, 0, 96480, 0, 96479, 0, 96478, 0, 96477, 0, 96476, 0, 96475, 0, 96474, 0, 96473, 0, 96472, 0, 96471, 0, 96470, 0, 96469, 0, 96468, 0, 96467, 0, 96466, 0, 96465, 0, 96464, 0, 96463, 0, 96462, 0, 96461, 0, 96460, 0, 96459, 0, 96458, 0, 96457, 0, 96456, 0, 96455, 0, 96454, 0, 96453, 0, 96452, 0, 96451, 0, 96450, 0, 96449, 0, 96448, 0, 96447, 0, 96446, 0, 96445, 0, 96444, 0, 96443, 0, 96442, 0, 96441, 0, 96440, 0, 96439, 0, 96438, 0, 96437, 0, 96436, 0, 96435, 0, 96434, 0, 96433, 0, 96432, 0, 96431, 0, 96430, 0, 96429, 0, 96428, 0, 96427, 0, 96426, 0, 96425, 0, 96424, 0, 96423, 0, 96422, 0, 96421, 0, 96420, 0, 96419, 0, 96418, 0, 96417, 0, 96416, 0, 96415, 0, 96414, 0, 96413, 0, 96412, 0, 96411, 0, 96410, 0, 96409, 0, 96408, 0, 96407, 0, 96406, 0, 96405, 0, 96404, 0, 96403, 0, 96402, 0, 96401, 0, 96400, 0, 96399, 0, 96398, 0, 96397, 0, 96396, 0, 96395, 0, 96394, 0, 96393, 0, 96392, 0, 96391, 0, 96390, 0, 96389, 0, 96388, 0, 96387, 0, 96386, 0, 96385, 0, 96384, 0, 96383, 0, 96382, 0, 96381, 0, 96380, 0, 96379, 0, 96378, 0, 96377, 0, 96376, 0, 96375, 0, 96374, 0, 96373, 0, 96372, 0, 96371, 0, 96370, 0, 96369, 0, 96368, 0, 96367, 0, 96366, 0, 96365, 0, 96364, 0, 96363, 0, 96362, 0, 96361, 0, 96360, 0, 96359, 0, 96358, 0, 96357, 0, 96356, 0, 96355, 0, 96354, 0, 96353, 0, 96352, 0, 96351, 0, 96350, 0, 96349, 0, 96348, 0, 96347, 0, 96346, 0, 96345, 0, 96344, 0, 96343, 0, 96342, 0, 96341, 0, 96340, 0, 96339, 0, 96338, 0, 96337, 0, 96336, 0, 96335, 0, 96334, 0, 96333, 0, 96332, 0, 96331, 0, 96330, 0, 96329, 0, 96328, 0, 96327, 0, 96326, 0, 96325, 0, 96324, 0, 96323, 0, 96322, 0, 96321, 0, 96320, 0, 96319, 0, 96318, 0, 96317, 0, 96316, 0, 96315, 0, 96314, 0, 96313, 0, 96312, 0, 96311, 0, 96310, 0, 96309, 0, 96308, 0, 96307, 0, 96306, 0, 96305, 0, 96304, 0, 96303, 0, 96302, 0, 96301, 0, 96300, 0, 96299, 0, 96298, 0, 96297, 0, 96296, 0, 96295, 0, 96294, 0, 96293, 0, 96292, 0, 96291, 0, 96290, 0, 96289, 0, 96288, 0, 96287, 0, 96286, 0, 96285, 0, 96284, 0, 96283, 0, 96282, 0, 96281, 0, 96280, 0, 96279, 0, 96278, 0, 96277, 0, 96276, 0, 96275, 0, 96274, 0, 96273, 0, 96272, 0, 96271, 0, 96270, 0, 96269, 0, 96268, 0, 96267, 0, 96266, 0, 96265, 0, 96264, 0, 96263, 0, 96262, 0, 96261, 0, 96260, 0, 96259, 0, 96258, 0, 96257, 0, 96256, 0, 96255, 0, 96254, 0, 96253, 0, 96252, 0, 96251, 0, 96250, 0, 96249, 0, 96248, 0, 96247, 0, 96246, 0, 96245, 0, 96244, 0, 96243, 0, 96242, 0, 96241, 0, 96240, 0, 96239, 0, 96238, 0, 96237, 0, 96236, 0, 96235, 0, 96234, 0, 96233, 0, 96232, 0, 96231, 0, 96230, 0, 96229, 0, 96228, 0, 96227, 0, 96226, 0, 96225, 0, 96224, 0, 96223, 0, 96222, 0, 96221, 0, 96220, 0, 96219, 0, 96218, 0, 96217, 0, 96216, 0, 96215, 0, 96214, 0, 96213, 0, 96212, 0, 96211, 0, 96210, 0, 96209, 0, 96208, 0, 96207, 0, 96206, 0, 96205, 0, 96204, 0, 96203, 0, 96202, 0, 96201, 0, 96200, 0, 96199, 0, 96198, 0, 96197, 0, 96196, 0, 96195, 0, 96194, 0, 96193, 0, 96192, 0, 96191, 0, 96190, 0, 96189, 0, 96188, 0, 96187, 0, 96186, 0, 96185, 0, 96184, 0, 96183, 0, 96182, 0, 96181, 0, 96180, 0, 96179, 0, 96178, 0, 96177, 0, 96176, 0, 96175, 0, 96174, 0, 96173, 0, 96172, 0, 96171, 0, 96170, 0, 96169, 0, 96168, 0, 96167, 0, 96166, 0, 96165, 0, 96164, 0, 96163, 0, 96162, 0, 96161, 0, 96160, 0, 96159, 0, 96158, 0, 96157, 0, 96156, 0, 96155, 0, 96154, 0, 96153, 0, 96152, 0, 96151, 0, 96150, 0, 96149, 0, 96148, 0, 96147, 0, 96146, 0, 96145, 0, 96144, 0, 96143, 0, 96142, 0, 96141, 0, 96140, 0, 96139, 0, 96138, 0, 96137, 0, 96136, 0, 96135, 0, 96134, 0, 96133, 0, 96132, 0, 96131, 0, 96130, 0, 96129, 0, 96128, 0, 96127, 0, 96126, 0, 96125, 0, 96124, 0, 96123, 0, 96122, 0, 96121, 0, 96120, 0, 96119, 0, 96118, 0, 96117, 0, 96116, 0, 96115, 0, 96114, 0, 96113, 0, 96112, 0, 96111, 0, 96110, 0, 96109, 0, 96108, 0, 96107, 0, 96106, 0, 96105, 0, 96104, 0, 96103, 0, 96102, 0, 96101, 0, 96100, 0, 96099, 0, 96098, 0, 96097, 0, 96096, 0, 96095, 0, 96094, 0, 96093, 0, 96092, 0, 96091, 0, 96090, 0, 96089, 0, 96088, 0, 96087, 0, 96086, 0, 96085, 0, 96084, 0, 96083, 0, 96082, 0, 96081, 0, 96080, 0, 96079, 0, 96078, 0, 96077, 0, 96076, 0, 96075, 0, 96074, 0, 96073, 0, 96072, 0, 96071, 0, 96070, 0, 96069, 0, 96068, 0, 96067, 0, 96066, 0, 96065, 0, 96064, 0, 96063, 0, 96062, 0, 96061, 0, 96060, 0, 96059, 0, 96058, 0, 96057, 0, 96056, 0, 96055, 0, 96054, 0, 96053, 0, 96052, 0, 96051, 0, 96050, 0, 96049, 0, 96048, 0, 96047, 0, 96046, 0, 96045, 0, 96044, 0, 96043, 0, 96042, 0, 96041, 0, 96040, 0, 96039, 0, 96038, 0, 96037, 0, 96036, 0, 96035, 0, 96034, 0, 96033, 0, 96032, 0, 96031, 0, 96030, 0, 96029, 0, 96028, 0, 96027, 0, 96026, 0, 96025, 0, 96024, 0, 96023, 0, 96022, 0, 96021, 0, 96020, 0, 96019, 0, 96018, 0, 96017, 0, 96016, 0, 96015, 0, 96014, 0, 96013, 0, 96012, 0, 96011, 0, 96010, 0, 96009, 0, 96008, 0, 96007, 0, 96006, 0, 96005, 0, 96004, 0, 96003, 0, 96002, 0, 96001, 0, 96000, 0, 95999, 0, 95998, 0, 95997, 0, 95996, 0, 95995, 0, 95994, 0, 95993, 0, 95992, 0, 95991, 0, 95990, 0, 95989, 0, 95988, 0, 95987, 0, 95986, 0, 95985, 0, 95984, 0, 95983, 0, 95982, 0, 95981, 0, 95980, 0, 95979, 0, 95978, 0, 95977, 0, 95976, 0, 95975, 0, 95974, 0, 95973, 0, 95972, 0, 95971, 0, 95970, 0, 95969, 0, 95968, 0, 95967, 0, 95966, 0, 95965, 0, 95964, 0, 95963, 0, 95962, 0, 95961, 0, 95960, 0, 95959, 0, 95958, 0, 95957, 0, 95956, 0, 95955, 0, 95954, 0, 95953, 0, 95952, 0, 95951, 0, 95950, 0, 95949, 0, 95948, 0, 95947, 0, 95946, 0, 95945, 0, 95944, 0, 95943, 0, 95942, 0, 95941, 0, 95940, 0, 95939, 0, 95938, 0, 95937, 0, 95936, 0, 95935, 0, 95934, 0, 95933, 0, 95932, 0, 95931, 0, 95930, 0, 95929, 0, 95928, 0, 95927, 0, 95926, 0, 95925, 0, 95924, 0, 95923, 0, 95922, 0, 95921, 0, 95920, 0, 95919, 0, 95918, 0, 95917, 0, 95916, 0, 95915, 0, 95914, 0, 95913, 0, 95912, 0, 95911, 0, 95910, 0, 95909, 0, 95908, 0, 95907, 0, 95906, 0, 95905, 0, 95904, 0, 95903, 0, 95902, 0, 95901, 0, 95900, 0, 95899, 0, 95898, 0, 95897, 0, 95896, 0, 95895, 0, 95894, 0, 95893, 0, 95892, 0, 95891, 0, 95890, 0, 95889, 0, 95888, 0, 95887, 0, 95886, 0, 95885, 0, 95884, 0, 95883, 0, 95882, 0, 95881, 0, 95880, 0, 95879, 0, 95878, 0, 95877, 0, 95876, 0, 95875, 0, 95874, 0, 95873, 0, 95872, 0, 95871, 0, 95870, 0, 95869, 0, 95868, 0, 95867, 0, 95866, 0, 95865, 0, 95864, 0, 95863, 0, 95862, 0, 95861, 0, 95860, 0, 95859, 0, 95858, 0, 95857, 0, 95856, 0, 95855, 0, 95854, 0, 95853, 0, 95852, 0, 95851, 0, 95850, 0, 95849, 0, 95848, 0, 95847, 0, 95846, 0, 95845, 0, 95844, 0, 95843, 0, 95842, 0, 95841, 0, 95840, 0, 95839, 0, 95838, 0, 95837, 0, 95836, 0, 95835, 0, 95834, 0, 95833, 0, 95832, 0, 95831, 0, 95830, 0, 95829, 0, 95828, 0, 95827, 0, 95826, 0, 95825, 0, 95824, 0, 95823, 0, 95822, 0, 95821, 0, 95820, 0, 95819, 0, 95818, 0, 95817, 0, 95816, 0, 95815, 0, 95814, 0, 95813, 0, 95812, 0, 95811, 0, 95810, 0, 95809, 0, 95808, 0, 95807, 0, 95806, 0, 95805, 0, 95804, 0, 95803, 0, 95802, 0, 95801, 0, 95800, 0, 95799, 0, 95798, 0, 95797, 0, 95796, 0, 95795, 0, 95794, 0, 95793, 0, 95792, 0, 95791, 0, 95790, 0, 95789, 0, 95788, 0, 95787, 0, 95786, 0, 95785, 0, 95784, 0, 95783, 0, 95782, 0, 95781, 0, 95780, 0, 95779, 0, 95778, 0, 95777, 0, 95776, 0, 95775, 0, 95774, 0, 95773, 0, 95772, 0, 95771, 0, 95770, 0, 95769, 0, 95768, 0, 95767, 0, 95766, 0, 95765, 0, 95764, 0, 95763, 0, 95762, 0, 95761, 0, 95760, 0, 95759, 0, 95758, 0, 95757, 0, 95756, 0, 95755, 0, 95754, 0, 95753, 0, 95752, 0, 95751, 0, 95750, 0, 95749, 0, 95748, 0, 95747, 0, 95746, 0, 95745, 0, 95744, 0, 95743, 0, 95742, 0, 95741, 0, 95740, 0, 95739, 0, 95738, 0, 95737, 0, 95736, 0, 95735, 0, 95734, 0, 95733, 0, 95732, 0, 95731, 0, 95730, 0, 95729, 0, 95728, 0, 95727, 0, 95726, 0, 95725, 0, 95724, 0, 95723, 0, 95722, 0, 95721, 0, 95720, 0, 95719, 0, 95718, 0, 95717, 0, 95716, 0, 95715, 0, 95714, 0, 95713, 0, 95712, 0, 95711, 0, 95710, 0, 95709, 0, 95708, 0, 95707, 0, 95706, 0, 95705, 0, 95704, 0, 95703, 0, 95702, 0, 95701, 0, 95700, 0, 95699, 0, 95698, 0, 95697, 0, 95696, 0, 95695, 0, 95694, 0, 95693, 0, 95692, 0, 95691, 0, 95690, 0, 95689, 0, 95688, 0, 95687, 0, 95686, 0, 95685, 0, 95684, 0, 95683, 0, 95682, 0, 95681, 0, 95680, 0, 95679, 0, 95678, 0, 95677, 0, 95676, 0, 95675, 0, 95674, 0, 95673, 0, 95672, 0, 95671, 0, 95670, 0, 95669, 0, 95668, 0, 95667, 0, 95666, 0, 95665, 0, 95664, 0, 95663, 0, 95662, 0, 95661, 0, 95660, 0, 95659, 0, 95658, 0, 95657, 0, 95656, 0, 95655, 0, 95654, 0, 95653, 0, 95652, 0, 95651, 0, 95650, 0, 95649, 0, 95648, 0, 95647, 0, 95646, 0, 95645, 0, 95644, 0, 95643, 0, 95642, 0, 95641, 0, 95640, 0, 95639, 0, 95638, 0, 95637, 0, 95636, 0, 95635, 0, 95634, 0, 95633, 0, 95632, 0, 95631, 0, 95630, 0, 95629, 0, 95628, 0, 95627, 0, 95626, 0, 95625, 0, 95624, 0, 95623, 0, 95622, 0, 95621, 0, 95620, 0, 95619, 0, 95618, 0, 95617, 0, 95616, 0, 95615, 0, 95614, 0, 95613, 0, 95612, 0, 95611, 0, 95610, 0, 95609, 0, 95608, 0, 95607, 0, 95606, 0, 95605, 0, 95604, 0, 95603, 0, 95602, 0, 95601, 0, 95600, 0, 95599, 0, 95598, 0, 95597, 0, 95596, 0, 95595, 0, 95594, 0, 95593, 0, 95592, 0, 95591, 0, 95590, 0, 95589, 0, 95588, 0, 95587, 0, 95586, 0, 95585, 0, 95584, 0, 95583, 0, 95582, 0, 95581, 0, 95580, 0, 95579, 0, 95578, 0, 95577, 0, 95576, 0, 95575, 0, 95574, 0, 95573, 0, 95572, 0, 95571, 0, 95570, 0, 95569, 0, 95568, 0, 95567, 0, 95566, 0, 95565, 0, 95564, 0, 95563, 0, 95562, 0, 95561, 0, 95560, 0, 95559, 0, 95558, 0, 95557, 0, 95556, 0, 95555, 0, 95554, 0, 95553, 0, 95552, 0, 95551, 0, 95550, 0, 95549, 0, 95548, 0, 95547, 0, 95546, 0, 95545, 0, 95544, 0, 95543, 0, 95542, 0, 95541, 0, 95540, 0, 95539, 0, 95538, 0, 95537, 0, 95536, 0, 95535, 0, 95534, 0, 95533, 0, 95532, 0, 95531, 0, 95530, 0, 95529, 0, 95528, 0, 95527, 0, 95526, 0, 95525, 0, 95524, 0, 95523, 0, 95522, 0, 95521, 0, 95520, 0, 95519, 0, 95518, 0, 95517, 0, 95516, 0, 95515, 0, 95514, 0, 95513, 0, 95512, 0, 95511, 0, 95510, 0, 95509, 0, 95508, 0, 95507, 0, 95506, 0, 95505, 0, 95504, 0, 95503, 0, 95502, 0, 95501, 0, 95500, 0, 95499, 0, 95498, 0, 95497, 0, 95496, 0, 95495, 0, 95494, 0, 95493, 0, 95492, 0, 95491, 0, 95490, 0, 95489, 0, 95488, 0, 95487, 0, 95486, 0, 95485, 0, 95484, 0, 95483, 0, 95482, 0, 95481, 0, 95480, 0, 95479, 0, 95478, 0, 95477, 0, 95476, 0, 95475, 0, 95474, 0, 95473, 0, 95472, 0, 95471, 0, 95470, 0, 95469, 0, 95468, 0, 95467, 0, 95466, 0, 95465, 0, 95464, 0, 95463, 0, 95462, 0, 95461, 0, 95460, 0, 95459, 0, 95458, 0, 95457, 0, 95456, 0, 95455, 0, 95454, 0, 95453, 0, 95452, 0, 95451, 0, 95450, 0, 95449, 0, 95448, 0, 95447, 0, 95446, 0, 95445, 0, 95444, 0, 95443, 0, 95442, 0, 95441, 0, 95440, 0, 95439, 0, 95438, 0, 95437, 0, 95436, 0, 95435, 0, 95434, 0, 95433, 0, 95432, 0, 95431, 0, 95430, 0, 95429, 0, 95428, 0, 95427, 0, 95426, 0, 95425, 0, 95424, 0, 95423, 0, 95422, 0, 95421, 0, 95420, 0, 95419, 0, 95418, 0, 95417, 0, 95416, 0, 95415, 0, 95414, 0, 95413, 0, 95412, 0, 95411, 0, 95410, 0, 95409, 0, 95408, 0, 95407, 0, 95406, 0, 95405, 0, 95404, 0, 95403, 0, 95402, 0, 95401, 0, 95400, 0, 95399, 0, 95398, 0, 95397, 0, 95396, 0, 95395, 0, 95394, 0, 95393, 0, 95392, 0, 95391, 0, 95390, 0, 95389, 0, 95388, 0, 95387, 0, 95386, 0, 95385, 0, 95384, 0, 95383, 0, 95382, 0, 95381, 0, 95380, 0, 95379, 0, 95378, 0, 95377, 0, 95376, 0, 95375, 0, 95374, 0, 95373, 0, 95372, 0, 95371, 0, 95370, 0, 95369, 0, 95368, 0, 95367, 0, 95366, 0, 95365, 0, 95364, 0, 95363, 0, 95362, 0, 95361, 0, 95360, 0, 95359, 0, 95358, 0, 95357, 0, 95356, 0, 95355, 0, 95354, 0, 95353, 0, 95352, 0, 95351, 0, 95350, 0, 95349, 0, 95348, 0, 95347, 0, 95346, 0, 95345, 0, 95344, 0, 95343, 0, 95342, 0, 95341, 0, 95340, 0, 95339, 0, 95338, 0, 95337, 0, 95336, 0, 95335, 0, 95334, 0, 95333, 0, 95332, 0, 95331, 0, 95330, 0, 95329, 0, 95328, 0, 95327, 0, 95326, 0, 95325, 0, 95324, 0, 95323, 0, 95322, 0, 95321, 0, 95320, 0, 95319, 0, 95318, 0, 95317, 0, 95316, 0, 95315, 0, 95314, 0, 95313, 0, 95312, 0, 95311, 0, 95310, 0, 95309, 0, 95308, 0, 95307, 0, 95306, 0, 95305, 0, 95304, 0, 95303, 0, 95302, 0, 95301, 0, 95300, 0, 95299, 0, 95298, 0, 95297, 0, 95296, 0, 95295, 0, 95294, 0, 95293, 0, 95292, 0, 95291, 0, 95290, 0, 95289, 0, 95288, 0, 95287, 0, 95286, 0, 95285, 0, 95284, 0, 95283, 0, 95282, 0, 95281, 0, 95280, 0, 95279, 0, 95278, 0, 95277, 0, 95276, 0, 95275, 0, 95274, 0, 95273, 0, 95272, 0, 95271, 0, 95270, 0, 95269, 0, 95268, 0, 95267, 0, 95266, 0, 95265, 0, 95264, 0, 95263, 0, 95262, 0, 95261, 0, 95260, 0, 95259, 0, 95258, 0, 95257, 0, 95256, 0, 95255, 0, 95254, 0, 95253, 0, 95252, 0, 95251, 0, 95250, 0, 95249, 0, 95248, 0, 95247, 0, 95246, 0, 95245, 0, 95244, 0, 95243, 0, 95242, 0, 95241, 0, 95240, 0, 95239, 0, 95238, 0, 95237, 0, 95236, 0, 95235, 0, 95234, 0, 95233, 0, 95232, 0, 95231, 0, 95230, 0, 95229, 0, 95228, 0, 95227, 0, 95226, 0, 95225, 0, 95224, 0, 95223, 0, 95222, 0, 95221, 0, 95220, 0, 95219, 0, 95218, 0, 95217, 0, 95216, 0, 95215, 0, 95214, 0, 95213, 0, 95212, 0, 95211, 0, 95210, 0, 95209, 0, 95208, 0, 95207, 0, 95206, 0, 95205, 0, 95204, 0, 95203, 0, 95202, 0, 95201, 0, 95200, 0, 95199, 0, 95198, 0, 95197, 0, 95196, 0, 95195, 0, 95194, 0, 95193, 0, 95192, 0, 95191, 0, 95190, 0, 95189, 0, 95188, 0, 95187, 0, 95186, 0, 95185, 0, 95184, 0, 95183, 0, 95182, 0, 95181, 0, 95180, 0, 95179, 0, 95178, 0, 95177, 0, 95176, 0, 95175, 0, 95174, 0, 95173, 0, 95172, 0, 95171, 0, 95170, 0, 95169, 0, 95168, 0, 95167, 0, 95166, 0, 95165, 0, 95164, 0, 95163, 0, 95162, 0, 95161, 0, 95160, 0, 95159, 0, 95158, 0, 95157, 0, 95156, 0, 95155, 0, 95154, 0, 95153, 0, 95152, 0, 95151, 0, 95150, 0, 95149, 0, 95148, 0, 95147, 0, 95146, 0, 95145, 0, 95144, 0, 95143, 0, 95142, 0, 95141, 0, 95140, 0, 95139, 0, 95138, 0, 95137, 0, 95136, 0, 95135, 0, 95134, 0, 95133, 0, 95132, 0, 95131, 0, 95130, 0, 95129, 0, 95128, 0, 95127, 0, 95126, 0, 95125, 0, 95124, 0, 95123, 0, 95122, 0, 95121, 0, 95120, 0, 95119, 0, 95118, 0, 95117, 0, 95116, 0, 95115, 0, 95114, 0, 95113, 0, 95112, 0, 95111, 0, 95110, 0, 95109, 0, 95108, 0, 95107, 0, 95106, 0, 95105, 0, 95104, 0, 95103, 0, 95102, 0, 95101, 0, 95100, 0, 95099, 0, 95098, 0, 95097, 0, 95096, 0, 95095, 0, 95094, 0, 95093, 0, 95092, 0, 95091, 0, 95090, 0, 95089, 0, 95088, 0, 95087, 0, 95086, 0, 95085, 0, 95084, 0, 95083, 0, 95082, 0, 95081, 0, 95080, 0, 95079, 0, 95078, 0, 95077, 0, 95076, 0, 95075, 0, 95074, 0, 95073, 0, 95072, 0, 95071, 0, 95070, 0, 95069, 0, 95068, 0, 95067, 0, 95066, 0, 95065, 0, 95064, 0, 95063, 0, 95062, 0, 95061, 0, 95060, 0, 95059, 0, 95058, 0, 95057, 0, 95056, 0, 95055, 0, 95054, 0, 95053, 0, 95052, 0, 95051, 0, 95050, 0, 95049, 0, 95048, 0, 95047, 0, 95046, 0, 95045, 0, 95044, 0, 95043, 0, 95042, 0, 95041, 0, 95040, 0, 95039, 0, 95038, 0, 95037, 0, 95036, 0, 95035, 0, 95034, 0, 95033, 0, 95032, 0, 95031, 0, 95030, 0, 95029, 0, 95028, 0, 95027, 0, 95026, 0, 95025, 0, 95024, 0, 95023, 0, 95022, 0, 95021, 0, 95020, 0, 95019, 0, 95018, 0, 95017, 0, 95016, 0, 95015, 0, 95014, 0, 95013, 0, 95012, 0, 95011, 0, 95010, 0, 95009, 0, 95008, 0, 95007, 0, 95006, 0, 95005, 0, 95004, 0, 95003, 0, 95002, 0, 95001, 0, 95000, 0, 94999, 0, 94998, 0, 94997, 0, 94996, 0, 94995, 0, 94994, 0, 94993, 0, 94992, 0, 94991, 0, 94990, 0, 94989, 0, 94988, 0, 94987, 0, 94986, 0, 94985, 0, 94984, 0, 94983, 0, 94982, 0, 94981, 0, 94980, 0, 94979, 0, 94978, 0, 94977, 0, 94976, 0, 94975, 0, 94974, 0, 94973, 0, 94972, 0, 94971, 0, 94970, 0, 94969, 0, 94968, 0, 94967, 0, 94966, 0, 94965, 0, 94964, 0, 94963, 0, 94962, 0, 94961, 0, 94960, 0, 94959, 0, 94958, 0, 94957, 0, 94956, 0, 94955, 0, 94954, 0, 94953, 0, 94952, 0, 94951, 0, 94950, 0, 94949, 0, 94948, 0, 94947, 0, 94946, 0, 94945, 0, 94944, 0, 94943, 0, 94942, 0, 94941, 0, 94940, 0, 94939, 0, 94938, 0, 94937, 0, 94936, 0, 94935, 0, 94934, 0, 94933, 0, 94932, 0, 94931, 0, 94930, 0, 94929, 0, 94928, 0, 94927, 0, 94926, 0, 94925, 0, 94924, 0, 94923, 0, 94922, 0, 94921, 0, 94920, 0, 94919, 0, 94918, 0, 94917, 0, 94916, 0, 94915, 0, 94914, 0, 94913, 0, 94912, 0, 94911, 0, 94910, 0, 94909, 0, 94908, 0, 94907, 0, 94906, 0, 94905, 0, 94904, 0, 94903, 0, 94902, 0, 94901, 0, 94900, 0, 94899, 0, 94898, 0, 94897, 0, 94896, 0, 94895, 0, 94894, 0, 94893, 0, 94892, 0, 94891, 0, 94890, 0, 94889, 0, 94888, 0, 94887, 0, 94886, 0, 94885, 0, 94884, 0, 94883, 0, 94882, 0, 94881, 0, 94880, 0, 94879, 0, 94878, 0, 94877, 0, 94876, 0, 94875, 0, 94874, 0, 94873, 0, 94872, 0, 94871, 0, 94870, 0, 94869, 0, 94868, 0, 94867, 0, 94866, 0, 94865, 0, 94864, 0, 94863, 0, 94862, 0, 94861, 0, 94860, 0, 94859, 0, 94858, 0, 94857, 0, 94856, 0, 94855, 0, 94854, 0, 94853, 0, 94852, 0, 94851, 0, 94850, 0, 94849, 0, 94848, 0, 94847, 0, 94846, 0, 94845, 0, 94844, 0, 94843, 0, 94842, 0, 94841, 0, 94840, 0, 94839, 0, 94838, 0, 94837, 0, 94836, 0, 94835, 0, 94834, 0, 94833, 0, 94832, 0, 94831, 0, 94830, 0, 94829, 0, 94828, 0, 94827, 0, 94826, 0, 94825, 0, 94824, 0, 94823, 0, 94822, 0, 94821, 0, 94820, 0, 94819, 0, 94818, 0, 94817, 0, 94816, 0, 94815, 0, 94814, 0, 94813, 0, 94812, 0, 94811, 0, 94810, 0, 94809, 0, 94808, 0, 94807, 0, 94806, 0, 94805, 0, 94804, 0, 94803, 0, 94802, 0, 94801, 0, 94800, 0, 94799, 0, 94798, 0, 94797, 0, 94796, 0, 94795, 0, 94794, 0, 94793, 0, 94792, 0, 94791, 0, 94790, 0, 94789, 0, 94788, 0, 94787, 0, 94786, 0, 94785, 0, 94784, 0, 94783, 0, 94782, 0, 94781, 0, 94780, 0, 94779, 0, 94778, 0, 94777, 0, 94776, 0, 94775, 0, 94774, 0, 94773, 0, 94772, 0, 94771, 0, 94770, 0, 94769, 0, 94768, 0, 94767, 0, 94766, 0, 94765, 0, 94764, 0, 94763, 0, 94762, 0, 94761, 0, 94760, 0, 94759, 0, 94758, 0, 94757, 0, 94756, 0, 94755, 0, 94754, 0, 94753, 0, 94752, 0, 94751, 0, 94750, 0, 94749, 0, 94748, 0, 94747, 0, 94746, 0, 94745, 0, 94744, 0, 94743, 0, 94742, 0, 94741, 0, 94740, 0, 94739, 0, 94738, 0, 94737, 0, 94736, 0, 94735, 0, 94734, 0, 94733, 0, 94732, 0, 94731, 0, 94730, 0, 94729, 0, 94728, 0, 94727, 0, 94726, 0, 94725, 0, 94724, 0, 94723, 0, 94722, 0, 94721, 0, 94720, 0, 94719, 0, 94718, 0, 94717, 0, 94716, 0, 94715, 0, 94714, 0, 94713, 0, 94712, 0, 94711, 0, 94710, 0, 94709, 0, 94708, 0, 94707, 0, 94706, 0, 94705, 0, 94704, 0, 94703, 0, 94702, 0, 94701, 0, 94700, 0, 94699, 0, 94698, 0, 94697, 0, 94696, 0, 94695, 0, 94694, 0, 94693, 0, 94692, 0, 94691, 0, 94690, 0, 94689, 0, 94688, 0, 94687, 0, 94686, 0, 94685, 0, 94684, 0, 94683, 0, 94682, 0, 94681, 0, 94680, 0, 94679, 0, 94678, 0, 94677, 0, 94676, 0, 94675, 0, 94674, 0, 94673, 0, 94672, 0, 94671, 0, 94670, 0, 94669, 0, 94668, 0, 94667, 0, 94666, 0, 94665, 0, 94664, 0, 94663, 0, 94662, 0, 94661, 0, 94660, 0, 94659, 0, 94658, 0, 94657, 0, 94656, 0, 94655, 0, 94654, 0, 94653, 0, 94652, 0, 94651, 0, 94650, 0, 94649, 0, 94648, 0, 94647, 0, 94646, 0, 94645, 0, 94644, 0, 94643, 0, 94642, 0, 94641, 0, 94640, 0, 94639, 0, 94638, 0, 94637, 0, 94636, 0, 94635, 0, 94634, 0, 94633, 0, 94632, 0, 94631, 0, 94630, 0, 94629, 0, 94628, 0, 94627, 0, 94626, 0, 94625, 0, 94624, 0, 94623, 0, 94622, 0, 94621, 0, 94620, 0, 94619, 0, 94618, 0, 94617, 0, 94616, 0, 94615, 0, 94614, 0, 94613, 0, 94612, 0, 94611, 0, 94610, 0, 94609, 0, 94608, 0, 94607, 0, 94606, 0, 94605, 0, 94604, 0, 94603, 0, 94602, 0, 94601, 0, 94600, 0, 94599, 0, 94598, 0, 94597, 0, 94596, 0, 94595, 0, 94594, 0, 94593, 0, 94592, 0, 94591, 0, 94590, 0, 94589, 0, 94588, 0, 94587, 0, 94586, 0, 94585, 0, 94584, 0, 94583, 0, 94582, 0, 94581, 0, 94580, 0, 94579, 0, 94578, 0, 94577, 0, 94576, 0, 94575, 0, 94574, 0, 94573, 0, 94572, 0, 94571, 0, 94570, 0, 94569, 0, 94568, 0, 94567, 0, 94566, 0, 94565, 0, 94564, 0, 94563, 0, 94562, 0, 94561, 0, 94560, 0, 94559, 0, 94558, 0, 94557, 0, 94556, 0, 94555, 0, 94554, 0, 94553, 0, 94552, 0, 94551, 0, 94550, 0, 94549, 0, 94548, 0, 94547, 0, 94546, 0, 94545, 0, 94544, 0, 94543, 0, 94542, 0, 94541, 0, 94540, 0, 94539, 0, 94538, 0, 94537, 0, 94536, 0, 94535, 0, 94534, 0, 94533, 0, 94532, 0, 94531, 0, 94530, 0, 94529, 0, 94528, 0, 94527, 0, 94526, 0, 94525, 0, 94524, 0, 94523, 0, 94522, 0, 94521, 0, 94520, 0, 94519, 0, 94518, 0, 94517, 0, 94516, 0, 94515, 0, 94514, 0, 94513, 0, 94512, 0, 94511, 0, 94510, 0, 94509, 0, 94508, 0, 94507, 0, 94506, 0, 94505, 0, 94504, 0, 94503, 0, 94502, 0, 94501, 0, 94500, 0, 94499, 0, 94498, 0, 94497, 0, 94496, 0, 94495, 0, 94494, 0, 94493, 0, 94492, 0, 94491, 0, 94490, 0, 94489, 0, 94488, 0, 94487, 0, 94486, 0, 94485, 0, 94484, 0, 94483, 0, 94482, 0, 94481, 0, 94480, 0, 94479, 0, 94478, 0, 94477, 0, 94476, 0, 94475, 0, 94474, 0, 94473, 0, 94472, 0, 94471, 0, 94470, 0, 94469, 0, 94468, 0, 94467, 0, 94466, 0, 94465, 0, 94464, 0, 94463, 0, 94462, 0, 94461, 0, 94460, 0, 94459, 0, 94458, 0, 94457, 0, 94456, 0, 94455, 0, 94454, 0, 94453, 0, 94452, 0, 94451, 0, 94450, 0, 94449, 0, 94448, 0, 94447, 0, 94446, 0, 94445, 0, 94444, 0, 94443, 0, 94442, 0, 94441, 0, 94440, 0, 94439, 0, 94438, 0, 94437, 0, 94436, 0, 94435, 0, 94434, 0, 94433, 0, 94432, 0, 94431, 0, 94430, 0, 94429, 0, 94428, 0, 94427, 0, 94426, 0, 94425, 0, 94424, 0, 94423, 0, 94422, 0, 94421, 0, 94420, 0, 94419, 0, 94418, 0, 94417, 0, 94416, 0, 94415, 0, 94414, 0, 94413, 0, 94412, 0, 94411, 0, 94410, 0, 94409, 0, 94408, 0, 94407, 0, 94406, 0, 94405, 0, 94404, 0, 94403, 0, 94402, 0, 94401, 0, 94400, 0, 94399, 0, 94398, 0, 94397, 0, 94396, 0, 94395, 0, 94394, 0, 94393, 0, 94392, 0, 94391, 0, 94390, 0, 94389, 0, 94388, 0, 94387, 0, 94386, 0, 94385, 0, 94384, 0, 94383, 0, 94382, 0, 94381, 0, 94380, 0, 94379, 0, 94378, 0, 94377, 0, 94376, 0, 94375, 0, 94374, 0, 94373, 0, 94372, 0, 94371, 0, 94370, 0, 94369, 0, 94368, 0, 94367, 0, 94366, 0, 94365, 0, 94364, 0, 94363, 0, 94362, 0, 94361, 0, 94360, 0, 94359, 0, 94358, 0, 94357, 0, 94356, 0, 94355, 0, 94354, 0, 94353, 0, 94352, 0, 94351, 0, 94350, 0, 94349, 0, 94348, 0, 94347, 0, 94346, 0, 94345, 0, 94344, 0, 94343, 0, 94342, 0, 94341, 0, 94340, 0, 94339, 0, 94338, 0, 94337, 0, 94336, 0, 94335, 0, 94334, 0, 94333, 0, 94332, 0, 94331, 0, 94330, 0, 94329, 0, 94328, 0, 94327, 0, 94326, 0, 94325, 0, 94324, 0, 94323, 0, 94322, 0, 94321, 0, 94320, 0, 94319, 0, 94318, 0, 94317, 0, 94316, 0, 94315, 0, 94314, 0, 94313, 0, 94312, 0, 94311, 0, 94310, 0, 94309, 0, 94308, 0, 94307, 0, 94306, 0, 94305, 0, 94304, 0, 94303, 0, 94302, 0, 94301, 0, 94300, 0, 94299, 0, 94298, 0, 94297, 0, 94296, 0, 94295, 0, 94294, 0, 94293, 0, 94292, 0, 94291, 0, 94290, 0, 94289, 0, 94288, 0, 94287, 0, 94286, 0, 94285, 0, 94284, 0, 94283, 0, 94282, 0, 94281, 0, 94280, 0, 94279, 0, 94278, 0, 94277, 0, 94276, 0, 94275, 0, 94274, 0, 94273, 0, 94272, 0, 94271, 0, 94270, 0, 94269, 0, 94268, 0, 94267, 0, 94266, 0, 94265, 0, 94264, 0, 94263, 0, 94262, 0, 94261, 0, 94260, 0, 94259, 0, 94258, 0, 94257, 0, 94256, 0, 94255, 0, 94254, 0, 94253, 0, 94252, 0, 94251, 0, 94250, 0, 94249, 0, 94248, 0, 94247, 0, 94246, 0, 94245, 0, 94244, 0, 94243, 0, 94242, 0, 94241, 0, 94240, 0, 94239, 0, 94238, 0, 94237, 0, 94236, 0, 94235, 0, 94234, 0, 94233, 0, 94232, 0, 94231, 0, 94230, 0, 94229, 0, 94228, 0, 94227, 0, 94226, 0, 94225, 0, 94224, 0, 94223, 0, 94222, 0, 94221, 0, 94220, 0, 94219, 0, 94218, 0, 94217, 0, 94216, 0, 94215, 0, 94214, 0, 94213, 0, 94212, 0, 94211, 0, 94210, 0, 94209, 0, 94208, 0, 94207, 0, 94206, 0, 94205, 0, 94204, 0, 94203, 0, 94202, 0, 94201, 0, 94200, 0, 94199, 0, 94198, 0, 94197, 0, 94196, 0, 94195, 0, 94194, 0, 94193, 0, 94192, 0, 94191, 0, 94190, 0, 94189, 0, 94188, 0, 94187, 0, 94186, 0, 94185, 0, 94184, 0, 94183, 0, 94182, 0, 94181, 0, 94180, 0, 94179, 0, 94178, 0, 94177, 0, 94176, 0, 94175, 0, 94174, 0, 94173, 0, 94172, 0, 94171, 0, 94170, 0, 94169, 0, 94168, 0, 94167, 0, 94166, 0, 94165, 0, 94164, 0, 94163, 0, 94162, 0, 94161, 0, 94160, 0, 94159, 0, 94158, 0, 94157, 0, 94156, 0, 94155, 0, 94154, 0, 94153, 0, 94152, 0, 94151, 0, 94150, 0, 94149, 0, 94148, 0, 94147, 0, 94146, 0, 94145, 0, 94144, 0, 94143, 0, 94142, 0, 94141, 0, 94140, 0, 94139, 0, 94138, 0, 94137, 0, 94136, 0, 94135, 0, 94134, 0, 94133, 0, 94132, 0, 94131, 0, 94130, 0, 94129, 0, 94128, 0, 94127, 0, 94126, 0, 94125, 0, 94124, 0, 94123, 0, 94122, 0, 94121, 0, 94120, 0, 94119, 0, 94118, 0, 94117, 0, 94116, 0, 94115, 0, 94114, 0, 94113, 0, 94112, 0, 94111, 0, 94110, 0, 94109, 0, 94108, 0, 94107, 0, 94106, 0, 94105, 0, 94104, 0, 94103, 0, 94102, 0, 94101, 0, 94100, 0, 94099, 0, 94098, 0, 94097, 0, 94096, 0, 94095, 0, 94094, 0, 94093, 0, 94092, 0, 94091, 0, 94090, 0, 94089, 0, 94088, 0, 94087, 0, 94086, 0, 94085, 0, 94084, 0, 94083, 0, 94082, 0, 94081, 0, 94080, 0, 94079, 0, 94078, 0, 94077, 0, 94076, 0, 94075, 0, 94074, 0, 94073, 0, 94072, 0, 94071, 0, 94070, 0, 94069, 0, 94068, 0, 94067, 0, 94066, 0, 94065, 0, 94064, 0, 94063, 0, 94062, 0, 94061, 0, 94060, 0, 94059, 0, 94058, 0, 94057, 0, 94056, 0, 94055, 0, 94054, 0, 94053, 0, 94052, 0, 94051, 0, 94050, 0, 94049, 0, 94048, 0, 94047, 0, 94046, 0, 94045, 0, 94044, 0, 94043, 0, 94042, 0, 94041, 0, 94040, 0, 94039, 0, 94038, 0, 94037, 0, 94036, 0, 94035, 0, 94034, 0, 94033, 0, 94032, 0, 94031, 0, 94030, 0, 94029, 0, 94028, 0, 94027, 0, 94026, 0, 94025, 0, 94024, 0, 94023, 0, 94022, 0, 94021, 0, 94020, 0, 94019, 0, 94018, 0, 94017, 0, 94016, 0, 94015, 0, 94014, 0, 94013, 0, 94012, 0, 94011, 0, 94010, 0, 94009, 0, 94008, 0, 94007, 0, 94006, 0, 94005, 0, 94004, 0, 94003, 0, 94002, 0, 94001, 0, 94000, 0, 93999, 0, 93998, 0, 93997, 0, 93996, 0, 93995, 0, 93994, 0, 93993, 0, 93992, 0, 93991, 0, 93990, 0, 93989, 0, 93988, 0, 93987, 0, 93986, 0, 93985, 0, 93984, 0, 93983, 0, 93982, 0, 93981, 0, 93980, 0, 93979, 0, 93978, 0, 93977, 0, 93976, 0, 93975, 0, 93974, 0, 93973, 0, 93972, 0, 93971, 0, 93970, 0, 93969, 0, 93968, 0, 93967, 0, 93966, 0, 93965, 0, 93964, 0, 93963, 0, 93962, 0, 93961, 0, 93960, 0, 93959, 0, 93958, 0, 93957, 0, 93956, 0, 93955, 0, 93954, 0, 93953, 0, 93952, 0, 93951, 0, 93950, 0, 93949, 0, 93948, 0, 93947, 0, 93946, 0, 93945, 0, 93944, 0, 93943, 0, 93942, 0, 93941, 0, 93940, 0, 93939, 0, 93938, 0, 93937, 0, 93936, 0, 93935, 0, 93934, 0, 93933, 0, 93932, 0, 93931, 0, 93930, 0, 93929, 0, 93928, 0, 93927, 0, 93926, 0, 93925, 0, 93924, 0, 93923, 0, 93922, 0, 93921, 0, 93920, 0, 93919, 0, 93918, 0, 93917, 0, 93916, 0, 93915, 0, 93914, 0, 93913, 0, 93912, 0, 93911, 0, 93910, 0, 93909, 0, 93908, 0, 93907, 0, 93906, 0, 93905, 0, 93904, 0, 93903, 0, 93902, 0, 93901, 0, 93900, 0, 93899, 0, 93898, 0, 93897, 0, 93896, 0, 93895, 0, 93894, 0, 93893, 0, 93892, 0, 93891, 0, 93890, 0, 93889, 0, 93888, 0, 93887, 0, 93886, 0, 93885, 0, 93884, 0, 93883, 0, 93882, 0, 93881, 0, 93880, 0, 93879, 0, 93878, 0, 93877, 0, 93876, 0, 93875, 0, 93874, 0, 93873, 0, 93872, 0, 93871, 0, 93870, 0, 93869, 0, 93868, 0, 93867, 0, 93866, 0, 93865, 0, 93864, 0, 93863, 0, 93862, 0, 93861, 0, 93860, 0, 93859, 0, 93858, 0, 93857, 0, 93856, 0, 93855, 0, 93854, 0, 93853, 0, 93852, 0, 93851, 0, 93850, 0, 93849, 0, 93848, 0, 93847, 0, 93846, 0, 93845, 0, 93844, 0, 93843, 0, 93842, 0, 93841, 0, 93840, 0, 93839, 0, 93838, 0, 93837, 0, 93836, 0, 93835, 0, 93834, 0, 93833, 0, 93832, 0, 93831, 0, 93830, 0, 93829, 0, 93828, 0, 93827, 0, 93826, 0, 93825, 0, 93824, 0, 93823, 0, 93822, 0, 93821, 0, 93820, 0, 93819, 0, 93818, 0, 93817, 0, 93816, 0, 93815, 0, 93814, 0, 93813, 0, 93812, 0, 93811, 0, 93810, 0, 93809, 0, 93808, 0, 93807, 0, 93806, 0, 93805, 0, 93804, 0, 93803, 0, 93802, 0, 93801, 0, 93800, 0, 93799, 0, 93798, 0, 93797, 0, 93796, 0, 93795, 0, 93794, 0, 93793, 0, 93792, 0, 93791, 0, 93790, 0, 93789, 0, 93788, 0, 93787, 0, 93786, 0, 93785, 0, 93784, 0, 93783, 0, 93782, 0, 93781, 0, 93780, 0, 93779, 0, 93778, 0, 93777, 0, 93776, 0, 93775, 0, 93774, 0, 93773, 0, 93772, 0, 93771, 0, 93770, 0, 93769, 0, 93768, 0, 93767, 0, 93766, 0, 93765, 0, 93764, 0, 93763, 0, 93762, 0, 93761, 0, 93760, 0, 93759, 0, 93758, 0, 93757, 0, 93756, 0, 93755, 0, 93754, 0, 93753, 0, 93752, 0, 93751, 0, 93750, 0, 93749, 0, 93748, 0, 93747, 0, 93746, 0, 93745, 0, 93744, 0, 93743, 0, 93742, 0, 93741, 0, 93740, 0, 93739, 0, 93738, 0, 93737, 0, 93736, 0, 93735, 0, 93734, 0, 93733, 0, 93732, 0, 93731, 0, 93730, 0, 93729, 0, 93728, 0, 93727, 0, 93726, 0, 93725, 0, 93724, 0, 93723, 0, 93722, 0, 93721, 0, 93720, 0, 93719, 0, 93718, 0, 93717, 0, 93716, 0, 93715, 0, 93714, 0, 93713, 0, 93712, 0, 93711, 0, 93710, 0, 93709, 0, 93708, 0, 93707, 0, 93706, 0, 93705, 0, 93704, 0, 93703, 0, 93702, 0, 93701, 0, 93700, 0, 93699, 0, 93698, 0, 93697, 0, 93696, 0, 93695, 0, 93694, 0, 93693, 0, 93692, 0, 93691, 0, 93690, 0, 93689, 0, 93688, 0, 93687, 0, 93686, 0, 93685, 0, 93684, 0, 93683, 0, 93682, 0, 93681, 0, 93680, 0, 93679, 0, 93678, 0, 93677, 0, 93676, 0, 93675, 0, 93674, 0, 93673, 0, 93672, 0, 93671, 0, 93670, 0, 93669, 0, 93668, 0, 93667, 0, 93666, 0, 93665, 0, 93664, 0, 93663, 0, 93662, 0, 93661, 0, 93660, 0, 93659, 0, 93658, 0, 93657, 0, 93656, 0, 93655, 0, 93654, 0, 93653, 0, 93652, 0, 93651, 0, 93650, 0, 93649, 0, 93648, 0, 93647, 0, 93646, 0, 93645, 0, 93644, 0, 93643, 0, 93642, 0, 93641, 0, 93640, 0, 93639, 0, 93638, 0, 93637, 0, 93636, 0, 93635, 0, 93634, 0, 93633, 0, 93632, 0, 93631, 0, 93630, 0, 93629, 0, 93628, 0, 93627, 0, 93626, 0, 93625, 0, 93624, 0, 93623, 0, 93622, 0, 93621, 0, 93620, 0, 93619, 0, 93618, 0, 93617, 0, 93616, 0, 93615, 0, 93614, 0, 93613, 0, 93612, 0, 93611, 0, 93610, 0, 93609, 0, 93608, 0, 93607, 0, 93606, 0, 93605, 0, 93604, 0, 93603, 0, 93602, 0, 93601, 0, 93600, 0, 93599, 0, 93598, 0, 93597, 0, 93596, 0, 93595, 0, 93594, 0, 93593, 0, 93592, 0, 93591, 0, 93590, 0, 93589, 0, 93588, 0, 93587, 0, 93586, 0, 93585, 0, 93584, 0, 93583, 0, 93582, 0, 93581, 0, 93580, 0, 93579, 0, 93578, 0, 93577, 0, 93576, 0, 93575, 0, 93574, 0, 93573, 0, 93572, 0, 93571, 0, 93570, 0, 93569, 0, 93568, 0, 93567, 0, 93566, 0, 93565, 0, 93564, 0, 93563, 0, 93562, 0, 93561, 0, 93560, 0, 93559, 0, 93558, 0, 93557, 0, 93556, 0, 93555, 0, 93554, 0, 93553, 0, 93552, 0, 93551, 0, 93550, 0, 93549, 0, 93548, 0, 93547, 0, 93546, 0, 93545, 0, 93544, 0, 93543, 0, 93542, 0, 93541, 0, 93540, 0, 93539, 0, 93538, 0, 93537, 0, 93536, 0, 93535, 0, 93534, 0, 93533, 0, 93532, 0, 93531, 0, 93530, 0, 93529, 0, 93528, 0, 93527, 0, 93526, 0, 93525, 0, 93524, 0, 93523, 0, 93522, 0, 93521, 0, 93520, 0, 93519, 0, 93518, 0, 93517, 0, 93516, 0, 93515, 0, 93514, 0, 93513, 0, 93512, 0, 93511, 0, 93510, 0, 93509, 0, 93508, 0, 93507, 0, 93506, 0, 93505, 0, 93504, 0, 93503, 0, 93502, 0, 93501, 0, 93500, 0, 93499, 0, 93498, 0, 93497, 0, 93496, 0, 93495, 0, 93494, 0, 93493, 0, 93492, 0, 93491, 0, 93490, 0, 93489, 0, 93488, 0, 93487, 0, 93486, 0, 93485, 0, 93484, 0, 93483, 0, 93482, 0, 93481, 0, 93480, 0, 93479, 0, 93478, 0, 93477, 0, 93476, 0, 93475, 0, 93474, 0, 93473, 0, 93472, 0, 93471, 0, 93470, 0, 93469, 0, 93468, 0, 93467, 0, 93466, 0, 93465, 0, 93464, 0, 93463, 0, 93462, 0, 93461, 0, 93460, 0, 93459, 0, 93458, 0, 93457, 0, 93456, 0, 93455, 0, 93454, 0, 93453, 0, 93452, 0, 93451, 0, 93450, 0, 93449, 0, 93448, 0, 93447, 0, 93446, 0, 93445, 0, 93444, 0, 93443, 0, 93442, 0, 93441, 0, 93440, 0, 93439, 0, 93438, 0, 93437, 0, 93436, 0, 93435, 0, 93434, 0, 93433, 0, 93432, 0, 93431, 0, 93430, 0, 93429, 0, 93428, 0, 93427, 0, 93426, 0, 93425, 0, 93424, 0, 93423, 0, 93422, 0, 93421, 0, 93420, 0, 93419, 0, 93418, 0, 93417, 0, 93416, 0, 93415, 0, 93414, 0, 93413, 0, 93412, 0, 93411, 0, 93410, 0, 93409, 0, 93408, 0, 93407, 0, 93406, 0, 93405, 0, 93404, 0, 93403, 0, 93402, 0, 93401, 0, 93400, 0, 93399, 0, 93398, 0, 93397, 0, 93396, 0, 93395, 0, 93394, 0, 93393, 0, 93392, 0, 93391, 0, 93390, 0, 93389, 0, 93388, 0, 93387, 0, 93386, 0, 93385, 0, 93384, 0, 93383, 0, 93382, 0, 93381, 0, 93380, 0, 93379, 0, 93378, 0, 93377, 0, 93376, 0, 93375, 0, 93374, 0, 93373, 0, 93372, 0, 93371, 0, 93370, 0, 93369, 0, 93368, 0, 93367, 0, 93366, 0, 93365, 0, 93364, 0, 93363, 0, 93362, 0, 93361, 0, 93360, 0, 93359, 0, 93358, 0, 93357, 0, 93356, 0, 93355, 0, 93354, 0, 93353, 0, 93352, 0, 93351, 0, 93350, 0, 93349, 0, 93348, 0, 93347, 0, 93346, 0, 93345, 0, 93344, 0, 93343, 0, 93342, 0, 93341, 0, 93340, 0, 93339, 0, 93338, 0, 93337, 0, 93336, 0, 93335, 0, 93334, 0, 93333, 0, 93332, 0, 93331, 0, 93330, 0, 93329, 0, 93328, 0, 93327, 0, 93326, 0, 93325, 0, 93324, 0, 93323, 0, 93322, 0, 93321, 0, 93320, 0, 93319, 0, 93318, 0, 93317, 0, 93316, 0, 93315, 0, 93314, 0, 93313, 0, 93312, 0, 93311, 0, 93310, 0, 93309, 0, 93308, 0, 93307, 0, 93306, 0, 93305, 0, 93304, 0, 93303, 0, 93302, 0, 93301, 0, 93300, 0, 93299, 0, 93298, 0, 93297, 0, 93296, 0, 93295, 0, 93294, 0, 93293, 0, 93292, 0, 93291, 0, 93290, 0, 93289, 0, 93288, 0, 93287, 0, 93286, 0, 93285, 0, 93284, 0, 93283, 0, 93282, 0, 93281, 0, 93280, 0, 93279, 0, 93278, 0, 93277, 0, 93276, 0, 93275, 0, 93274, 0, 93273, 0, 93272, 0, 93271, 0, 93270, 0, 93269, 0, 93268, 0, 93267, 0, 93266, 0, 93265, 0, 93264, 0, 93263, 0, 93262, 0, 93261, 0, 93260, 0, 93259, 0, 93258, 0, 93257, 0, 93256, 0, 93255, 0, 93254, 0, 93253, 0, 93252, 0, 93251, 0, 93250, 0, 93249, 0, 93248, 0, 93247, 0, 93246, 0, 93245, 0, 93244, 0, 93243, 0, 93242, 0, 93241, 0, 93240, 0, 93239, 0, 93238, 0, 93237, 0, 93236, 0, 93235, 0, 93234, 0, 93233, 0, 93232, 0, 93231, 0, 93230, 0, 93229, 0, 93228, 0, 93227, 0, 93226, 0, 93225, 0, 93224, 0, 93223, 0, 93222, 0, 93221, 0, 93220, 0, 93219, 0, 93218, 0, 93217, 0, 93216, 0, 93215, 0, 93214, 0, 93213, 0, 93212, 0, 93211, 0, 93210, 0, 93209, 0, 93208, 0, 93207, 0, 93206, 0, 93205, 0, 93204, 0, 93203, 0, 93202, 0, 93201, 0, 93200, 0, 93199, 0, 93198, 0, 93197, 0, 93196, 0, 93195, 0, 93194, 0, 93193, 0, 93192, 0, 93191, 0, 93190, 0, 93189, 0, 93188, 0, 93187, 0, 93186, 0, 93185, 0, 93184, 0, 93183, 0, 93182, 0, 93181, 0, 93180, 0, 93179, 0, 93178, 0, 93177, 0, 93176, 0, 93175, 0, 93174, 0, 93173, 0, 93172, 0, 93171, 0, 93170, 0, 93169, 0, 93168, 0, 93167, 0, 93166, 0, 93165, 0, 93164, 0, 93163, 0, 93162, 0, 93161, 0, 93160, 0, 93159, 0, 93158, 0, 93157, 0, 93156, 0, 93155, 0, 93154, 0, 93153, 0, 93152, 0, 93151, 0, 93150, 0, 93149, 0, 93148, 0, 93147, 0, 93146, 0, 93145, 0, 93144, 0, 93143, 0, 93142, 0, 93141, 0, 93140, 0, 93139, 0, 93138, 0, 93137, 0, 93136, 0, 93135, 0, 93134, 0, 93133, 0, 93132, 0, 93131, 0, 93130, 0, 93129, 0, 93128, 0, 93127, 0, 93126, 0, 93125, 0, 93124, 0, 93123, 0, 93122, 0, 93121, 0, 93120, 0, 93119, 0, 93118, 0, 93117, 0, 93116, 0, 93115, 0, 93114, 0, 93113, 0, 93112, 0, 93111, 0, 93110, 0, 93109, 0, 93108, 0, 93107, 0, 93106, 0, 93105, 0, 93104, 0, 93103, 0, 93102, 0, 93101, 0, 93100, 0, 93099, 0, 93098, 0, 93097, 0, 93096, 0, 93095, 0, 93094, 0, 93093, 0, 93092, 0, 93091, 0, 93090, 0, 93089, 0, 93088, 0, 93087, 0, 93086, 0, 93085, 0, 93084, 0, 93083, 0, 93082, 0, 93081, 0, 93080, 0, 93079, 0, 93078, 0, 93077, 0, 93076, 0, 93075, 0, 93074, 0, 93073, 0, 93072, 0, 93071, 0, 93070, 0, 93069, 0, 93068, 0, 93067, 0, 93066, 0, 93065, 0, 93064, 0, 93063, 0, 93062, 0, 93061, 0, 93060, 0, 93059, 0, 93058, 0, 93057, 0, 93056, 0, 93055, 0, 93054, 0, 93053, 0, 93052, 0, 93051, 0, 93050, 0, 93049, 0, 93048, 0, 93047, 0, 93046, 0, 93045, 0, 93044, 0, 93043, 0, 93042, 0, 93041, 0, 93040, 0, 93039, 0, 93038, 0, 93037, 0, 93036, 0, 93035, 0, 93034, 0, 93033, 0, 93032, 0, 93031, 0, 93030, 0, 93029, 0, 93028, 0, 93027, 0, 93026, 0, 93025, 0, 93024, 0, 93023, 0, 93022, 0, 93021, 0, 93020, 0, 93019, 0, 93018, 0, 93017, 0, 93016, 0, 93015, 0, 93014, 0, 93013, 0, 93012, 0, 93011, 0, 93010, 0, 93009, 0, 93008, 0, 93007, 0, 93006, 0, 93005, 0, 93004, 0, 93003, 0, 93002, 0, 93001, 0, 93000, 0, 92999, 0, 92998, 0, 92997, 0, 92996, 0, 92995, 0, 92994, 0, 92993, 0, 92992, 0, 92991, 0, 92990, 0, 92989, 0, 92988, 0, 92987, 0, 92986, 0, 92985, 0, 92984, 0, 92983, 0, 92982, 0, 92981, 0, 92980, 0, 92979, 0, 92978, 0, 92977, 0, 92976, 0, 92975, 0, 92974, 0, 92973, 0, 92972, 0, 92971, 0, 92970, 0, 92969, 0, 92968, 0, 92967, 0, 92966, 0, 92965, 0, 92964, 0, 92963, 0, 92962, 0, 92961, 0, 92960, 0, 92959, 0, 92958, 0, 92957, 0, 92956, 0, 92955, 0, 92954, 0, 92953, 0, 92952, 0, 92951, 0, 92950, 0, 92949, 0, 92948, 0, 92947, 0, 92946, 0, 92945, 0, 92944, 0, 92943, 0, 92942, 0, 92941, 0, 92940, 0, 92939, 0, 92938, 0, 92937, 0, 92936, 0, 92935, 0, 92934, 0, 92933, 0, 92932, 0, 92931, 0, 92930, 0, 92929, 0, 92928, 0, 92927, 0, 92926, 0, 92925, 0, 92924, 0, 92923, 0, 92922, 0, 92921, 0, 92920, 0, 92919, 0, 92918, 0, 92917, 0, 92916, 0, 92915, 0, 92914, 0, 92913, 0, 92912, 0, 92911, 0, 92910, 0, 92909, 0, 92908, 0, 92907, 0, 92906, 0, 92905, 0, 92904, 0, 92903, 0, 92902, 0, 92901, 0, 92900, 0, 92899, 0, 92898, 0, 92897, 0, 92896, 0, 92895, 0, 92894, 0, 92893, 0, 92892, 0, 92891, 0, 92890, 0, 92889, 0, 92888, 0, 92887, 0, 92886, 0, 92885, 0, 92884, 0, 92883, 0, 92882, 0, 92881, 0, 92880, 0, 92879, 0, 92878, 0, 92877, 0, 92876, 0, 92875, 0, 92874, 0, 92873, 0, 92872, 0, 92871, 0, 92870, 0, 92869, 0, 92868, 0, 92867, 0, 92866, 0, 92865, 0, 92864, 0, 92863, 0, 92862, 0, 92861, 0, 92860, 0, 92859, 0, 92858, 0, 92857, 0, 92856, 0, 92855, 0, 92854, 0, 92853, 0, 92852, 0, 92851, 0, 92850, 0, 92849, 0, 92848, 0, 92847, 0, 92846, 0, 92845, 0, 92844, 0, 92843, 0, 92842, 0, 92841, 0, 92840, 0, 92839, 0, 92838, 0, 92837, 0, 92836, 0, 92835, 0, 92834, 0, 92833, 0, 92832, 0, 92831, 0, 92830, 0, 92829, 0, 92828, 0, 92827, 0, 92826, 0, 92825, 0, 92824, 0, 92823, 0, 92822, 0, 92821, 0, 92820, 0, 92819, 0, 92818, 0, 92817, 0, 92816, 0, 92815, 0, 92814, 0, 92813, 0, 92812, 0, 92811, 0, 92810, 0, 92809, 0, 92808, 0, 92807, 0, 92806, 0, 92805, 0, 92804, 0, 92803, 0, 92802, 0, 92801, 0, 92800, 0, 92799, 0, 92798, 0, 92797, 0, 92796, 0, 92795, 0, 92794, 0, 92793, 0, 92792, 0, 92791, 0, 92790, 0, 92789, 0, 92788, 0, 92787, 0, 92786, 0, 92785, 0, 92784, 0, 92783, 0, 92782, 0, 92781, 0, 92780, 0, 92779, 0, 92778, 0, 92777, 0, 92776, 0, 92775, 0, 92774, 0, 92773, 0, 92772, 0, 92771, 0, 92770, 0, 92769, 0, 92768, 0, 92767, 0, 92766, 0, 92765, 0, 92764, 0, 92763, 0, 92762, 0, 92761, 0, 92760, 0, 92759, 0, 92758, 0, 92757, 0, 92756, 0, 92755, 0, 92754, 0, 92753, 0, 92752, 0, 92751, 0, 92750, 0, 92749, 0, 92748, 0, 92747, 0, 92746, 0, 92745, 0, 92744, 0, 92743, 0, 92742, 0, 92741, 0, 92740, 0, 92739, 0, 92738, 0, 92737, 0, 92736, 0, 92735, 0, 92734, 0, 92733, 0, 92732, 0, 92731, 0, 92730, 0, 92729, 0, 92728, 0, 92727, 0, 92726, 0, 92725, 0, 92724, 0, 92723, 0, 92722, 0, 92721, 0, 92720, 0, 92719, 0, 92718, 0, 92717, 0, 92716, 0, 92715, 0, 92714, 0, 92713, 0, 92712, 0, 92711, 0, 92710, 0, 92709, 0, 92708, 0, 92707, 0, 92706, 0, 92705, 0, 92704, 0, 92703, 0, 92702, 0, 92701, 0, 92700, 0, 92699, 0, 92698, 0, 92697, 0, 92696, 0, 92695, 0, 92694, 0, 92693, 0, 92692, 0, 92691, 0, 92690, 0, 92689, 0, 92688, 0, 92687, 0, 92686, 0, 92685, 0, 92684, 0, 92683, 0, 92682, 0, 92681, 0, 92680, 0, 92679, 0, 92678, 0, 92677, 0, 92676, 0, 92675, 0, 92674, 0, 92673, 0, 92672, 0, 92671, 0, 92670, 0, 92669, 0, 92668, 0, 92667, 0, 92666, 0, 92665, 0, 92664, 0, 92663, 0, 92662, 0, 92661, 0, 92660, 0, 92659, 0, 92658, 0, 92657, 0, 92656, 0, 92655, 0, 92654, 0, 92653, 0, 92652, 0, 92651, 0, 92650, 0, 92649, 0, 92648, 0, 92647, 0, 92646, 0, 92645, 0, 92644, 0, 92643, 0, 92642, 0, 92641, 0, 92640, 0, 92639, 0, 92638, 0, 92637, 0, 92636, 0, 92635, 0, 92634, 0, 92633, 0, 92632, 0, 92631, 0, 92630, 0, 92629, 0, 92628, 0, 92627, 0, 92626, 0, 92625, 0, 92624, 0, 92623, 0, 92622, 0, 92621, 0, 92620, 0, 92619, 0, 92618, 0, 92617, 0, 92616, 0, 92615, 0, 92614, 0, 92613, 0, 92612, 0, 92611, 0, 92610, 0, 92609, 0, 92608, 0, 92607, 0, 92606, 0, 92605, 0, 92604, 0, 92603, 0, 92602, 0, 92601, 0, 92600, 0, 92599, 0, 92598, 0, 92597, 0, 92596, 0, 92595, 0, 92594, 0, 92593, 0, 92592, 0, 92591, 0, 92590, 0, 92589, 0, 92588, 0, 92587, 0, 92586, 0, 92585, 0, 92584, 0, 92583, 0, 92582, 0, 92581, 0, 92580, 0, 92579, 0, 92578, 0, 92577, 0, 92576, 0, 92575, 0, 92574, 0, 92573, 0, 92572, 0, 92571, 0, 92570, 0, 92569, 0, 92568, 0, 92567, 0, 92566, 0, 92565, 0, 92564, 0, 92563, 0, 92562, 0, 92561, 0, 92560, 0, 92559, 0, 92558, 0, 92557, 0, 92556, 0, 92555, 0, 92554, 0, 92553, 0, 92552, 0, 92551, 0, 92550, 0, 92549, 0, 92548, 0, 92547, 0, 92546, 0, 92545, 0, 92544, 0, 92543, 0, 92542, 0, 92541, 0, 92540, 0, 92539, 0, 92538, 0, 92537, 0, 92536, 0, 92535, 0, 92534, 0, 92533, 0, 92532, 0, 92531, 0, 92530, 0, 92529, 0, 92528, 0, 92527, 0, 92526, 0, 92525, 0, 92524, 0, 92523, 0, 92522, 0, 92521, 0, 92520, 0, 92519, 0, 92518, 0, 92517, 0, 92516, 0, 92515, 0, 92514, 0, 92513, 0, 92512, 0, 92511, 0, 92510, 0, 92509, 0, 92508, 0, 92507, 0, 92506, 0, 92505, 0, 92504, 0, 92503, 0, 92502, 0, 92501, 0, 92500, 0, 92499, 0, 92498, 0, 92497, 0, 92496, 0, 92495, 0, 92494, 0, 92493, 0, 92492, 0, 92491, 0, 92490, 0, 92489, 0, 92488, 0, 92487, 0, 92486, 0, 92485, 0, 92484, 0, 92483, 0, 92482, 0, 92481, 0, 92480, 0, 92479, 0, 92478, 0, 92477, 0, 92476, 0, 92475, 0, 92474, 0, 92473, 0, 92472, 0, 92471, 0, 92470, 0, 92469, 0, 92468, 0, 92467, 0, 92466, 0, 92465, 0, 92464, 0, 92463, 0, 92462, 0, 92461, 0, 92460, 0, 92459, 0, 92458, 0, 92457, 0, 92456, 0, 92455, 0, 92454, 0, 92453, 0, 92452, 0, 92451, 0, 92450, 0, 92449, 0, 92448, 0, 92447, 0, 92446, 0, 92445, 0, 92444, 0, 92443, 0, 92442, 0, 92441, 0, 92440, 0, 92439, 0, 92438, 0, 92437, 0, 92436, 0, 92435, 0, 92434, 0, 92433, 0, 92432, 0, 92431, 0, 92430, 0, 92429, 0, 92428, 0, 92427, 0, 92426, 0, 92425, 0, 92424, 0, 92423, 0, 92422, 0, 92421, 0, 92420, 0, 92419, 0, 92418, 0, 92417, 0, 92416, 0, 92415, 0, 92414, 0, 92413, 0, 92412, 0, 92411, 0, 92410, 0, 92409, 0, 92408, 0, 92407, 0, 92406, 0, 92405, 0, 92404, 0, 92403, 0, 92402, 0, 92401, 0, 92400, 0, 92399, 0, 92398, 0, 92397, 0, 92396, 0, 92395, 0, 92394, 0, 92393, 0, 92392, 0, 92391, 0, 92390, 0, 92389, 0, 92388, 0, 92387, 0, 92386, 0, 92385, 0, 92384, 0, 92383, 0, 92382, 0, 92381, 0, 92380, 0, 92379, 0, 92378, 0, 92377, 0, 92376, 0, 92375, 0, 92374, 0, 92373, 0, 92372, 0, 92371, 0, 92370, 0, 92369, 0, 92368, 0, 92367, 0, 92366, 0, 92365, 0, 92364, 0, 92363, 0, 92362, 0, 92361, 0, 92360, 0, 92359, 0, 92358, 0, 92357, 0, 92356, 0, 92355, 0, 92354, 0, 92353, 0, 92352, 0, 92351, 0, 92350, 0, 92349, 0, 92348, 0, 92347, 0, 92346, 0, 92345, 0, 92344, 0, 92343, 0, 92342, 0, 92341, 0, 92340, 0, 92339, 0, 92338, 0, 92337, 0, 92336, 0, 92335, 0, 92334, 0, 92333, 0, 92332, 0, 92331, 0, 92330, 0, 92329, 0, 92328, 0, 92327, 0, 92326, 0, 92325, 0, 92324, 0, 92323, 0, 92322, 0, 92321, 0, 92320, 0, 92319, 0, 92318, 0, 92317, 0, 92316, 0, 92315, 0, 92314, 0, 92313, 0, 92312, 0, 92311, 0, 92310, 0, 92309, 0, 92308, 0, 92307, 0, 92306, 0, 92305, 0, 92304, 0, 92303, 0, 92302, 0, 92301, 0, 92300, 0, 92299, 0, 92298, 0, 92297, 0, 92296, 0, 92295, 0, 92294, 0, 92293, 0, 92292, 0, 92291, 0, 92290, 0, 92289, 0, 92288, 0, 92287, 0, 92286, 0, 92285, 0, 92284, 0, 92283, 0, 92282, 0, 92281, 0, 92280, 0, 92279, 0, 92278, 0, 92277, 0, 92276, 0, 92275, 0, 92274, 0, 92273, 0, 92272, 0, 92271, 0, 92270, 0, 92269, 0, 92268, 0, 92267, 0, 92266, 0, 92265, 0, 92264, 0, 92263, 0, 92262, 0, 92261, 0, 92260, 0, 92259, 0, 92258, 0, 92257, 0, 92256, 0, 92255, 0, 92254, 0, 92253, 0, 92252, 0, 92251, 0, 92250, 0, 92249, 0, 92248, 0, 92247, 0, 92246, 0, 92245, 0, 92244, 0, 92243, 0, 92242, 0, 92241, 0, 92240, 0, 92239, 0, 92238, 0, 92237, 0, 92236, 0, 92235, 0, 92234, 0, 92233, 0, 92232, 0, 92231, 0, 92230, 0, 92229, 0, 92228, 0, 92227, 0, 92226, 0, 92225, 0, 92224, 0, 92223, 0, 92222, 0, 92221, 0, 92220, 0, 92219, 0, 92218, 0, 92217, 0, 92216, 0, 92215, 0, 92214, 0, 92213, 0, 92212, 0, 92211, 0, 92210, 0, 92209, 0, 92208, 0, 92207, 0, 92206, 0, 92205, 0, 92204, 0, 92203, 0, 92202, 0, 92201, 0, 92200, 0, 92199, 0, 92198, 0, 92197, 0, 92196, 0, 92195, 0, 92194, 0, 92193, 0, 92192, 0, 92191, 0, 92190, 0, 92189, 0, 92188, 0, 92187, 0, 92186, 0, 92185, 0, 92184, 0, 92183, 0, 92182, 0, 92181, 0, 92180, 0, 92179, 0, 92178, 0, 92177, 0, 92176, 0, 92175, 0, 92174, 0, 92173, 0, 92172, 0, 92171, 0, 92170, 0, 92169, 0, 92168, 0, 92167, 0, 92166, 0, 92165, 0, 92164, 0, 92163, 0, 92162, 0, 92161, 0, 92160, 0, 92159, 0, 92158, 0, 92157, 0, 92156, 0, 92155, 0, 92154, 0, 92153, 0, 92152, 0, 92151, 0, 92150, 0, 92149, 0, 92148, 0, 92147, 0, 92146, 0, 92145, 0, 92144, 0, 92143, 0, 92142, 0, 92141, 0, 92140, 0, 92139, 0, 92138, 0, 92137, 0, 92136, 0, 92135, 0, 92134, 0, 92133, 0, 92132, 0, 92131, 0, 92130, 0, 92129, 0, 92128, 0, 92127, 0, 92126, 0, 92125, 0, 92124, 0, 92123, 0, 92122, 0, 92121, 0, 92120, 0, 92119, 0, 92118, 0, 92117, 0, 92116, 0, 92115, 0, 92114, 0, 92113, 0, 92112, 0, 92111, 0, 92110, 0, 92109, 0, 92108, 0, 92107, 0, 92106, 0, 92105, 0, 92104, 0, 92103, 0, 92102, 0, 92101, 0, 92100, 0, 92099, 0, 92098, 0, 92097, 0, 92096, 0, 92095, 0, 92094, 0, 92093, 0, 92092, 0, 92091, 0, 92090, 0, 92089, 0, 92088, 0, 92087, 0, 92086, 0, 92085, 0, 92084, 0, 92083, 0, 92082, 0, 92081, 0, 92080, 0, 92079, 0, 92078, 0, 92077, 0, 92076, 0, 92075, 0, 92074, 0, 92073, 0, 92072, 0, 92071, 0, 92070, 0, 92069, 0, 92068, 0, 92067, 0, 92066, 0, 92065, 0, 92064, 0, 92063, 0, 92062, 0, 92061, 0, 92060, 0, 92059, 0, 92058, 0, 92057, 0, 92056, 0, 92055, 0, 92054, 0, 92053, 0, 92052, 0, 92051, 0, 92050, 0, 92049, 0, 92048, 0, 92047, 0, 92046, 0, 92045, 0, 92044, 0, 92043, 0, 92042, 0, 92041, 0, 92040, 0, 92039, 0, 92038, 0, 92037, 0, 92036, 0, 92035, 0, 92034, 0, 92033, 0, 92032, 0, 92031, 0, 92030, 0, 92029, 0, 92028, 0, 92027, 0, 92026, 0, 92025, 0, 92024, 0, 92023, 0, 92022, 0, 92021, 0, 92020, 0, 92019, 0, 92018, 0, 92017, 0, 92016, 0, 92015, 0, 92014, 0, 92013, 0, 92012, 0, 92011, 0, 92010, 0, 92009, 0, 92008, 0, 92007, 0, 92006, 0, 92005, 0, 92004, 0, 92003, 0, 92002, 0, 92001, 0, 92000, 0, 91999, 0, 91998, 0, 91997, 0, 91996, 0, 91995, 0, 91994, 0, 91993, 0, 91992, 0, 91991, 0, 91990, 0, 91989, 0, 91988, 0, 91987, 0, 91986, 0, 91985, 0, 91984, 0, 91983, 0, 91982, 0, 91981, 0, 91980, 0, 91979, 0, 91978, 0, 91977, 0, 91976, 0, 91975, 0, 91974, 0, 91973, 0, 91972, 0, 91971, 0, 91970, 0, 91969, 0, 91968, 0, 91967, 0, 91966, 0, 91965, 0, 91964, 0, 91963, 0, 91962, 0, 91961, 0, 91960, 0, 91959, 0, 91958, 0, 91957, 0, 91956, 0, 91955, 0, 91954, 0, 91953, 0, 91952, 0, 91951, 0, 91950, 0, 91949, 0, 91948, 0, 91947, 0, 91946, 0, 91945, 0, 91944, 0, 91943, 0, 91942, 0, 91941, 0, 91940, 0, 91939, 0, 91938, 0, 91937, 0, 91936, 0, 91935, 0, 91934, 0, 91933, 0, 91932, 0, 91931, 0, 91930, 0, 91929, 0, 91928, 0, 91927, 0, 91926, 0, 91925, 0, 91924, 0, 91923, 0, 91922, 0, 91921, 0, 91920, 0, 91919, 0, 91918, 0, 91917, 0, 91916, 0, 91915, 0, 91914, 0, 91913, 0, 91912, 0, 91911, 0, 91910, 0, 91909, 0, 91908, 0, 91907, 0, 91906, 0, 91905, 0, 91904, 0, 91903, 0, 91902, 0, 91901, 0, 91900, 0, 91899, 0, 91898, 0, 91897, 0, 91896, 0, 91895, 0, 91894, 0, 91893, 0, 91892, 0, 91891, 0, 91890, 0, 91889, 0, 91888, 0, 91887, 0, 91886, 0, 91885, 0, 91884, 0, 91883, 0, 91882, 0, 91881, 0, 91880, 0, 91879, 0, 91878, 0, 91877, 0, 91876, 0, 91875, 0, 91874, 0, 91873, 0, 91872, 0, 91871, 0, 91870, 0, 91869, 0, 91868, 0, 91867, 0, 91866, 0, 91865, 0, 91864, 0, 91863, 0, 91862, 0, 91861, 0, 91860, 0, 91859, 0, 91858, 0, 91857, 0, 91856, 0, 91855, 0, 91854, 0, 91853, 0, 91852, 0, 91851, 0, 91850, 0, 91849, 0, 91848, 0, 91847, 0, 91846, 0, 91845, 0, 91844, 0, 91843, 0, 91842, 0, 91841, 0, 91840, 0, 91839, 0, 91838, 0, 91837, 0, 91836, 0, 91835, 0, 91834, 0, 91833, 0, 91832, 0, 91831, 0, 91830, 0, 91829, 0, 91828, 0, 91827, 0, 91826, 0, 91825, 0, 91824, 0, 91823, 0, 91822, 0, 91821, 0, 91820, 0, 91819, 0, 91818, 0, 91817, 0, 91816, 0, 91815, 0, 91814, 0, 91813, 0, 91812, 0, 91811, 0, 91810, 0, 91809, 0, 91808, 0, 91807, 0, 91806, 0, 91805, 0, 91804, 0, 91803, 0, 91802, 0, 91801, 0, 91800, 0, 91799, 0, 91798, 0, 91797, 0, 91796, 0, 91795, 0, 91794, 0, 91793, 0, 91792, 0, 91791, 0, 91790, 0, 91789, 0, 91788, 0, 91787, 0, 91786, 0, 91785, 0, 91784, 0, 91783, 0, 91782, 0, 91781, 0, 91780, 0, 91779, 0, 91778, 0, 91777, 0, 91776, 0, 91775, 0, 91774, 0, 91773, 0, 91772, 0, 91771, 0, 91770, 0, 91769, 0, 91768, 0, 91767, 0, 91766, 0, 91765, 0, 91764, 0, 91763, 0, 91762, 0, 91761, 0, 91760, 0, 91759, 0, 91758, 0, 91757, 0, 91756, 0, 91755, 0, 91754, 0, 91753, 0, 91752, 0, 91751, 0, 91750, 0, 91749, 0, 91748, 0, 91747, 0, 91746, 0, 91745, 0, 91744, 0, 91743, 0, 91742, 0, 91741, 0, 91740, 0, 91739, 0, 91738, 0, 91737, 0, 91736, 0, 91735, 0, 91734, 0, 91733, 0, 91732, 0, 91731, 0, 91730, 0, 91729, 0, 91728, 0, 91727, 0, 91726, 0, 91725, 0, 91724, 0, 91723, 0, 91722, 0, 91721, 0, 91720, 0, 91719, 0, 91718, 0, 91717, 0, 91716, 0, 91715, 0, 91714, 0, 91713, 0, 91712, 0, 91711, 0, 91710, 0, 91709, 0, 91708, 0, 91707, 0, 91706, 0, 91705, 0, 91704, 0, 91703, 0, 91702, 0, 91701, 0, 91700, 0, 91699, 0, 91698, 0, 91697, 0, 91696, 0, 91695, 0, 91694, 0, 91693, 0, 91692, 0, 91691, 0, 91690, 0, 91689, 0, 91688, 0, 91687, 0, 91686, 0, 91685, 0, 91684, 0, 91683, 0, 91682, 0, 91681, 0, 91680, 0, 91679, 0, 91678, 0, 91677, 0, 91676, 0, 91675, 0, 91674, 0, 91673, 0, 91672, 0, 91671, 0, 91670, 0, 91669, 0, 91668, 0, 91667, 0, 91666, 0, 91665, 0, 91664, 0, 91663, 0, 91662, 0, 91661, 0, 91660, 0, 91659, 0, 91658, 0, 91657, 0, 91656, 0, 91655, 0, 91654, 0, 91653, 0, 91652, 0, 91651, 0, 91650, 0, 91649, 0, 91648, 0, 91647, 0, 91646, 0, 91645, 0, 91644, 0, 91643, 0, 91642, 0, 91641, 0, 91640, 0, 91639, 0, 91638, 0, 91637, 0, 91636, 0, 91635, 0, 91634, 0, 91633, 0, 91632, 0, 91631, 0, 91630, 0, 91629, 0, 91628, 0, 91627, 0, 91626, 0, 91625, 0, 91624, 0, 91623, 0, 91622, 0, 91621, 0, 91620, 0, 91619, 0, 91618, 0, 91617, 0, 91616, 0, 91615, 0, 91614, 0, 91613, 0, 91612, 0, 91611, 0, 91610, 0, 91609, 0, 91608, 0, 91607, 0, 91606, 0, 91605, 0, 91604, 0, 91603, 0, 91602, 0, 91601, 0, 91600, 0, 91599, 0, 91598, 0, 91597, 0, 91596, 0, 91595, 0, 91594, 0, 91593, 0, 91592, 0, 91591, 0, 91590, 0, 91589, 0, 91588, 0, 91587, 0, 91586, 0, 91585, 0, 91584, 0, 91583, 0, 91582, 0, 91581, 0, 91580, 0, 91579, 0, 91578, 0, 91577, 0, 91576, 0, 91575, 0, 91574, 0, 91573, 0, 91572, 0, 91571, 0, 91570, 0, 91569, 0, 91568, 0, 91567, 0, 91566, 0, 91565, 0, 91564, 0, 91563, 0, 91562, 0, 91561, 0, 91560, 0, 91559, 0, 91558, 0, 91557, 0, 91556, 0, 91555, 0, 91554, 0, 91553, 0, 91552, 0, 91551, 0, 91550, 0, 91549, 0, 91548, 0, 91547, 0, 91546, 0, 91545, 0, 91544, 0, 91543, 0, 91542, 0, 91541, 0, 91540, 0, 91539, 0, 91538, 0, 91537, 0, 91536, 0, 91535, 0, 91534, 0, 91533, 0, 91532, 0, 91531, 0, 91530, 0, 91529, 0, 91528, 0, 91527, 0, 91526, 0, 91525, 0, 91524, 0, 91523, 0, 91522, 0, 91521, 0, 91520, 0, 91519, 0, 91518, 0, 91517, 0, 91516, 0, 91515, 0, 91514, 0, 91513, 0, 91512, 0, 91511, 0, 91510, 0, 91509, 0, 91508, 0, 91507, 0, 91506, 0, 91505, 0, 91504, 0, 91503, 0, 91502, 0, 91501, 0, 91500, 0, 91499, 0, 91498, 0, 91497, 0, 91496, 0, 91495, 0, 91494, 0, 91493, 0, 91492, 0, 91491, 0, 91490, 0, 91489, 0, 91488, 0, 91487, 0, 91486, 0, 91485, 0, 91484, 0, 91483, 0, 91482, 0, 91481, 0, 91480, 0, 91479, 0, 91478, 0, 91477, 0, 91476, 0, 91475, 0, 91474, 0, 91473, 0, 91472, 0, 91471, 0, 91470, 0, 91469, 0, 91468, 0, 91467, 0, 91466, 0, 91465, 0, 91464, 0, 91463, 0, 91462, 0, 91461, 0, 91460, 0, 91459, 0, 91458, 0, 91457, 0, 91456, 0, 91455, 0, 91454, 0, 91453, 0, 91452, 0, 91451, 0, 91450, 0, 91449, 0, 91448, 0, 91447, 0, 91446, 0, 91445, 0, 91444, 0, 91443, 0, 91442, 0, 91441, 0, 91440, 0, 91439, 0, 91438, 0, 91437, 0, 91436, 0, 91435, 0, 91434, 0, 91433, 0, 91432, 0, 91431, 0, 91430, 0, 91429, 0, 91428, 0, 91427, 0, 91426, 0, 91425, 0, 91424, 0, 91423, 0, 91422, 0, 91421, 0, 91420, 0, 91419, 0, 91418, 0, 91417, 0, 91416, 0, 91415, 0, 91414, 0, 91413, 0, 91412, 0, 91411, 0, 91410, 0, 91409, 0, 91408, 0, 91407, 0, 91406, 0, 91405, 0, 91404, 0, 91403, 0, 91402, 0, 91401, 0, 91400, 0, 91399, 0, 91398, 0, 91397, 0, 91396, 0, 91395, 0, 91394, 0, 91393, 0, 91392, 0, 91391, 0, 91390, 0, 91389, 0, 91388, 0, 91387, 0, 91386, 0, 91385, 0, 91384, 0, 91383, 0, 91382, 0, 91381, 0, 91380, 0, 91379, 0, 91378, 0, 91377, 0, 91376, 0, 91375, 0, 91374, 0, 91373, 0, 91372, 0, 91371, 0, 91370, 0, 91369, 0, 91368, 0, 91367, 0, 91366, 0, 91365, 0, 91364, 0, 91363, 0, 91362, 0, 91361, 0, 91360, 0, 91359, 0, 91358, 0, 91357, 0, 91356, 0, 91355, 0, 91354, 0, 91353, 0, 91352, 0, 91351, 0, 91350, 0, 91349, 0, 91348, 0, 91347, 0, 91346, 0, 91345, 0, 91344, 0, 91343, 0, 91342, 0, 91341, 0, 91340, 0, 91339, 0, 91338, 0, 91337, 0, 91336, 0, 91335, 0, 91334, 0, 91333, 0, 91332, 0, 91331, 0, 91330, 0, 91329, 0, 91328, 0, 91327, 0, 91326, 0, 91325, 0, 91324, 0, 91323, 0, 91322, 0, 91321, 0, 91320, 0, 91319, 0, 91318, 0, 91317, 0, 91316, 0, 91315, 0, 91314, 0, 91313, 0, 91312, 0, 91311, 0, 91310, 0, 91309, 0, 91308, 0, 91307, 0, 91306, 0, 91305, 0, 91304, 0, 91303, 0, 91302, 0, 91301, 0, 91300, 0, 91299, 0, 91298, 0, 91297, 0, 91296, 0, 91295, 0, 91294, 0, 91293, 0, 91292, 0, 91291, 0, 91290, 0, 91289, 0, 91288, 0, 91287, 0, 91286, 0, 91285, 0, 91284, 0, 91283, 0, 91282, 0, 91281, 0, 91280, 0, 91279, 0, 91278, 0, 91277, 0, 91276, 0, 91275, 0, 91274, 0, 91273, 0, 91272, 0, 91271, 0, 91270, 0, 91269, 0, 91268, 0, 91267, 0, 91266, 0, 91265, 0, 91264, 0, 91263, 0, 91262, 0, 91261, 0, 91260, 0, 91259, 0, 91258, 0, 91257, 0, 91256, 0, 91255, 0, 91254, 0, 91253, 0, 91252, 0, 91251, 0, 91250, 0, 91249, 0, 91248, 0, 91247, 0, 91246, 0, 91245, 0, 91244, 0, 91243, 0, 91242, 0, 91241, 0, 91240, 0, 91239, 0, 91238, 0, 91237, 0, 91236, 0, 91235, 0, 91234, 0, 91233, 0, 91232, 0, 91231, 0, 91230, 0, 91229, 0, 91228, 0, 91227, 0, 91226, 0, 91225, 0, 91224, 0, 91223, 0, 91222, 0, 91221, 0, 91220, 0, 91219, 0, 91218, 0, 91217, 0, 91216, 0, 91215, 0, 91214, 0, 91213, 0, 91212, 0, 91211, 0, 91210, 0, 91209, 0, 91208, 0, 91207, 0, 91206, 0, 91205, 0, 91204, 0, 91203, 0, 91202, 0, 91201, 0, 91200, 0, 91199, 0, 91198, 0, 91197, 0, 91196, 0, 91195, 0, 91194, 0, 91193, 0, 91192, 0, 91191, 0, 91190, 0, 91189, 0, 91188, 0, 91187, 0, 91186, 0, 91185, 0, 91184, 0, 91183, 0, 91182, 0, 91181, 0, 91180, 0, 91179, 0, 91178, 0, 91177, 0, 91176, 0, 91175, 0, 91174, 0, 91173, 0, 91172, 0, 91171, 0, 91170, 0, 91169, 0, 91168, 0, 91167, 0, 91166, 0, 91165, 0, 91164, 0, 91163, 0, 91162, 0, 91161, 0, 91160, 0, 91159, 0, 91158, 0, 91157, 0, 91156, 0, 91155, 0, 91154, 0, 91153, 0, 91152, 0, 91151, 0, 91150, 0, 91149, 0, 91148, 0, 91147, 0, 91146, 0, 91145, 0, 91144, 0, 91143, 0, 91142, 0, 91141, 0, 91140, 0, 91139, 0, 91138, 0, 91137, 0, 91136, 0, 91135, 0, 91134, 0, 91133, 0, 91132, 0, 91131, 0, 91130, 0, 91129, 0, 91128, 0, 91127, 0, 91126, 0, 91125, 0, 91124, 0, 91123, 0, 91122, 0, 91121, 0, 91120, 0, 91119, 0, 91118, 0, 91117, 0, 91116, 0, 91115, 0, 91114, 0, 91113, 0, 91112, 0, 91111, 0, 91110, 0, 91109, 0, 91108, 0, 91107, 0, 91106, 0, 91105, 0, 91104, 0, 91103, 0, 91102, 0, 91101, 0, 91100, 0, 91099, 0, 91098, 0, 91097, 0, 91096, 0, 91095, 0, 91094, 0, 91093, 0, 91092, 0, 91091, 0, 91090, 0, 91089, 0, 91088, 0, 91087, 0, 91086, 0, 91085, 0, 91084, 0, 91083, 0, 91082, 0, 91081, 0, 91080, 0, 91079, 0, 91078, 0, 91077, 0, 91076, 0, 91075, 0, 91074, 0, 91073, 0, 91072, 0, 91071, 0, 91070, 0, 91069, 0, 91068, 0, 91067, 0, 91066, 0, 91065, 0, 91064, 0, 91063, 0, 91062, 0, 91061, 0, 91060, 0, 91059, 0, 91058, 0, 91057, 0, 91056, 0, 91055, 0, 91054, 0, 91053, 0, 91052, 0, 91051, 0, 91050, 0, 91049, 0, 91048, 0, 91047, 0, 91046, 0, 91045, 0, 91044, 0, 91043, 0, 91042, 0, 91041, 0, 91040, 0, 91039, 0, 91038, 0, 91037, 0, 91036, 0, 91035, 0, 91034, 0, 91033, 0, 91032, 0, 91031, 0, 91030, 0, 91029, 0, 91028, 0, 91027, 0, 91026, 0, 91025, 0, 91024, 0, 91023, 0, 91022, 0, 91021, 0, 91020, 0, 91019, 0, 91018, 0, 91017, 0, 91016, 0, 91015, 0, 91014, 0, 91013, 0, 91012, 0, 91011, 0, 91010, 0, 91009, 0, 91008, 0, 91007, 0, 91006, 0, 91005, 0, 91004, 0, 91003, 0, 91002, 0, 91001, 0, 91000, 0, 90999, 0, 90998, 0, 90997, 0, 90996, 0, 90995, 0, 90994, 0, 90993, 0, 90992, 0, 90991, 0, 90990, 0, 90989, 0, 90988, 0, 90987, 0, 90986, 0, 90985, 0, 90984, 0, 90983, 0, 90982, 0, 90981, 0, 90980, 0, 90979, 0, 90978, 0, 90977, 0, 90976, 0, 90975, 0, 90974, 0, 90973, 0, 90972, 0, 90971, 0, 90970, 0, 90969, 0, 90968, 0, 90967, 0, 90966, 0, 90965, 0, 90964, 0, 90963, 0, 90962, 0, 90961, 0, 90960, 0, 90959, 0, 90958, 0, 90957, 0, 90956, 0, 90955, 0, 90954, 0, 90953, 0, 90952, 0, 90951, 0, 90950, 0, 90949, 0, 90948, 0, 90947, 0, 90946, 0, 90945, 0, 90944, 0, 90943, 0, 90942, 0, 90941, 0, 90940, 0, 90939, 0, 90938, 0, 90937, 0, 90936, 0, 90935, 0, 90934, 0, 90933, 0, 90932, 0, 90931, 0, 90930, 0, 90929, 0, 90928, 0, 90927, 0, 90926, 0, 90925, 0, 90924, 0, 90923, 0, 90922, 0, 90921, 0, 90920, 0, 90919, 0, 90918, 0, 90917, 0, 90916, 0, 90915, 0, 90914, 0, 90913, 0, 90912, 0, 90911, 0, 90910, 0, 90909, 0, 90908, 0, 90907, 0, 90906, 0, 90905, 0, 90904, 0, 90903, 0, 90902, 0, 90901, 0, 90900, 0, 90899, 0, 90898, 0, 90897, 0, 90896, 0, 90895, 0, 90894, 0, 90893, 0, 90892, 0, 90891, 0, 90890, 0, 90889, 0, 90888, 0, 90887, 0, 90886, 0, 90885, 0, 90884, 0, 90883, 0, 90882, 0, 90881, 0, 90880, 0, 90879, 0, 90878, 0, 90877, 0, 90876, 0, 90875, 0, 90874, 0, 90873, 0, 90872, 0, 90871, 0, 90870, 0, 90869, 0, 90868, 0, 90867, 0, 90866, 0, 90865, 0, 90864, 0, 90863, 0, 90862, 0, 90861, 0, 90860, 0, 90859, 0, 90858, 0, 90857, 0, 90856, 0, 90855, 0, 90854, 0, 90853, 0, 90852, 0, 90851, 0, 90850, 0, 90849, 0, 90848, 0, 90847, 0, 90846, 0, 90845, 0, 90844, 0, 90843, 0, 90842, 0, 90841, 0, 90840, 0, 90839, 0, 90838, 0, 90837, 0, 90836, 0, 90835, 0, 90834, 0, 90833, 0, 90832, 0, 90831, 0, 90830, 0, 90829, 0, 90828, 0, 90827, 0, 90826, 0, 90825, 0, 90824, 0, 90823, 0, 90822, 0, 90821, 0, 90820, 0, 90819, 0, 90818, 0, 90817, 0, 90816, 0, 90815, 0, 90814, 0, 90813, 0, 90812, 0, 90811, 0, 90810, 0, 90809, 0, 90808, 0, 90807, 0, 90806, 0, 90805, 0, 90804, 0, 90803, 0, 90802, 0, 90801, 0, 90800, 0, 90799, 0, 90798, 0, 90797, 0, 90796, 0, 90795, 0, 90794, 0, 90793, 0, 90792, 0, 90791, 0, 90790, 0, 90789, 0, 90788, 0, 90787, 0, 90786, 0, 90785, 0, 90784, 0, 90783, 0, 90782, 0, 90781, 0, 90780, 0, 90779, 0, 90778, 0, 90777, 0, 90776, 0, 90775, 0, 90774, 0, 90773, 0, 90772, 0, 90771, 0, 90770, 0, 90769, 0, 90768, 0, 90767, 0, 90766, 0, 90765, 0, 90764, 0, 90763, 0, 90762, 0, 90761, 0, 90760, 0, 90759, 0, 90758, 0, 90757, 0, 90756, 0, 90755, 0, 90754, 0, 90753, 0, 90752, 0, 90751, 0, 90750, 0, 90749, 0, 90748, 0, 90747, 0, 90746, 0, 90745, 0, 90744, 0, 90743, 0, 90742, 0, 90741, 0, 90740, 0, 90739, 0, 90738, 0, 90737, 0, 90736, 0, 90735, 0, 90734, 0, 90733, 0, 90732, 0, 90731, 0, 90730, 0, 90729, 0, 90728, 0, 90727, 0, 90726, 0, 90725, 0, 90724, 0, 90723, 0, 90722, 0, 90721, 0, 90720, 0, 90719, 0, 90718, 0, 90717, 0, 90716, 0, 90715, 0, 90714, 0, 90713, 0, 90712, 0, 90711, 0, 90710, 0, 90709, 0, 90708, 0, 90707, 0, 90706, 0, 90705, 0, 90704, 0, 90703, 0, 90702, 0, 90701, 0, 90700, 0, 90699, 0, 90698, 0, 90697, 0, 90696, 0, 90695, 0, 90694, 0, 90693, 0, 90692, 0, 90691, 0, 90690, 0, 90689, 0, 90688, 0, 90687, 0, 90686, 0, 90685, 0, 90684, 0, 90683, 0, 90682, 0, 90681, 0, 90680, 0, 90679, 0, 90678, 0, 90677, 0, 90676, 0, 90675, 0, 90674, 0, 90673, 0, 90672, 0, 90671, 0, 90670, 0, 90669, 0, 90668, 0, 90667, 0, 90666, 0, 90665, 0, 90664, 0, 90663, 0, 90662, 0, 90661, 0, 90660, 0, 90659, 0, 90658, 0, 90657, 0, 90656, 0, 90655, 0, 90654, 0, 90653, 0, 90652, 0, 90651, 0, 90650, 0, 90649, 0, 90648, 0, 90647, 0, 90646, 0, 90645, 0, 90644, 0, 90643, 0, 90642, 0, 90641, 0, 90640, 0, 90639, 0, 90638, 0, 90637, 0, 90636, 0, 90635, 0, 90634, 0, 90633, 0, 90632, 0, 90631, 0, 90630, 0, 90629, 0, 90628, 0, 90627, 0, 90626, 0, 90625, 0, 90624, 0, 90623, 0, 90622, 0, 90621, 0, 90620, 0, 90619, 0, 90618, 0, 90617, 0, 90616, 0, 90615, 0, 90614, 0, 90613, 0, 90612, 0, 90611, 0, 90610, 0, 90609, 0, 90608, 0, 90607, 0, 90606, 0, 90605, 0, 90604, 0, 90603, 0, 90602, 0, 90601, 0, 90600, 0, 90599, 0, 90598, 0, 90597, 0, 90596, 0, 90595, 0, 90594, 0, 90593, 0, 90592, 0, 90591, 0, 90590, 0, 90589, 0, 90588, 0, 90587, 0, 90586, 0, 90585, 0, 90584, 0, 90583, 0, 90582, 0, 90581, 0, 90580, 0, 90579, 0, 90578, 0, 90577, 0, 90576, 0, 90575, 0, 90574, 0, 90573, 0, 90572, 0, 90571, 0, 90570, 0, 90569, 0, 90568, 0, 90567, 0, 90566, 0, 90565, 0, 90564, 0, 90563, 0, 90562, 0, 90561, 0, 90560, 0, 90559, 0, 90558, 0, 90557, 0, 90556, 0, 90555, 0, 90554, 0, 90553, 0, 90552, 0, 90551, 0, 90550, 0, 90549, 0, 90548, 0, 90547, 0, 90546, 0, 90545, 0, 90544, 0, 90543, 0, 90542, 0, 90541, 0, 90540, 0, 90539, 0, 90538, 0, 90537, 0, 90536, 0, 90535, 0, 90534, 0, 90533, 0, 90532, 0, 90531, 0, 90530, 0, 90529, 0, 90528, 0, 90527, 0, 90526, 0, 90525, 0, 90524, 0, 90523, 0, 90522, 0, 90521, 0, 90520, 0, 90519, 0, 90518, 0, 90517, 0, 90516, 0, 90515, 0, 90514, 0, 90513, 0, 90512, 0, 90511, 0, 90510, 0, 90509, 0, 90508, 0, 90507, 0, 90506, 0, 90505, 0, 90504, 0, 90503, 0, 90502, 0, 90501, 0, 90500, 0, 90499, 0, 90498, 0, 90497, 0, 90496, 0, 90495, 0, 90494, 0, 90493, 0, 90492, 0, 90491, 0, 90490, 0, 90489, 0, 90488, 0, 90487, 0, 90486, 0, 90485, 0, 90484, 0, 90483, 0, 90482, 0, 90481, 0, 90480, 0, 90479, 0, 90478, 0, 90477, 0, 90476, 0, 90475, 0, 90474, 0, 90473, 0, 90472, 0, 90471, 0, 90470, 0, 90469, 0, 90468, 0, 90467, 0, 90466, 0, 90465, 0, 90464, 0, 90463, 0, 90462, 0, 90461, 0, 90460, 0, 90459, 0, 90458, 0, 90457, 0, 90456, 0, 90455, 0, 90454, 0, 90453, 0, 90452, 0, 90451, 0, 90450, 0, 90449, 0, 90448, 0, 90447, 0, 90446, 0, 90445, 0, 90444, 0, 90443, 0, 90442, 0, 90441, 0, 90440, 0, 90439, 0, 90438, 0, 90437, 0, 90436, 0, 90435, 0, 90434, 0, 90433, 0, 90432, 0, 90431, 0, 90430, 0, 90429, 0, 90428, 0, 90427, 0, 90426, 0, 90425, 0, 90424, 0, 90423, 0, 90422, 0, 90421, 0, 90420, 0, 90419, 0, 90418, 0, 90417, 0, 90416, 0, 90415, 0, 90414, 0, 90413, 0, 90412, 0, 90411, 0, 90410, 0, 90409, 0, 90408, 0, 90407, 0, 90406, 0, 90405, 0, 90404, 0, 90403, 0, 90402, 0, 90401, 0, 90400, 0, 90399, 0, 90398, 0, 90397, 0, 90396, 0, 90395, 0, 90394, 0, 90393, 0, 90392, 0, 90391, 0, 90390, 0, 90389, 0, 90388, 0, 90387, 0, 90386, 0, 90385, 0, 90384, 0, 90383, 0, 90382, 0, 90381, 0, 90380, 0, 90379, 0, 90378, 0, 90377, 0, 90376, 0, 90375, 0, 90374, 0, 90373, 0, 90372, 0, 90371, 0, 90370, 0, 90369, 0, 90368, 0, 90367, 0, 90366, 0, 90365, 0, 90364, 0, 90363, 0, 90362, 0, 90361, 0, 90360, 0, 90359, 0, 90358, 0, 90357, 0, 90356, 0, 90355, 0, 90354, 0, 90353, 0, 90352, 0, 90351, 0, 90350, 0, 90349, 0, 90348, 0, 90347, 0, 90346, 0, 90345, 0, 90344, 0, 90343, 0, 90342, 0, 90341, 0, 90340, 0, 90339, 0, 90338, 0, 90337, 0, 90336, 0, 90335, 0, 90334, 0, 90333, 0, 90332, 0, 90331, 0, 90330, 0, 90329, 0, 90328, 0, 90327, 0, 90326, 0, 90325, 0, 90324, 0, 90323, 0, 90322, 0, 90321, 0, 90320, 0, 90319, 0, 90318, 0, 90317, 0, 90316, 0, 90315, 0, 90314, 0, 90313, 0, 90312, 0, 90311, 0, 90310, 0, 90309, 0, 90308, 0, 90307, 0, 90306, 0, 90305, 0, 90304, 0, 90303, 0, 90302, 0, 90301, 0, 90300, 0, 90299, 0, 90298, 0, 90297, 0, 90296, 0, 90295, 0, 90294, 0, 90293, 0, 90292, 0, 90291, 0, 90290, 0, 90289, 0, 90288, 0, 90287, 0, 90286, 0, 90285, 0, 90284, 0, 90283, 0, 90282, 0, 90281, 0, 90280, 0, 90279, 0, 90278, 0, 90277, 0, 90276, 0, 90275, 0, 90274, 0, 90273, 0, 90272, 0, 90271, 0, 90270, 0, 90269, 0, 90268, 0, 90267, 0, 90266, 0, 90265, 0, 90264, 0, 90263, 0, 90262, 0, 90261, 0, 90260, 0, 90259, 0, 90258, 0, 90257, 0, 90256, 0, 90255, 0, 90254, 0, 90253, 0, 90252, 0, 90251, 0, 90250, 0, 90249, 0, 90248, 0, 90247, 0, 90246, 0, 90245, 0, 90244, 0, 90243, 0, 90242, 0, 90241, 0, 90240, 0, 90239, 0, 90238, 0, 90237, 0, 90236, 0, 90235, 0, 90234, 0, 90233, 0, 90232, 0, 90231, 0, 90230, 0, 90229, 0, 90228, 0, 90227, 0, 90226, 0, 90225, 0, 90224, 0, 90223, 0, 90222, 0, 90221, 0, 90220, 0, 90219, 0, 90218, 0, 90217, 0, 90216, 0, 90215, 0, 90214, 0, 90213, 0, 90212, 0, 90211, 0, 90210, 0, 90209, 0, 90208, 0, 90207, 0, 90206, 0, 90205, 0, 90204, 0, 90203, 0, 90202, 0, 90201, 0, 90200, 0, 90199, 0, 90198, 0, 90197, 0, 90196, 0, 90195, 0, 90194, 0, 90193, 0, 90192, 0, 90191, 0, 90190, 0, 90189, 0, 90188, 0, 90187, 0, 90186, 0, 90185, 0, 90184, 0, 90183, 0, 90182, 0, 90181, 0, 90180, 0, 90179, 0, 90178, 0, 90177, 0, 90176, 0, 90175, 0, 90174, 0, 90173, 0, 90172, 0, 90171, 0, 90170, 0, 90169, 0, 90168, 0, 90167, 0, 90166, 0, 90165, 0, 90164, 0, 90163, 0, 90162, 0, 90161, 0, 90160, 0, 90159, 0, 90158, 0, 90157, 0, 90156, 0, 90155, 0, 90154, 0, 90153, 0, 90152, 0, 90151, 0, 90150, 0, 90149, 0, 90148, 0, 90147, 0, 90146, 0, 90145, 0, 90144, 0, 90143, 0, 90142, 0, 90141, 0, 90140, 0, 90139, 0, 90138, 0, 90137, 0, 90136, 0, 90135, 0, 90134, 0, 90133, 0, 90132, 0, 90131, 0, 90130, 0, 90129, 0, 90128, 0, 90127, 0, 90126, 0, 90125, 0, 90124, 0, 90123, 0, 90122, 0, 90121, 0, 90120, 0, 90119, 0, 90118, 0, 90117, 0, 90116, 0, 90115, 0, 90114, 0, 90113, 0, 90112, 0, 90111, 0, 90110, 0, 90109, 0, 90108, 0, 90107, 0, 90106, 0, 90105, 0, 90104, 0, 90103, 0, 90102, 0, 90101, 0, 90100, 0, 90099, 0, 90098, 0, 90097, 0, 90096, 0, 90095, 0, 90094, 0, 90093, 0, 90092, 0, 90091, 0, 90090, 0, 90089, 0, 90088, 0, 90087, 0, 90086, 0, 90085, 0, 90084, 0, 90083, 0, 90082, 0, 90081, 0, 90080, 0, 90079, 0, 90078, 0, 90077, 0, 90076, 0, 90075, 0, 90074, 0, 90073, 0, 90072, 0, 90071, 0, 90070, 0, 90069, 0, 90068, 0, 90067, 0, 90066, 0, 90065, 0, 90064, 0, 90063, 0, 90062, 0, 90061, 0, 90060, 0, 90059, 0, 90058, 0, 90057, 0, 90056, 0, 90055, 0, 90054, 0, 90053, 0, 90052, 0, 90051, 0, 90050, 0, 90049, 0, 90048, 0, 90047, 0, 90046, 0, 90045, 0, 90044, 0, 90043, 0, 90042, 0, 90041, 0, 90040, 0, 90039, 0, 90038, 0, 90037, 0, 90036, 0, 90035, 0, 90034, 0, 90033, 0, 90032, 0, 90031, 0, 90030, 0, 90029, 0, 90028, 0, 90027, 0, 90026, 0, 90025, 0, 90024, 0, 90023, 0, 90022, 0, 90021, 0, 90020, 0, 90019, 0, 90018, 0, 90017, 0, 90016, 0, 90015, 0, 90014, 0, 90013, 0, 90012, 0, 90011, 0, 90010, 0, 90009, 0, 90008, 0, 90007, 0, 90006, 0, 90005, 0, 90004, 0, 90003, 0, 90002, 0, 90001});

        System.out.println(a);
    }
}
