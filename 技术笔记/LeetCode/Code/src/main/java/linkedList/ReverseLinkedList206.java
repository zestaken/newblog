package linkedList;

public class ReverseLinkedList206 {

    /**
     * 递归解法
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        //当当前节点或其下一节点为null时返回
        if(head == null || head.next == null) {
            return head;
        }
        //逆向考虑，每次调用本方法说明当前节点的前面节点都已经完成了反转
        //现在只需将当前节点和前面反转好的链表反向连接好就行
        ListNode nextHead = reverseList1(head.next);
        head.next.next = head;
        //因为是逆向思考，所以反转后当前节点的下一节点始终为null，前面的情况并不清楚
        head.next = null;
        return nextHead;

    }

    /**
     * 迭代解法
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        //定义前一节点和后一节点
        ListNode pre = null; // pre开始为null，因为head之前没有节点
        ListNode next;
        while(head != null) {
            //记录当前节点的下一节点
            next = head.next;
            //当前节点的下一节点变为pre
            head.next = pre;
            //同时移动当前节点和对应的前一节点到下一节点
            pre = head;
            head = next;
        }
        //最后返回pre
        //因为，head此时指向空，pre是其前一位正好指向原链表的最后一节点，即新链表的头节点
        return pre;
    }
}
