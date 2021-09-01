package hashTable;

import java.util.HashMap;
import java.util.Map;


public class MaxPointsOnALine149 {

    public int maxPoints(int[][] points) {

        //如果少于三个点则无需计算
        if(points.length < 2) {
            return points.length;
        }
        //创建哈希表，键为斜率，值为斜率出现的次数
        Map<Double, Integer> hashMap = new HashMap<>();
        //记录构成直线的最多点数
        int res = 0;

        //遍历点数组，计算之后的点与自己构成直线的次数（因为一旦与前面的点能构成直线，再前面点计算时已经包含，所以只用计算当前点之后的点）
        for(int i = 0; i < points.length; i++) {
                //斜率不存在的情况单独存储
                int sameX = 1;
                //遍历当前点之后的数组，将与当前点的斜率以及出现次数存入哈希表 
                for(int j = i + 1; j < points.length; j++) {
                    //当斜率存在时才计算
                    if((points[i][0] - points[j][0]) != 0) {
                        //计算斜率
                        double k = ((double)(points[i][1] - points[j][1]))/((double)(points[i][0] - points[j][0]));
                        //因为哈希表判断键时将0 和 -0看作两个不同的数，所以需要处理一下
                        if(k == -0) {
                            k = 0;
                        }
                        //查询哈希表中是否存在该斜率，如果存在则取出，如果不存在则初始为1（因为要包含自身，一旦构成直线就是两个点）
                        int count = hashMap.getOrDefault(k, 1);
                        //将该斜率的计数加一再存入哈希表中
                        count++;
                        hashMap.remove(k);
                        hashMap.put(k, count);
                    } else {
                        sameX++;
                    }
                }
                //获取经过当前点构成直线的最多点数
                int temp = 0;
                for(int count : hashMap.values()) {
                    if(count > temp) {
                        temp = count;
                    }
                }
                if(temp < sameX) {
                    temp = sameX;
                }
                //如果经过当前点的构成直线的最多点数比以及统计的要多则赋给最后的结果
                if(temp > res) {
                    res = temp;
                }
                //清空当前点构成的哈希表，进入下一个点的统计
                hashMap.clear();
        }

        return res;
    }
}
