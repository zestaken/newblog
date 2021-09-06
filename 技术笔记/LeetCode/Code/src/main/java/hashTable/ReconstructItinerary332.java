package hashTable;

import java.util.*;

public class ReconstructItinerary332 {

    public List<String> findItinerary(List<List<String>> tickets) {
        //创建哈希集合，键是起始站，值是这个起始站能到达的终点站的集合(使用优先队列，自动返回字典排序最小的值）
        Map<String, PriorityQueue<String>> hashMap = new HashMap<>();
        //临时存储产生站的顺序的队列
        Queue<String> queue = new LinkedList<>();
        //遍历票集合，初始化哈希集合
        for(List<String> ticket : tickets) {
            hashMap.getOrDefault(ticket.get(0), new PriorityQueue<String>()).offer(ticket.get(1));
        }
        //从"JFK"站开始，一次寻找字典排序最小的下一个站，然后存储到队列中
        String firstStop = "JFK";
        String startStop = new String();
        for (int i = 0; queue.size() > tickets.size();) {
            System.out.println(startStop);
            if(i == 0) {
                queue.offer(firstStop);
                startStop = new String(hashMap.get(firstStop).poll());
                queue.offer(startStop);
                i++;
            } else {
                startStop = hashMap.get(startStop).poll();
                queue.offer(startStop);
            }
        }
        for(String res : queue) {
            System.out.println(res);
        }
        List<String> res = (List<String>) queue;
        return res;
    }

}
