package tree;

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
