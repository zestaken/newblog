package array;

public class MaxChunksToSorted769 {

    //连续整数的一个排列，用下标与值对应
    public int maxChunksToSorted(int[] arr) {
        int max = arr[0];
        int count = 0;
        int length = arr.length;
        for(int i = 0; i < length; i++) {
            if(arr[i] >= max) {
                max = arr[i];
            }
            //每当最大值与下标相等则说明前面的数据全是比最大值小的而之后全是比最大值大的
            //如果只能分为一块，说明最大值在最前面出现，最后总能遇到自己的下标，而使count加一
            if(max == i) {
                count++;
            }
        }
        return count;
    }
    //不对
    public int maxChunksToSorted1(int[] arr) {
        int count = 1;
        int max = arr[0];
        int i = 0;
        while(i < arr.length) {
            if(max >= arr[i]) {
                i++;
            }else if(max < arr[i]) {
                max = arr[i];
                count++;
                i++;
            }
        }
        return count;
    }
}
