package linkedList;

import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * 通过数组构造链表
     * @param nums
     * @return
     */
    public static ListNode makeLinkedList(int[] nums) {
        ListNode head = new ListNode();
        ListNode cur;
        ListNode pre = head;
        for(int num : nums) {
            cur = new ListNode(num);
            pre.next = cur;
            pre = cur;
        }
        return head.next;
    }

    public static boolean isEqual(ListNode e1, ListNode res1) {
        ListNode e = e1, res = res1;
        while(res != null && e != null) {
            assert e.val == res.val : res.val;
            e = e.next;
            res = res.next;
        }
        return true;
    }
}
