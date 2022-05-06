package elvis.interview;

/*

You are given the head of a singly linked-list. The list can be represented as:
L0 - L1 - ... - Ln-1 - Ln

Reorder the list to be on the following form:
L0 - Ln - L1 - Ln-1 - L2 - Ln-2 -...

You may not modify the values in the list's nodes. Only nodes themselves may be changed.


0 -> 1 -> 2 -> 3

0 -> 3 -> 1 -> 2

*/


// Class name must be "Main"
// Libraries included:
// json simple, guava, apache commons lang3, junit, jmock

class ListNode {
    ListNode next;
    int val;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Shopee {
    public static void main(String[] args) {
        System.out.println("Hello, world!");

        Shopee instance = new Shopee();
        ListNode head = new ListNode(0, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))));
        instance.traverse(instance.solution(head));
    }

    public ListNode solution(ListNode head) {
        // find middle
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse
        ListNode mid = reverse(null, slow);

        // reform list
        ListNode cur = head, mid_cur = mid, next = head, mid_next = mid;
        while (cur.next != null && mid_cur != null) {
            next = cur.next;
            mid_next = mid_cur.next;

            cur.next = mid_cur;
            mid_cur.next = next;

            cur = next;
            mid_cur = mid_next;
        }
        cur.next = null;
        if (mid_cur != null)
            mid_cur.next = null;
        return head;
    }

    public void traverse(ListNode node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null)
            return pre;
        //System.out.println("pre:"+pre.val+", cur:"+cur.val);
        ListNode next = cur.next;
        cur.next = pre;
        return reverse(cur, next);
    }
}

