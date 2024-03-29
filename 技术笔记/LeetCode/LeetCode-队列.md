---
title: LeetCode-队列
date: 2021-07-31 11:20:59
tags: [LeetCode, 数据结构]
categories: 技术笔记
---
# 代码地址
[github](https://github.com/zestaken/newblog/tree/master/%E6%8A%80%E6%9C%AF%E7%AC%94%E8%AE%B0/LeetCode/Code/src/main/java/queue)

# 1. 合并k个升序链表 23

* [题目](https://leetcode-cn.com/problems/merge-k-sorted-lists/)
---
给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。

 

示例 1：
```
输入：lists = [[1,4,5],[1,3,4],[2,6]]
输出：[1,1,2,3,4,4,5,6]
解释：链表数组如下：
[
  1->4->5,
  1->3->4,
  2->6
]
将它们合并到一个有序链表中得到。
1->1->2->3->4->4->5->6
示例 2：

输入：lists = []
输出：[]
示例 3：

输入：lists = [[]]
输出：[]
```

提示：

```
k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] 按 升序 排列
lists[i].length 的总和不超过 10^4
```

## Java解法

* 法一：使用[优先队列](https://www.apiref.com/java11-zh/java.base/java/util/PriorityQueue.html)，开始将各个链表的头节点加入优先队列中，优先队列会自动按值大小顺序添加到队列中，之后弹出队列元素，因为链表自身是有序的，弹出的一定是当前最小的元素，之后将弹出元素的下一节点添加到优先队列中，则又会自动排序，直到队列为空时，所有链表的元素都已经添加完了。
  * 结果：![KM82lW](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/uPic/KM82lW.png)
  * 代码：
```java
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
```

# 2. 天际线问题 218

* [题目](https://leetcode-cn.com/problems/the-skyline-problem/)
---
城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。

每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：

lefti 是第 i 座建筑物左边缘的 x 坐标。
righti 是第 i 座建筑物右边缘的 x 坐标。
heighti 是第 i 座建筑物的高度。
天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。

注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]


示例1:
![AIl1gT](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/uPic/AIl1gT.jpg)
```
输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
解释：
图 A 显示输入的所有建筑物的位置和高度，
图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。
示例 2：

输入：buildings = [[0,2,3],[2,5,3]]
输出：[[0,3],[5,0]]
 

提示：

1 <= buildings.length <= 104
0 <= lefti < righti <= 231 - 1
1 <= heighti <= 231 - 1
buildings 按 lefti 非递减排序
```

## Java解法

* 法一：首先先要观察出关键点出现的规律：1 关键点的横坐标必然在建筑物的左右边界上 2 处于建筑物左边界上的关键点的高度就是该建筑的高度，右边界上的则不然，得出建筑包含关键点的概念：大于等于建筑左坐标，小于建筑右坐标；之后使用**扫描线法**，逐个遍历所有的建筑的边界横坐标，并找出包含该边界的建筑物，将该建筑物信息存储。然后在所有包含该边界的建筑物中（遍历）选出最高的高度就是关键点的坐标。在基本思路清晰后，使用**优先队列**减少遍历包含边界的建筑物信息得出最大高度的过程：以高度为比较变量，高度最高的放在队列最前，每次只要确保队列最前的建筑包含边界即可使用其高度作为关键点高度（如果不包含，说明边界已经超过了该建筑所在区域，因为边界是从左到右遍历的，所以该建筑在确定关键点上已经失效（之后的边界它必然也不会包含），可以从队列中移除）。最后要注意每次得到新的关键点时都要确定是否**与以前的关键点高度相同**，如果相同则丢弃这个关键点。
  * 结果：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20210830084011.png)
  * 代码：
```java
public class SkyLine218 {

    public List<List<Integer>> getSkyline(int[][] buildings) {
        //创建优先队列，
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<int[]>((a, b)->b[1] - a[1]);

        //存储建筑物的边界的集合(建筑物的左右横坐标)
        List<Integer> boundaries = new ArrayList<>();
        for(int[] building : buildings) {
            boundaries.add(building[0]);
            boundaries.add(building[1]);
        }
        //对边界进行排序(从小到大)
        Collections.sort(boundaries);

        //存储结果天际线的集合
        List<List<Integer>> result = new ArrayList<>();

        int n = buildings.length;
        int index = 0;
        //遍历边界一个个地找到天际线关键点
        for(int boundary : boundaries) {
            //从左到右遍历并找到包含该边界的建筑物，并添加到优先队列中去
            //已经添加了的建筑不会再遍历
            while(index < n && buildings[index][0] <= boundary) {
                //将建筑物的右边界和高度整体存入优先队列中
                priorityQueue.offer(new int[] {buildings[index][1], buildings[index][2]});
                index++;
            }
            //确保优先队列首部的元素包含该边界（即右横坐标大于边界坐标）
            while(!priorityQueue.isEmpty() && priorityQueue.peek()[0] <= boundary) {
                priorityQueue.poll();
            }

            //获取优先队列首部的元素（建筑物）的高度，即最大高度（同时这个高度对应的建筑包含该边界）
            int maxHeight = priorityQueue.isEmpty() ? 0 : priorityQueue.peek()[1];
            //如果这个高度没有与前面的高度相同，则和边界一起存入结果集合中
            if(result.size() == 0 || maxHeight != result.get(result.size() - 1).get(1)) {
                result.add(Arrays.asList(boundary, maxHeight));
            }
        }
        return result;
    }
}
```

# 3. 滑动窗口最大值 239

*[题目](https://leetcode-cn.com/problems/sliding-window-maximum/)
---
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。

```
示例 1：

输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
示例 2：

输入：nums = [1], k = 1
输出：[1]
示例 3：

输入：nums = [1,-1], k = 1
输出：[1,-1]
示例 4：

输入：nums = [9,11], k = 2
输出：[11]
示例 5：

输入：nums = [4,-2], k = 2
输出：[4]

提示：

1 <= nums.length <= 105
-104 <= nums[i] <= 104
1 <= k <= nums.length
```

## Java解法

* 法一：使用双端队列存储窗口内容，每次移动窗口就是将双端队列的最前面去掉在尾部加入新元素。将双端队列中的新元素加入优先队列中去，并且记录每个元素对应的下标。每次移动窗口后，获取优先队列的顶部元素，并根据下标判断他是否是窗口中的元素，如果不是则弹出，再取出新的顶部元素，直到属于窗口中为止，这个顶部元素就是当前窗口最大的元素，将其加入结果数组中。
    * 结果：![M6GE8z](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/uPic/M6GE8z.png)
    * 代码：
```java
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
        //先计算出队列中的最大值
        for(int j = 0; j < k; j++) {
            int temp = (int) deque.pollLast();
            priorityQueue.offer(new int[]{temp, k - j - 1});
            //用栈来保存，以便复原双端队列
            stack.push(temp);
        }
        //复原双端队列
        while (!stack.isEmpty()) {
            int temp = (int) stack.pop();
            deque.offerLast(temp);
        }

        for(int i = 0; k + i <= length; i++) {

            //获取在当前窗口的最大值
            boolean flag = false; //是否获取到当前窗口最大值的标志
            while(!flag) {
                if(i <= priorityQueue.peek()[1] && priorityQueue.peek()[1]  < k + i) {
                    res[i] = priorityQueue.peek()[0];
                    flag = true;
                } else {
                    priorityQueue.poll();
                }
            }
            //移动窗口
            if(k + i < length) {
                deque.pollFirst();
                deque.offerLast(nums[k + i]);
            }
            //将最新加入窗口的元素加入优先队列
            int temp = (int) deque.getLast();
            priorityQueue.offer(new int[]{temp, k + i});
        }

        return res;
    }
}
```
