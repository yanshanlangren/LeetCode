package elvis.leetcode;

import java.util.PriorityQueue;

public class MinimumDeletions {
    public class Node {
        String str;
        int min;
        int layer;

        public Node(String str, int min, int layer) {
            this.str = str;
            this.min = min;
            this.layer = layer;
        }
    }

    public int minimumDeletions_broad_first(String s) {
        PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> a.min - b.min == 0 ? b.layer - a.layer : a.min - b.min);
        q.offer(new Node(s.substring(0, 1), 0, 1));
        while (q.size() > 0) {
            Node n = q.poll();
            System.out.println("str:" + n.str + ", min:" + n.min + ", layer:" + n.layer);
            if (n.layer == s.length())
                return n.min;
            if (s.charAt(n.layer) >= n.str.charAt(n.str.length() - 1)) {
                //直接加
                q.offer(new Node(n.str + s.charAt(n.layer), n.min, n.layer + 1));
            } else {
                //去当前的a, 或者去前面的b
                //去当前的a
                q.offer(new Node(n.str, n.min + 1, n.layer + 1));
                //去前面的b
                int i = n.str.length() - 1;
                while (i > -1 && n.str.charAt(i) == 'b')
                    i--;
                q.offer(new Node(n.str.substring(0, i + 1) + s.charAt(n.layer), n.min + n.str.length() - i - 1, n.layer + 1));
            }
        }
        return s.length();
    }

    public int minimumDeletions(String s) {
        int dp = 0;
        int countB = 0, last = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'b') {
                countB++;
            } else {
                dp = Math.min(dp + 1, countB);
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        MinimumDeletions m = new MinimumDeletions();
//        System.out.println(m.minimumDeletions("aababbab"));
//        System.out.println(m.minimumDeletions("bbaaaaabb"));
        System.out.println(m.minimumDeletions("abaababbbbaabaaabbbaaabbbaaaaaabbababababaaabbbbababababbabaaabababbabaabaaabbababbbbabbabbbb"));

    }
}
