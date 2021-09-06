package array;

import java.lang.reflect.Array;
import java.util.ArrayList;

//303
public class NumArray {

    private int[] partialSum;
    public NumArray(int[] nums) {
        partialSum = new int[nums.length];
        //构造前缀和数组
        for(int i = 0; i < nums.length;i++) {
            int sum = 0;
            for( int j = i; j >= 0; j--) {
                sum += nums[j];
            }
            partialSum[i] = sum;
        }
    }

    public int sumRange(int left, int right) {
        //左边为0 ，直接就是第right个前缀和
        if(left == 0) {
            return partialSum[right];
        }
        //两边相减得到中间的和
        return partialSum[right] - partialSum[left - 1];
    }
}
