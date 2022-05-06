package elvis.leetcode;

import elvis.leetcode.model.ListNode;

public class DeleteDuplicatesII {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode cur = head;
        while (cur.next != null && cur.val == cur.next.val) {
            cur = cur.next;
        }
        head.next = deleteDuplicates(cur.next);
        return head;
    }

    public static void main(String[] args) {
        DeleteDuplicatesII d = new DeleteDuplicatesII();
//        ListNode n = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5)))))));
        ListNode n = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(3)))));
        n = d.deleteDuplicates(n);
        return;
    }
}
