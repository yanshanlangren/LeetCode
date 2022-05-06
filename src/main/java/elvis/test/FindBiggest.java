package elvis.test;

import java.util.ArrayList;
import java.util.List;

public class FindBiggest {
    public static class Node {
        Node left, right;
        int val;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    List<Integer> max;

    public List<Integer> findBigest(Node root) {
        max = new ArrayList<>();
        max.add(Integer.MIN_VALUE);
        find(0, root);
        return max;
    }

    public void find(int depth, Node node) {
        if (max.get(depth) < node.val) {
            max.set(depth, node.val);
        }
        if ((node.left != null || node.right != null) && depth + 1 >= max.size())
            max.add(Integer.MIN_VALUE);
        if (node.left != null)
            find(depth + 1, node.left);
        if (node.right != null)
            find(depth + 1, node.right);
    }

    public static void main(String[] args) {
        FindBiggest f = new FindBiggest();
        Node root = new Node(1, new Node(98, null, new Node(13, null, null)), new Node(29, null, null));
        f.findBigest(root);
    }
}
