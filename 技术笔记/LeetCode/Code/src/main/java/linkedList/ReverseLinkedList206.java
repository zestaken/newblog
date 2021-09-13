package linkedList;

import java.util.List;

public class ReverseLinkedList206 {

    public ListNode reverseList(ListNode head) {
        
        ListNode pre = null;
        ListNode next = null;
        while(head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return head;
    }
}
