---
title: LeetCode-树
date: 2021-09-26 10:20:59
tags: [LeetCode, 数据结构]
categories: 技术笔记
---

# 1. 二叉树的最大深度 104

* [题目](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/)
---
给定一个二叉树，找出其最大深度。
二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
说明: 叶子节点是指没有子节点的节点。
示例：
给定二叉树 `[3,9,20,null,null,15,7]`
![hKwQG4](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/hKwQG4.png)
返回它的最大深度 3 。

## Java解法

* 法一：递归进行深度优先搜索：将一一颗二叉树划为三个部分：根节点，左子树，右子树，子树的高度加上根节点的高度（1）就是整颗二叉树的高度。
  * 结果：![Su4uv1](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/Su4uv1.png)
  * 代码：
```java
public class MaximumDepthOfBinaryTree104 {

    public int maxDepth(TreeNode root) {
        //利用递归的思想来求解
        //将一一颗二叉树划为三个部分：根节点，左子树，右子树，子树的高度加上根节点的高度（1）就是整颗二叉树的高度
        //实质是深度优先搜索
        if(root != null) {
            //注意要加上代表根节点的1
            int depth = 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
            return depth;
        } else {
            //当当前二叉树的根节点为null时返回0，也是递归到底返回的条件
            return 0;
        }
    }
}

//根据一个数组构造二叉树的方法
    /**
     * 输入值的数组，生成二叉树（null用-1替代）
     * @param nums
     * @return
     */
    public static TreeNode mkBT(int[] nums) {
        //当数组长度为0时，直接返回空
         if(nums.length == 0) {
             return null;
         }
        //先根据数组依次生成节点，存储到集合中
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        for(int i : nums) {
            if(i == -1) {
                treeNodes.add(null);
            } else {
                treeNodes.add(new TreeNode(i));
            }
         }
        //遍历前一半的节点（因为只有这些节点不是叶子节点）
        // 根据子节点与根节点的下标对应关系，找到每一个非叶子节点的左右子节点并连接
         for(int i = 0; i < nums.length / 2; i++) {
             TreeNode cur = treeNodes.get(i);
             cur.left = treeNodes.get(2 * (i + 1) - 1);
             cur.right = treeNodes.get(2 * (i + 1) + 1 - 1);
         }
        //返回总的根节点（就是集合中的第一个节点）
         return treeNodes.get(0);
    }
```

# 2. 平衡二叉树 110

* [题目](https://leetcode-cn.com/problems/balanced-binary-tree/)
---
给定一个二叉树，判断它是否是高度平衡的二叉树。
本题中，一棵高度平衡二叉树定义为：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
示例 1：
![IGtDFV](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/IGtDFV.jpg)
```
输入：root = [3,9,20,null,null,15,7]
输出：true
```
示例2:
![JRslxu](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/JRslxu.jpg)
```
输入：root = [1,2,2,3,3,null,null,4,4]
输出：false
```
示例3:
```
输入：root = []
输出：true
```
提示：
 树中的节点数在范围 [0, 5000] 内
 -104 <= Node.val <= 104

## Java解法

* 法一：递归获取左右子树的高度，并比较，如果高度差不大于1，则正常返回左右子树中最大高度，否则返回-1。
  * 结果：![V0Yg9H](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/V0Yg9H.png)
  * 代码：
```java
public class BalancedBinaryTree110 {

    public boolean isBalanced(TreeNode root) {
        if (getDepth(root) != -1) {
            return true;
        }
        return false;
    }

    /**
     * 递归获取左右子树的高度并比较，如果高度差大于1，则返回-1
     * @param root
     * @return
     */
    int getDepth(TreeNode root) {
        //当root为null，说明已遍历到底部，返回0
        if(root == null) {
            return 0;
        }
        //获取左右子树的高度，并比较
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        //如果左右子树高度返回为-1，说明左右子树内部高度差超过了1
        if(left == -1 || right == -1 ||Math.abs(left - right) > 1) {
            return -1;
        }
        //如果正常，返回左右子树中的最大高度
        return 1 + Math.max(left, right);
    }
}
```

# 3. 二叉树的直径 543

* [题目](https://leetcode-cn.com/problems/diameter-of-binary-tree/)
---
示例 :
给定二叉树
![JuYUaG](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/JuYUaG.png)
返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
注意：两结点之间的路径长度是以它们之间边的数目表示。

## Java解法

* 法一：递归获取左右子树的节点数，每层相对根节点都根据左右子树节点数计算直径，最后选择最大的直径。
  * 结果：![lGTZ2E](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/lGTZ2E.png)
  * 代码：
```java
public class DiameterOfBinaryTree543 {

    //记录直径值，设为类属性便于在方法间传递
    int diameter;

    public int diameterOfBinaryTree(TreeNode root) {
        //将初始直径设置为0
        diameter = 0;
        //获取二叉树的深度的同时修改最长直径值
        getDepth(root);
        return diameter;
    }

    /**
     * 递归获取二叉树的深度的同时修改最长直径值
     * @param root
     * @return
     */
    int getDepth(TreeNode root) {
        //当当前根节点为null，说明已经递归到了叶子节点，触底返回
        if(root == null) {
            return 0;
        }
        //获取左右子树的节点数（不加一是除去了根节点，以防计算直径的时候重复计算）
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        //当前节点为根节点时的最长直径是左右子树的节点数相加
        //每次比较当前节点下的最长直径与前面直径，取最大值（因为最长直径必然经过某个相对根节点）
        diameter = Math.max(left + right, diameter);
        //返回左右子树的深度（加一算上当前根节点）
        return Math.max(left, right) + 1;
    }
}
```
