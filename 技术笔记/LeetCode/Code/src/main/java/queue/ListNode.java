package queue;

public class ListNode {

    int val;
    ListNode next;

    /**
     * 无参构造器
     */
    ListNode() {};

    /**
     * 节点值构造器
     * @param val
     */
    ListNode(int val) {
        this.val = val;
    }

    /**
     * 节点构造器
     * @param val
     * @param next
     */
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * 根据输入int值构造链表
     * @param vals
     * @return
     */
    public static ListNode makeListNodes(int[] vals) {
        ListNode head = new ListNode();
        ListNode tail = head;

        for(int i : vals) {
            ListNode node = new ListNode(i);
            tail.next = node;
            tail = node;
        }

        return head.next;
    }
}
