---
title: LeetCode-哈希表
date: 2021-08-31 19:20:59
tags: [LeetCode, 数据结构]
categories: 技术笔记
---

# 代码地址

[Github](https://github.com/zestaken/newblog/tree/master/%E6%8A%80%E6%9C%AF%E7%AC%94%E8%AE%B0/LeetCode/Code/src/main/java/hashTable)

# 1. 两数之和 1

* [题目](https://leetcode-cn.com/problems/two-sum/)
---
给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
你可以按任意顺序返回答案。
```
示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
示例 2：

输入：nums = [3,2,4], target = 6
输出：[1,2]
示例 3：

输入：nums = [3,3], target = 6
输出：[0,1]
 

提示：

2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
只会存在一个有效答案
进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
```

## Java解法

* 法一: 首先遍历一次数组，将数组每个元素的值和下标以键值对的形式存入哈希表中，然后再次遍历数组，将目标值减去当前数组元素值得到一个结果，然后查询这个结果是否在哈希表中存在（注意避免查到自身的情况，如3 + 3 = 6）
  * 结果：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20210831215434.png)
  * 代码：
```java
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
```

# 2. 最长连续序列 128

* [题目](https://leetcode-cn.com/problems/longest-consecutive-sequence/)
---
给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
```
示例 1：

输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
示例 2：

输入：nums = [0,3,7,2,5,8,4,6,0,1]
输出：9
 

提示：

0 <= nums.length <= 105
-109 <= nums[i] <= 109
```

## Java解法

* 法一：用一个哈希集合存储数组元素（方便查找，同时去除重复的元素），然后遍历数组元素，已当前数组元素为中心向前和向后枚举与其连续的整数并在集合中查找，如果找到则在集合中删除该元素并且计数，如果没有找到则中断该方向的查找。每一次查找都能将一堆连续的数找到并清除出集合，在集合被清空时则确认得到了最长的连续整数长度。
  * 结果：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20210831231239.png)
  * 代码：
```java
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
````

# 3. 直线上最多的点数 149

* [题目](https://leetcode-cn.com/problems/max-points-on-a-line/)
---
给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
示例1：
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20210831232314.png)
```
输入：points = [[1,1],[2,2],[3,3]]
输出：3
```
示例2：
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20210831232354.png)
```
输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
输出：4
```
提示：
```
1 <= points.length <= 300
points[i].length == 2
-104 <= xi, yi <= 104
points 中的所有点 互不相同
```

## Java解法

* 法一：因为一条直线有一个点和斜率确定，所以我们遍历数组取出点，然后计算这个点与其它点的斜率，将这个斜率和其出现的次数存入哈希表中，在一个点与其它点的关系计算结束后，取出哈希表中出现次数最多的斜率，然后清空哈希表，进行下一轮的计算。因为一旦与前面的点能构成直线，在前面点计算时已经包含，所以只用计算当前点之后的点（按照在数组中出现的次序而言）。这个解法虽然通过了，但是有缺陷：如果计算的斜率超出了double的小数范围，就有可能不准确。
    * 结果：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20210901134149.png)
    * 代码：
```java
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

        //遍历点数组，计算之后的点与自己构成直线的次数（因为一旦与前面的点能构成直线，在前面点计算时已经包含，所以只用计算当前点之后的点）
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
```


