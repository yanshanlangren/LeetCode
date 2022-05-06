package elvis.leetcode;

import elvis.leetcode.model.ListNode;

public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode l1 = list1, l2 = list2, l = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l.next = l1;
                l1 = l1.next;
                l = l.next;
            } else {
                l.next = l2;
                l2 = l2.next;
                l = l.next;
            }
        }
        if (l1 != null) {
            l.next = l1;
        }
        if (l2 != null) {
            l.next = l2;
        }
        return head.next;
    }
}
