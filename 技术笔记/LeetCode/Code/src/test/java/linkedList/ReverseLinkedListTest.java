package linkedList;

import org.junit.jupiter.api.Test;

public class ReverseLinkedListTest {

    @Test
    public void test() {
        ReverseLinkedList206 reverseLinkedList = new ReverseLinkedList206();

        ListNode head1 = new ListNode(1, new ListNode(2));
        ListNode e1 = new ListNode(2, new ListNode(1));


        ListNode res1 = reverseLinkedList.reverseList(head1);
        ListNode e = e1, res = res1;
        while(res == null || e == null) {
            assert e.val == res.val : res.val;
            e = e.next;
            res = res.next;
        }
    }
}
