package linkedList;

import java.util.Stack;

public class PalindromeLinkedList234 {

    public boolean isPalindrome(ListNode head) {

        Stack<Integer> stack = new Stack<>();
        //统计链表结点的数量
        ListNode node = head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        count -= 1;

        //将链表结点数量为奇数和偶数分别处理
        if(count % 2 == 0) {
            //链表结点数量为奇数
            //找到链表的“对称轴”，将前半部分结点的压栈，然后遍历后半部分，与出栈的值比较
            ListNode node1 = head;
            for(int i = 0; i < count / 2; i++) {
                stack.push(node1.val);
                node1 = node1.next;
            }
            //奇数个结点，以中间结点为对称轴，不用比较其值，直接跳过
            node1 = node1.next;
            for(int i = 1 + count / 2; i <= count; i++) {
                int val = stack.pop();
                if(val == node1.val) {
                    node1 = node1.next;
                } else {
                    return false;
                }
            }
        } else {
            //链表结点数量为偶数
            ListNode node2 = head;
            for(int i = 0; i <= count / 2; i++) {
                stack.push(node2.val);
                node2 = node2.next;
            }
            for(int i = 1 + count / 2; i <= count; i++) {
                int val = stack.pop();
                if(val == node2.val) {
                    node2 = node2.next;
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}
