package queue;

import java.util.PriorityQueue;

public class MergeKLists23 {
    /**
     * 创建一个实现Comparable接口的ListNode，作为优先队列的元素
     * 使用优先队列PriorityQueue需要实现Comparable接口
     */
    class Status implements Comparable<Status>{
        int val;
        ListNode node;

        Status(int val, ListNode node) {
            this.val = val;
            this.node = node;
        }

        /**
         * 新添加进优先队列的元素通过这个方法获得优先级，较小的元素优先级高放在队列前面
         * @param status2
         * @return
         */
        @Override
        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {

        //创建以Status作为元素的优先队列
        PriorityQueue<Status> queue = new PriorityQueue<>();

        //首先将每个链表的头节点放入优先队列中
        for(ListNode node : lists) {
            if(node != null) {
                queue.offer(new Status(node.val, node));
            }
        }

        //创建一个不存值的头节点便于返回结果
        ListNode head = new ListNode();
        //使用tail指针便于尾插法
        ListNode tail = head;

        //逐步弹出优先队列中元素，直到队列为空
        while(!queue.isEmpty()) {
            //弹出队列头部节点并插入结果链表尾部
            Status temp = queue.poll();
            tail.next = temp.node;
            tail = temp.node;
            //将当前被弹出节点的下一节点加入优先队列中
            if(temp.node.next != null) {
                queue.offer(new Status(temp.node.next.val, temp.node.next));
            }
        }
        tail.next = null;

        return head.next;
    }

}
