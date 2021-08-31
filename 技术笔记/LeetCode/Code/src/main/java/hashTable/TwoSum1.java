package hashTable;

import java.util.HashMap;
import java.util.Map;

public class TwoSum1 {

    public int[] twoSum(int[] nums, int target) {

        //创建哈希表，键为数组元素值，值为数组元素下标
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();

        //将数组元素的值和下标存入哈希表中
        for(int i = 0; i < nums.length; i++) {
            hashtable.put(nums[i], i);
        }

        //存储结果的数组
        int[] res = new int[2];
        //遍历数组，将目标值减去数组元素值得到结果，然后查找哈希表中是否存在这个结果
        for(int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            //当在哈希表中查找到不是自身的数时，确认结果返回
            if(hashtable.containsKey(temp) && hashtable.get(temp) != i) {
                res[0] = i;
                res[1] = hashtable.get(temp);
                return res;
            }
        }

        return res;
    }
}
