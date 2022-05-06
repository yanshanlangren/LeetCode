package elvis.leetcode;

import elvis.leetcode.model.ListNode;

import java.util.HashMap;

public class DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode pre, cur;
        HashMap<Integer, Integer> m = new HashMap();
        cur = head;
//        cur = head.next;
        while (cur != null) {
            if (m.containsKey(cur.val))
                m.put(cur.val, m.get(cur.val) + 1);
            else
                m.put(cur.val, 1);
            cur = cur.next;
        }
        cur = head;
        while (cur != null && m.get(cur.val) > 1) {
            cur = cur.next;
        }
        if (cur == null)
            return null;
        head = cur;
        pre = cur;
        cur = cur.next;
        while (cur != null) {
            while (cur != null && m.get(cur.val) > 1) {
                cur = cur.next;
            }
            if (cur == null) {
                pre.next = null;
            } else {
                pre.next = cur;
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        DeleteDuplicates d = new DeleteDuplicates();
//        ListNode n = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5)))))));
//        ListNode n = new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3)))));
        ListNode n = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(3)))));
        ListNode ans = d.deleteDuplicates(n);
        System.out.println(ans);
    }
}
