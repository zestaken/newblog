package tree;

public class PathSumIII437 {

    int targetSum;
    int count;
    public int pathSum(TreeNode root, int targetSum) {
        this.targetSum = targetSum;
        this.count = 0;
        getSum(root);
        return count;
    }

    int getSum(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int leftSum = getSum(root.left);
        int rightSum = getSum(root.right);
        if(leftSum == targetSum || rightSum == targetSum
                || leftSum + root.val == targetSum || rightSum + root.val == targetSum) {
            count++;
        }
        return leftSum + rightSum + root.val;
    }
}
