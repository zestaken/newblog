package hashTable;

import java.util.HashMap;
import java.util.Map;


public class MaxPointsOnALine149 {
    public int maxPoints(int[][] points) {
        //
        Map<Double, Integer> hashMap = new HashMap<>();
        //遍历数组，将点的斜率存入集合
        for(int[] i : points) {
            double k = (double) i[0]/(double) i[1];
            if(hashMap.containsKey(k)) {
                int count = hashMap.get(k);
                count++;

            }
        }

        int res = 0;

        for(int[] i : points) {
            double k = (double) i[0]/(double) i[1];
            while(hashMap.containsKey(k)) {
                if(!i.equals(hashMap.get(k))) {
                    int[] temp = hashMap.get(k);
                    System.out.println(temp[0]);
                    hashMap.remove(k, temp);
                    res++;
                }
            }
        }

        return res;
    }
}
