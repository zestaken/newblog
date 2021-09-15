package linkedList;

public class MergeTwoSortedLists21 {

    /**
     * 非递归解法
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        //设置一个头节点，便于链表的创建
        ListNode head = new ListNode();
        //指向合并链表的当前节点
        ListNode cur = head;
        //指向l1, l2的当前节点
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        if(l1 == null && l2 == null) {
            return null;
        }
        //临时存储两个链表的当前节点的值
        int val1;
        int val2;
        //当两个链表都不为空时
        while(cur1 != null && cur2 != null) {
            val1 = cur1.val;
            val2 = cur2.val;

            if(val1 <= val2) {
                //如果val1不大于val2
                //将l1的当前节点连在合并链表末尾节点后，
                cur.next = cur1;
                //相应移动当前节点指针，l2的当前节点指针不变
                cur = cur1;
                cur1 = cur1.next;
            } else {
                cur.next = cur2;
                cur = cur2;
                cur2 = cur2.next;
            }
        }
        //如果有一个链表提前遍历完了，直接将另一个链表的剩余部分连在合并链表后
        if(cur1 == null && cur2 != null) {
            cur.next = cur2;
        } else if(cur2 == null && cur1 != null) {
            cur.next = cur1;
        }
        //头节点是虚拟的，从它的下一节点开始才有值
        return head.next;
    }

    /**
     * 递归解法
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        //当有链表为空时，直接返回非空链表
        if(l2 == null) {
            return l1;
        }
        if(l1 == null) {
            return l2;
        }
        //当l2的头节点比l1小时，将
        if(l1.val > l2.val) {
            //因为l2的头节点较小，所以将l2之后的节点和l1合并然后将其连在当前头节点之后
            l2.next = mergeTwoLists1(l1, l2.next);
            return l2;
        }
        l1.next = mergeTwoLists1(l2, l1.next);
        return l1;
    }

}
