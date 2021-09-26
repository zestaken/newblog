package tree;

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
