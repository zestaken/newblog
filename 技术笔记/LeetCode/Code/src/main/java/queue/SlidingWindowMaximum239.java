package queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class SlidingWindowMaximum239 {

    public int[] maxSlidingWindow(int[] nums, int k) {

        //创建存储结果的数组
        int length  = nums.length;
        int[] res = new int[length - k + 1];
        //创建双端队列
        Deque deque = new LinkedList();
        //初始化队列，将数组最前面的k位数装入队列
        for(int i = 0; i < k; i++) {
            deque.offerLast(nums[i]);
        }
        //用于比较的栈
        Stack stack = new Stack();
        //用优先队列来比较大小,优先队列元素：{num, index},数以及它对应的下标来感知最大值是否已经超出窗口
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<int[]>((a, b)->b[0] - a[0]);
        for(int i = 0; k + i <= length; i++) {
            //先计算出队列中的最大值
            for(int j = 0; j < k; j++) {
                int temp = (int) deque.pollLast();
                priorityQueue.offer(new int[]{temp, k + i - j});
                //用栈来保存，以便复原双端队列
                stack.push(temp);
            }
            //获取在当前窗口的最大值
            boolean flag = false; //是否获取到当前窗口最大值的标志
            while(!flag) {
                if(i <= priorityQueue.peek()[1] && priorityQueue.peek()[1]  <= k + i) {
                    res[i] = priorityQueue.peek()[0];
                    flag = true;
                } else {
                    priorityQueue.poll();
                }
            }
            //复原双端队列
            while (!stack.isEmpty()) {
                int temp = (int) stack.pop();
                deque.offerFirst(temp);
            }
            //移动窗口
            if(k + i < length) {
                deque.pollLast();
                deque.offerLast(nums[k + i]);
            }
        }

        return res;
    }
}
