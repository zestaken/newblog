---
title: LeetCode-数组
date: 2020-09-20 10:01:51
tags: [LeetCode, 数据结构]
categories: 技术笔记
---
# 代码地址
[github](https://github.com/zestaken/newblog/tree/master/%E6%8A%80%E6%9C%AF%E7%AC%94%E8%AE%B0/LeetCode/Code/src/main/java/array)

# 1. 一维数组的动态和 1480

* 题目：
---
给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。

请返回 nums 的动态和。

示例 1：

输入：nums = [1,2,3,4]
输出：[1,3,6,10]
解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。

## Java解法

* 法一：
  * 设置一个新数组，每一项为前面所有项之和；
  * 通过双重循环实现。
```java
public class Sums {

    public static int[] runningSums(int nums[]) {
        int[] sums = new int[nums.length];
        for(int i = 0 ; i < nums.length; i++) {
            for(int j = 0; j <= i; j++) {
                sums[i] += nums[j];
            }
        }
        return sums;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4,5};

        int[] sums = Sums.runningSums(nums);

       for(int i : sums) {
           System.out.println(i);
       }
    }
}
```
* 法二：
  * 动态求和过程中，每一项都是前一项与自身之和。
  * 第一个元素没有前一个元素，对它没有操作，所以直接从第二个元素开始遍历求和。
```java
public class Sums {

    public static int[] runningSums (int[] nums) {

        for(int i = 1; i < nums.length; i++) {
//            if(i == 0) {
//                continue;
//            }因为0是无需操作的，所以直接从1开始就好了
            nums[i] += nums[i-1] ;
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3,4};

        int[] sums = Sums.runningSums(nums);
        for (int i : sums) {
            System.out.println(i);
        }
    }
}
```

# 2. 拥有糖果最多的孩子 1431

* 题目：
---
给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。

对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果。注意，允许有多个孩子同时拥有 最多 的糖果数目。

 

示例 1：

输入：candies = [2,3,5,1,3], extraCandies = 3
输出：[true,true,true,false,true] 
解释：
孩子 1 有 2 个糖果，如果他得到所有额外的糖果（3个），那么他总共有 5 个糖果，他将成为拥有最多糖果的孩子。
孩子 2 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
孩子 3 有 5 个糖果，他已经是拥有最多糖果的孩子。
孩子 4 有 1 个糖果，即使他得到所有额外的糖果，他也只有 4 个糖果，无法成为拥有糖果最多的孩子。
孩子 5 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。

* java解法
---
* 先找到拥有糖果数量最多的孩子，将加上补充的糖果数后的与这个最大比较
```java
public class Candy {
    public static boolean[] isMax(int kids[], int extraCandies) {
        //找出原有最多的数
        int max = 0;
        for(int i = 0; i < kids.length; i++) {
            if(max < kids[i]) {
                max = kids[i];
            }
        }
        //设置判别数组
        boolean[] isMax = new boolean[kids.length];
        for(int i = 0; i < kids.length; i++) {
            if(kids[i] + extraCandies >= max) {
                isMax[i] = true;
            } else{
                isMax[i] = false;
            }
        }
        return isMax;
    }

    public static void main(String[] args) {
        int[] kids = {2, 3, 5, 1, 3};
        int extraCandies = 3;
        boolean[] isMax = Candy.isMax(kids, extraCandies);
        for(boolean i : isMax) {
            System.out.println(i);
        }
    }
}
```

# 3. 重新排列数组 1470

* 题目：
---
1470. 重新排列数组
给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。

请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。

 

示例 1：

输入：nums = [2,5,1,3,4,7], n = 3
输出：[2,3,5,4,1,7] 
解释：由于 x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 ，所以答案为 [2,3,5,4,1,7]

* Java解法：
---
* 法一：设置一个额外的数组，用来将重排后的数据记录；
```java
public class Resort1470 {
    public static int[] resort(int[] nums1){
        int n = nums1.length / 2;
        int[] nums2 = new int[nums1.length];

        for(int i = 0 ,j = 0; j < n;  i++, j++) {
            nums2[i] = nums1[j];
            nums2[++i] = nums1[n + j];
        }
    
        return nums2;
    }

    public static void main(String[] args) {
        int[] nums1 = {2,5,1,3,4,7};
        int[] nums2 = Resort1470.resort(nums1);
        for(int i : nums2) {
            System.out.println(i);
        }
    }
}
```

#  4. 左旋转字符串 剑指offer 58-II

* 题目：
---
字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。

 

示例 1：

输入: s = "abcdefg", k = 2
输出: "cdefgab"

* Java解法：
---
* 将该字符串分为两个子串，然后再反向连接起来
```java
import java.util.Scanner;

public class ReverseLeftWords {

    public String reverseLeftWords(String s, int n) {
         String str1 = s.substring(0, n); //读入从0到n（但是不包括下标为n的）
         String str2 = s.substring(n); //从n开始到最后（包括下标为n的）
         String  s1 = str2 + str1;//反向连接，达到左旋转的效果

         return s1;
// return s.substring(n) + s.substring(o, n);

    }

    public static void main(String[] args) {
        String s = " ";
        int n;
        Scanner in = new Scanner(System.in);

        System.out.println("请输入字符：");
        s = in.nextLine();

        System.out.println("请输入从哪里开始转：");
        n = in.nextInt();

        var reverse = new ReverseLeftWords();
        s = reverse.reverseLeftWords(s, n);

        for(int i = 0; i < s.length(); i++) {
            System.out.print(s.charAt(i) + " ");
        }

    }
}
```
* 数组形式解法：
```java
public class ReverseLeftWords {
    public static char[] reverseLeftWords(char[] nums, int n) {
        char[] nums1 = new char[n];
        char[] nums2 = new char[nums.length];

        for(int i = 0; i < nums.length; i++) {
            if(i < n) {
                nums1[i] = nums[i];
            }else {
                nums2[i - n] = nums[i];
            }
        }
        for(int i = 0; i < n; i++) {
            nums2[nums.length - n + i] = nums1[i];
        }

        return nums2;
    }

    public static void main(String[] args) {
       char[] nums = {'a','b','c','d','e'};

       char[] nums2 = ReverseLeftWords.reverseLeftWords(nums, 2);

       for(char i : nums2) {
           System.out.println(i);
       }
    }
}
```

# 5. 好数对的数目 1512

* 题目
---
给你一个整数数组 nums 。

如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。

返回好数对的数目。

 

示例 1：

输入：nums = [1,2,3,1,1,3]
输出：4
解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始.

* java解法
---
* 法一：双重循环遍历数组，外层为数组这个计数循环，内层取寻找与当前元素相同的元素（但是只找当前位置之后的，避免重复计算）
```java
import java.util.Scanner;

public class NumPairs1512 {

    public static int numIdenticalPairs(int[] nums) {
        int ans = 0;

        for(int i = 0; i < nums.length; i++) {
            for(int j = 1; j < nums.length; j++) { //每次统计相同的元素都是从当前位置向后遍历，这样就不会有重复的情况
                if(nums[i] == nums[i+j]) {
                    ans++;
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3,1,1,3};
        System.out.println(Solution.numIdenticalPairs(nums));
    }
}
```

# 6. 两个数组的交集 II 350

* [题目](https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/)：
---
给定两个数组，编写一个函数来计算它们的交集。

示例 1：
```
输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2,2]
```
示例 2:
```
输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[4,9]
```

说明：

输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
我们可以不考虑输出结果的顺序。
进阶：

如果给定的数组已经排好序呢？你将如何优化你的算法？
如果 nums1 的大小比 nums2 小很多，哪种方法更优？
如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？

## Java题解

* 法一：遍历一个数组，将每个数出现的次数存入哈希表中，之后再遍历另一个数组并查找哈希表
  * 代码：
  ```java
    public class Intersect350 {

    public int[] intersect(int[] nums1, int[] nums2) {
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
  ```
* 法二：先对两个数组进行排序，然后设置两个指针指向数组元素，每次相等的时候两个指针同时移动，不相等的时候较小的移动
  * 结果：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20210714205354.png)
  * 代码：
    ```java
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
    ```
  
# 7. 找到所有数组中消失的数字 448

* [题目](https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/)
--- 
给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
```
示例 1：
输入：nums = [4,3,2,7,8,2,3,1]
输出：[5,6]

示例 2：
输入：nums = [1,1]
输出：[2]
```

提示：

n == nums.length
1 <= n <= 105
1 <= nums[i] <= n

进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。

## java解法

* 法一：排序后双指针扫描确定
    * 结果：
    ![EEorl6](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/EEorl6.png)
    * 代码：
    ```java
    public List<Integer> findDisappearedNumbers(int[] nums) {
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
    ```
* 法二：利用数组脚标可对应连续整数，通过修改原数组指定位置的值来筛选没有出现过的数。不用排序，所以时间复杂度减少
  * 结果：
  ![XfqEaq](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/XfqEaq.png)
  * 代码：
  ```java
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
    ```

# 8. 旋转图像 48
* [题目](https://leetcode-cn.com/problems/rotate-image/)
---
给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
```
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[[7,4,1],[8,5,2],[9,6,3]]

输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]

输入：matrix = [[1]]
输出：[[1]]

输入：matrix = [[1,2],[3,4]]
输出：[[3,1],[4,2]]
```
提示：

matrix.length == n
matrix[i].length == n
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000

## java解法

* 法一：通过一圈一圈找到四个依次换位置的元素，来依次移动这些元素的值，来达到借助一个中间值变量实现原地旋转的效果。关键在于如何确定这些元素的下标的变化规律。
    * 结果：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20210719214209.png)
    * 代码：
```java
public class Rotate48 {
    public void rotate(int[][] matrix) {
        //从外向内一圈圈找，用times表示在第几圈
        int times = 0;
        //nums表示矩阵的列数（行数）
        int nums = matrix.length;

        while(times <= (nums / 2)) { //比如4x4矩阵就是两圈，3x3也是两圈
            //在每一圈中再确定依次交换值的四个元素
            int len = nums - times * 2; //len是圈的范围，每缩小一圈减少两个
            for(int i = 0; i < len - 1; i++) {
                //确定每一圈第一个元素的位置并用临时变量存储其值
                //通过i来将第一个元素右移一位，其它三个元素顺势右移，遇到边界就贴着边界转换方向接着移
                int temp = matrix[times][times + i];
                //将上一个元素的值由其下一位元素的值来替代
                matrix[times][times + i] = matrix[times + len - 1 - i][times];
                matrix[times + len - 1 - i][times] = matrix[times + len - 1][times + len - 1 - i];
                matrix[times + len - 1][times + len - 1 - i] = matrix[times + i][times + len - 1];
                matrix[times + i][times + len - 1] = temp;
                //虽然看起来复杂，但是每一个元素又一个坐标都是不随i的变化而变化，只要关注i对其有影响的坐标，顺势移动即可
            }
            //进入下一圈
            times++;
        }
    }
}
```

# 9. 搜索二维矩阵 II 240

* [题目](https://leetcode-cn.com/problems/search-a-2d-matrix-ii/)
---
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
```
输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
输出：true

输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
输出：false

```
提示：

m == matrix.length
n == matrix[i].length
1 <= n, m <= 300
-109 <= matix[i][j] <= 109
每行的所有元素从左到右升序排列
每列的所有元素从上到下升序排列
-109 <= target <= 109

## Java解法

* 法一：从矩阵右上角开始寻找，直到超出矩阵边界，则目标不存在。当前元素比目标小，则下移，当前元素比目标大则左移。用一个元素的眼光来看，这个元素的左侧的元素的较小，下方的元素比自己小，从递归的观点来看，对每一个元素都这样处理。如果开始从左上角开始，没有选择较小元素的余地，一旦遇见比目标的元素，只能回退
  * 结果：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20210719232725.png)
  * 代码：
```java
    public boolean searchMatrix(int[][] matrix, int target) {
        //从右上角开始搜索
        int lines = matrix.length;
        int rows = matrix[0].length;

        for(int i = 0, j = rows - 1; i < lines && 0 <= j; ) {
            if(matrix[i][j] > target) {
                //比目标大，向左横向移动
                j--;
                if(j < 0) {
                    return false;
                }
            }else if(matrix[i][j] < target) {
                //比目标小，向下移动
                i++;
                if(i >= lines) {
                    return false;
                }
            } else if (matrix[i][j] == target) {
                return true;
            }
        }
        return false;
    }
```

# 10. 最多能完成排序的块 769

* [题目](https://leetcode-cn.com/problems/max-chunks-to-make-sorted/)
---
数组arr是[0, 1, ..., arr.length - 1]的一种排列，我们将这个数组分割成几个“块”，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。

我们最多能将数组分成多少块？
```
示例 1:

输入: arr = [4,3,2,1,0]
输出: 1
解释:
将数组分成2块或者更多块，都无法得到所需的结果。
例如，分成 [4, 3], [2, 1, 0] 的结果是 [3, 4, 0, 1, 2]，这不是有序的数组。
示例 2:

输入: arr = [1,0,2,3,4]
输出: 4
解释:
我们可以把它分成两块，例如 [1, 0], [2, 3, 4]。
然而，分成 [1, 0], [2], [3], [4] 可以得到最多的块数。
```
注意:

arr 的长度在 [1, 10] 之间。
arr[i]是 [0, 1, ..., arr.length - 1]的一种排列。

## Java解法

* 法一：值为连续整数，将值与下标对应，当目前最大值与下标对应时，将数组分为左边一定比目前最大值都小，右边一定比当前最大值都大，可以划分
  * 结果：![ZYfn0h](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/ZYfn0h.png)
  * 代码：
```java
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
```

# 11. 区域和检索 - 数组不可变 303

* [题目](https://leetcode-cn.com/problems/range-sum-query-immutable/)
---
给定一个整数数组 nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
实现 NumArray 类：
```
NumArray(int[] nums) 使用数组 nums 初始化对象
int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
```
示例：
```
输入：
["NumArray", "sumRange", "sumRange", "sumRange"]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
输出：
[null, 1, -1, -3]

解释：
NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1)) 
numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
```
提示：

```
0 <= nums.length <= 104
-105 <= nums[i] <= 105
0 <= i <= j < nums.length
最多调用 104 次 sumRange 方法
```

## Java解法

* 法一：构造一个前缀和数组，存储每个位置对应的前缀之后，以后计算中间的和，只需要使用两个前缀和相减即可。虽然感觉构造一个前缀和数组时间复杂度很大，但是可以一次计算，永久使用。（既然他要用一个数组来构造一个对象，想必也是为了这个目的）注意，为了防止越界而使前缀和数组和源数组向后错一位。
  * 结果：![1293N6](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/1293N6.png)
  * 代码：
```java
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
```

# 12. 二维区域和检索 - 矩阵不可变 304

* [题目](https://leetcode-cn.com/problems/range-sum-query-2d-immutable/)
---
给定一个二维矩阵 matrix，以下类型的多个请求：

计算其子矩形范围内元素的总和，该子矩阵的左上角为` (row1, col1)` ，右下角为 `(row2, col2)` 。
实现 NumMatrix 类：
`NumMatrix(int[][] matrix)` 给定整数矩阵 matrix 进行初始化
`int sumRegion(int row1, int col1, int row2, int col2)` 返回左上角` (row1, col1)` 、右下角 `(row2, col2) `的子矩阵的元素总和。

示例：
![n4dUJm](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/n4dUJm.jpg)
```
输入: 
["NumMatrix","sumRegion","sumRegion","sumRegion"]
[[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
输出: 
[null, 8, 11, 12]

解释:
NumMatrix numMatrix = new NumMatrix([[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
```
提示：
```
m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
-105 <= matrix[i][j] <= 105
0 <= row1 <= row2 < m
0 <= col1 <= col2 < n
最多调用 104 次 sumRegion 方法
```

## Java解法

* 法一：一维前缀和叠加，设置一个二维的前缀和数组，前缀和数组的每一行，都是当行数组的一维前缀和数组。
  * 结果：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20210906220738.png)
  * 代码： 
```java
    private int[][] partialSum;
    public NumMatrix(int[][] matrix) {
        //初始化前缀和数组
        partialSum = new int[matrix.length][matrix[0].length + 1];
        //计算前缀和
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                partialSum[i][j+1] = partialSum[i][j] + matrix[i][j];
            }
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        //逐行扫描，每一行依次用前缀和相减计算中间和
        for(int i = row1; i <= row2; i++) {
            sum += (partialSum[i][col2 + 1] - partialSum[i][col1]);
        }
        return sum;
    }
```
* 法二：二维前缀和（积分图）
  * 结果：
  * 代码：


# 13. 和为K的子数组 560

* [题目](https://leetcode-cn.com/problems/subarray-sum-equals-k/)
---
给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
示例1:
```
输入：nums = [1,1,1], k = 2
输出：2
```
示例2:
```
输入：nums = [1,2,3], k = 3
输出：2
```
提示：
```
1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
```

## Java解法

* 法一：一边计算当前元素的前缀和，一边查找与当前前缀和差值为0的前面的前缀和。将每次计算得到的前缀和存入哈希表中，哈希表的键为前缀和，值为前缀和目前出现的次数。
  * 结果：![sT7AHQ](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/sT7AHQ.png)
  * 代码：
```java
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
```