package linkedList;

public class SwapNodesInPairs24 {

    public ListNode swapPairs(ListNode head) {
        //设置一个前导节点，便于连接两组交换的元素
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode cur = head;
        ListNode head1 = head;
        if(head != null && head.next != null) {
            head1 = head.next;
        }
        //遍历整个链表，然后每次处理连续两位
        while (cur != null && cur.next != null) {
                ListNode temp1;
                temp1 =  cur.next;
                pre.next = temp1;
                cur.next = temp1.next;
                temp1.next = cur;
                //每次移动前导节点指针2位
                pre = pre.next.next;
                //每次将当前节点指针移动到前导节点指针之后
                cur = pre.next;
        }
        return head1;
    }

}
