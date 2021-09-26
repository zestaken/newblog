package tree;

import java.util.ArrayList;

/**
 * 树节点的定义
 */
public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;

     TreeNode() {}

     TreeNode(int val) { this.val = val; }

     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
   }

    /**
     * 输入值的数组，生成二叉树（null用-1替代）
     * @param nums
     * @return
     */
    public static TreeNode mkBT(int[] nums) {
         if(nums.length == 0) {
             return null;
         }

        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        for(int i : nums) {
            if(i == -1) {
                treeNodes.add(new TreeNode());
            } else {
                treeNodes.add(new TreeNode(nums[i]));
            }
         }

         for(int i = 0; i < nums.length / 2; i++) {
             TreeNode cur = treeNodes.get(i);
             cur.left = treeNodes.get(2 * (i + 1) - 1);
             cur.right = treeNodes.get(2 * (i + 1) + 1 - 1);
         }

         return treeNodes.get(0);
    }
}
