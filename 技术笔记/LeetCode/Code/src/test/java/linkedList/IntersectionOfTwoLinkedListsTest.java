package linkedList;

import org.junit.jupiter.api.Test;

public class IntersectionOfTwoLinkedListsTest {

    @Test
    public void test() {
        IntersectionOfTwoLinkedLists160 intersectionOfTwoLinkedLists = new IntersectionOfTwoLinkedLists160();

        int[] nums1_a = {4, 1, 8, 4, 5};
        int[] nums1_b = {5, 0, 1};
        ListNode head1_a = ListNode.makeLinkedList(nums1_a);
        ListNode head1_b = ListNode.makeLinkedList(nums1_b);
        ListNode e1 = head1_a.next.next;
        head1_b.next.next.next = e1;

        ListNode res1 = intersectionOfTwoLinkedLists.getIntersectionNode(head1_a, head1_b);
        assert  res1 == e1 : res1;

    }
}
