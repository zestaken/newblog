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
             if(2 * (i + 1) - 1 < nums.length) {
                 cur.left = treeNodes.get(2 * (i + 1) - 1);
             }
             if(2 * (i + 1) + 1 - 1 < nums.length) {
                 cur.right = treeNodes.get(2 * (i + 1) + 1 - 1);
             }
         }
        //返回总的根节点（就是集合中的第一个节点）
         return treeNodes.get(0);
    }
}
