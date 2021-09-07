package array;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK560 {

    public int subarraySum(int[] nums, int k) {
        //存储子数组计数结果
        int res = 0;
        //创建前缀和数组
        Map<Integer, Integer> hashPartialSums = new HashMap<>();
        //添加一个0的和，保证从0到当前位置的连续数组和为k能被统计
        hashPartialSums.put(0, 1);
        //存储当前前缀和
        int partialSum = 0;
        //初始化前缀和数组
        for(int i = 0; i < nums.length; i++) {
            //计算当前前缀和
            partialSum = partialSum + nums[i];
            //将当前前缀和及其所出现的次数存入哈希表中
            int count = hashPartialSums.getOrDefault(partialSum, 0);
            //查看哈希表中是否存在与当前前缀和差为k的值（这个值肯定是由这个数组前面的元素产生的，只用考虑差值为k），如果有则记录次数
            if(hashPartialSums.containsKey(partialSum - k)) {
                res += hashPartialSums.get(partialSum - k);
            }
            //当前前缀和次数加一，之所以在检索之后再增加计数
            // 是因为计算差值不能将自己与自己的差值算上，次数不加，说明只查找当前元素之前
            count++;
            hashPartialSums.put(partialSum, count);
        }

        return res;
    }
}
