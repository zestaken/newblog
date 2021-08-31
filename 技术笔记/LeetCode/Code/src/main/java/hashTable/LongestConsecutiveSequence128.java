package hashTable;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence128 {

    public int longestConsecutive(int[] nums) {

        //创建一个集合
        //使用集合便于直接查找元素,并且利用集合的唯一性去除重复出现的元素
        Set<Integer> hashTable = new HashSet<>();
        //将数组中的值全部存入集合
        for(int i : nums) {
            hashTable.add(i);
        }

        //最长长度
        int longestLength = 0;

        //遍历数组，在集合中找每一个数组元素的前后连续元素，然后将这些元素都删除并记录数量
        for(int num : nums) {
            //当集合已空则停止寻找连续元素
            if(!hashTable.isEmpty()) {
                //临时存储连续元素个数
                int tempRes = 0;
                //向左寻找当前元素的连续元素
                for(int i = 0;  hashTable.contains(num - i); i++) {
                    hashTable.contains(num - i);
                    hashTable.remove(num - i);
                    tempRes++;
                }
                //向右寻找当前元素的连续元素（不包含自身)
                for(int i = 1; hashTable.contains(num + i); i++) {
                    hashTable.contains(num + i);
                    hashTable.remove(num + i);
                    tempRes++;
                }
                //比较长短
                if(tempRes > longestLength) {
                    longestLength = tempRes;
                }
            } else {
                break;
            }
        }
        return longestLength;
    }
}
