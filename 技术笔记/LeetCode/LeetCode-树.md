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
