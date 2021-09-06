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
            if(hashMap.containsKey(ticket.get(0))) {
                hashMap.get(ticket.get(0)).offer(ticket.get(1));
            } else {
                PriorityQueue<String> temp = new PriorityQueue<>();
                temp.offer(ticket.get(1));
                hashMap.put(ticket.get(0), temp);
            }
        }
        System.out.println("---");
        System.out.println(hashMap.toString());
        System.out.println("----");

        //从"JFK"站开始，依次寻找字典排序最小的下一个站，然后存储到队列中
        String startStop = "JFK";
        String nextStop = new String();
        queue.offer(startStop);
        ArrayList<String> strings = new ArrayList<>();
        while(queue.size() <= tickets.size()) {
            boolean flag = false;
                do {
                        if(hashMap.get(startStop).isEmpty()) {
                            flag = true;
                            break;
                        } else {
                            nextStop = hashMap.get(startStop).poll();
                            strings.add(nextStop);
                        }
                } while (!(hashMap.containsKey(nextStop)));
                if(flag) {
                    hashMap.get(startStop).addAll(strings);
                    for(String string : strings) {
                        queue.offer(string);
                    }
                } else {
                    strings.remove(strings.size() - 1);
                    hashMap.get(startStop).addAll(strings);
                    strings.clear();
                    startStop = nextStop;
                    queue.offer(startStop);
                }
        }

        return (List<String>) queue;
    }

}
