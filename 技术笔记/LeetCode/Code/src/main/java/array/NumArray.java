package array;

import java.lang.reflect.Array;
import java.util.ArrayList;

//303
public class NumArray {

    private int[] partialSum;
    public NumArray(int[] nums) {
        //初始默认值为0
        partialSum = new int[nums.length + 1];
        //构造前缀和数组
        for(int i = 0; i < nums.length;i++) {
            //向后推一位存储
            partialSum[i + 1] = partialSum[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        //两边相减得到中间的和，因为前缀和数组向后推了一位，所以下标也要向后推一位
        //正常是right - (left - 1)
        //向后推一位的目的是防止计算第一位的前缀时超出边界
        return partialSum[right + 1] - partialSum[left];
    }
}
