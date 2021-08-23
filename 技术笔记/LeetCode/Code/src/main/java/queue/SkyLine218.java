package queue;

import java.util.*;

public class SkyLine218 {

    public List<List<Integer>> getSkyline(int[][] buildings) {
        //创建优先队列，
        //todo lamda表达式的应用
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
                //todo 将两个数直接组成集合对象
                result.add(Arrays.asList(boundary, maxHeight));
            }
        }
        return result;
    }
}
