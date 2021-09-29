package tree;

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
