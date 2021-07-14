package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Intersect350 {

    //对数组排序后，双指针遍历
    public int[] intersect(int[] nums1, int[] nums2) {
        //先排序（升序）
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        //存储结果数组
        int[] res = new int[(nums1.length > nums2.length)?nums2.length:nums1.length];
        int count1 = 0;
        int count2 = 0;
        int index = 0;
        while (true) {
            if(nums1[count1] == nums2[count2]) {
                //如果相等则双指针均前移
                res[index] = nums1[count1];
                index++;
                count1++;
                count2++;
            } else if(nums1[count1] < nums2[count2]) {
                count1++;
            } else {
                count2++;
            }
            if(count1 >= nums1.length || count2 >= nums2.length) {
                break;
            }
        }

        return Arrays.copyOfRange(res, 0, index);
    }

    //哈希表存储元素及其数量
    public int[] intersect2(int[] nums1, int[] nums2) {
        //用哈希表存次数
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        //遍历一个数组，获取每个元素值以及出现次数
        for(int i : nums1) {
            if(!map.containsKey(i)) {
                //初始计数为1
                map.put(i, 1);
            } else {
                int count = map.get(i);
                //第二次出现计数加一
                count++;
                map.put(i, count);
            }
        }

        int[] res = new int[(nums1.length > nums2.length)?nums2.length:nums1.length];
        int index = 0;
        for(int i : nums2) {
            if(map.containsKey(i)) {
                int count = map.get(i);
                //每出现一次计数减一，只在计数大于0时存入结果中，以保证出现次数与最少的数组相同
                count--;
                map.put(i, count);
                if(count >= 0) {
                    res[index] = i;
                    index++;
                }
            }
        }
        return Arrays.copyOfRange(res, 0, index);
    }

    //错误解法，没有考虑元素的次数问题
    public int[] intersect1(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        ArrayList<Integer> res1 = new ArrayList<Integer>();
        int count = 0;
        if (length1 >= length2) {
            for(int i : nums2) {
                for(int j : nums1) {
                    if(i == j) {
                        res1.add(i);
                        count++;
                        break;
                    }
                }
            }
        } else if(length2 < length1){
            for(int i : nums1) {
                for(int j : nums2) {
                    if(i == j) {
                        res1.add(i);
                        count++;
                        break;
                    }
                }
            }
        }

        int[] res2 = new int[count];
        for(int i = 0; i < res2.length; i++) {
            res2[i] = res1.get(i);
        }
        return res2;
    }
}
