package linkedList;

public class IntersectionOfTwoLinkedLists160 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //如果两个链表头节点就相交
        if(headA == headB) {
            return headA;
        }
        //如果有一个链表为空
        if(headA == null || headB == null) {
            return null;
        }
        ListNode cur1 = headA;
        ListNode cur2 = headB;
        //遍历链表对比节点
        while (cur1 != null) {
            while(cur2 != null) {
                if(cur1 != cur2) {
                    cur2 = cur2.next;
                }else {
                    return cur1;
                }
            }
            //每次都从另一个链表的头节点开始
            cur2 = headB;
            cur1 = cur1.next;
        }
        return null;
    }
}
