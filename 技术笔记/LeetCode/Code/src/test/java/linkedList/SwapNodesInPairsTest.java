package linkedList;

import org.junit.jupiter.api.Test;

public class SwapNodesInPairsTest {

    @Test
    public void test() {
        SwapNodesInPairs24 swapNodesInPairs = new SwapNodesInPairs24();

        int[] nums1_a = {1, 2, 3, 4};
        int[] nums1_b ={2, 1, 4, 3};
        ListNode l1_a = ListNode.makeLinkedList(nums1_a);
        ListNode e1 = ListNode.makeLinkedList(nums1_b);

        ListNode res1 = swapNodesInPairs.swapPairs(l1_a);
        assert ListNode.isEqual(e1, res1);
    }
}
