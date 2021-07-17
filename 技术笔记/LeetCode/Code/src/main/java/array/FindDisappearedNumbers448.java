package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindDisappearedNumbers448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> ans = new ArrayList<>();
        int length = nums.length;

        for(int n : nums) {
            int index = (n - 1) % length;//因为会加长度，导致有些位置上的数超过length，所以需要取模
            //出现了的数字对应的位置的值加上长度
            nums[index] += length;
        }

        //再次遍历数组，如果值不大于长度length，则说明该位置脚标对应的值没有出现过
        for(int i = 0; i < length; i++) {
            if(nums[i] <= length) {
                ans.add(i + 1);
            }
        }

        return ans;
    }

    public List<Integer> findDisappearedNumbers1(int[] nums) {
        //用于存储所有数字
        ArrayList<Integer> all = new ArrayList<>();
        //存储消失的数字
        ArrayList<Integer> disappear = new ArrayList<>();
        int length = nums.length;
        //将范围内所有数字存入all集合
        for(int i = 1; i <= length; i++) {
            all.add(i);
        }
        //对数组进行排序后，通过双指针扫描两个数组确定消失的数字
        Arrays.sort(nums);

        //双指针扫描两个数组/集合
        for(int j = 0, i = 0; j < length && i < length; ) {
            //all指针数字大于原数组指针数字，则原数组指针前移，all不变
            if(all.get(j) > nums[i]) {
                i++;
                //判断边界，防止原数组扫描结束后all中剩余元素未被扫描
                if(i >= length) {
                    for(int t = j; t < length; t++) {
                        disappear.add(all.get(t));
                    }
                }
            } else if(all.get(j) == nums[i]) {// 两个指针同时迁移
                j++;
                i++;
                //判断边界，防止原数组扫描结束后all中剩余元素未被扫描（all数组不可能在原数组之前扫描完）
                if(i >= length) {
                    for(int t = j; t < length; t++) {
                        disappear.add(all.get(t));
                    }
                }
            } else if(all.get(j) < nums[i]) { //all指针小于原数组指针数字则前移all指针，原数组不变
                disappear.add(all.get(j));
                j++;
            }
        }

        return disappear;
    }
}
