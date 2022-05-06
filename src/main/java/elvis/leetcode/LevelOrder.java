package elvis.leetcode;

import elvis.leetcode.model.Node;
import scala.Char;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LevelOrder {
    public class QueueNode {
        int depth;
        Node node;

        public QueueNode(int depth, Node node) {
            this.depth = depth;
            this.node = node;
        }
    }

    public List<List<Integer>> levelOrder_Mine(Node root) {
        Queue<QueueNode> q = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        q.offer(new QueueNode(0, root));
        while (!q.isEmpty()) {
            QueueNode n = q.poll();
            List<Integer> l = null;
            if (res.size() <= n.depth) {
                l = new ArrayList<>();
                res.add(l);
            }
            l = res.get(n.depth);
            l.add(n.node.val);
            if (n.node.children != null) n.node.children.forEach(x -> q.offer(new QueueNode(n.depth + 1, x)));
        }
        return res;
    }

    public List<List<Integer>> levelOrder(Node root) {
        Queue<Node> q = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> l = new ArrayList<>();
            while (size > 0) {
                Node n = q.poll();
                size--;
                l.add(n.val);
                if (n.children != null) for (Node cn : n.children)
                    q.offer(cn);
            }
            res.add(l);
        }
        return res;
    }

    public boolean judgeCircle(String moves) {
        AtomicInteger up = new AtomicInteger();
        AtomicInteger right = new AtomicInteger();
        moves.chars().forEach(x -> {
            if (x == 'U') {
                up.getAndIncrement();
            } else if (x == 'D') {
                up.getAndDecrement();
            } else if (x == 'R') {
                right.getAndIncrement();
            } else if (x == 'L') {
                right.getAndDecrement();
            }
        });
        return up.get() == 0 && right.get() == 0;
    }


    public static void main(String[] args) {
        LevelOrder l = new LevelOrder();
    }
}
