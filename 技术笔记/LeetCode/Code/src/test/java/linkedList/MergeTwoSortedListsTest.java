package linkedList;

import org.junit.jupiter.api.Test;

public class MergeTwoSortedListsTest {

    @Test
    public void test() {
        MergeTwoSortedLists21 mergeTwoSortedLists = new MergeTwoSortedLists21();

        int[] nums1_a = {1, 2, 4};
        int[] nums1_b ={1, 3, 4};
        int[] nums1_c = {1, 1, 2, 3, 4, 4};
        ListNode l1_a = ListNode.makeLinkedList(nums1_a);
        ListNode l1_b = ListNode.makeLinkedList(nums1_b);
        ListNode e1 = ListNode.makeLinkedList(nums1_c);

        ListNode res1 = mergeTwoSortedLists.mergeTwoLists(l1_a, l1_b);
        assert ListNode.isEqual(e1, res1);
    }

    @Test
    public void test1() {
        MergeTwoSortedLists21 mergeTwoSortedLists = new MergeTwoSortedLists21();

        int[] nums1_a = {1, 2, 4};
        int[] nums1_b ={1, 3, 4};
        int[] nums1_c = {1, 1, 2, 3, 4, 4};
        ListNode l1_a = ListNode.makeLinkedList(nums1_a);
        ListNode l1_b = ListNode.makeLinkedList(nums1_b);
        ListNode e1 = ListNode.makeLinkedList(nums1_c);

        ListNode res1 = mergeTwoSortedLists.mergeTwoLists1(l1_a, l1_b);
        assert ListNode.isEqual(e1, res1);
    }
}
