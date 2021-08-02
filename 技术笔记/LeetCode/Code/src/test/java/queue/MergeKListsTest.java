package queue;

import org.junit.jupiter.api.Test;

public class MergeKListsTest {

    @Test
    public void test1() {

        MergeKLists23 mergeKLists = new MergeKLists23();

        int[] val1 = {1, 4, 5};
        ListNode list1 = ListNode.makeListNodes(val1);
        int[] val2 = {1, 3, 4};
        ListNode list2 = ListNode.makeListNodes(val2);
        int[] val3 = {2, 6};
        ListNode list3 = ListNode.makeListNodes(val3);

        ListNode[] input1 = {list1, list2, list3};

        ListNode res;
        int[] val4 = {1, 1, 2, 3, 4, 4, 5, 6};
        ListNode expect = ListNode.makeListNodes(val4);

        res = mergeKLists.mergeKLists(input1);

        ListNode cur1 = res;
        ListNode cur2 = expect;
        while(cur1 != null && cur2 != null) {
            assert cur1.val == cur2.val : cur1.val;
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

    }
}
