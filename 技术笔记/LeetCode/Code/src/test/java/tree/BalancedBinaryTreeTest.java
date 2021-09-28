package tree;

import org.junit.jupiter.api.Test;

public class BalancedBinaryTreeTest {

    @Test
    public void test() {
        BalancedBinaryTree110 balancedBinaryTree = new BalancedBinaryTree110();

        int[] input1 = {3,9,20,-1,-1,15,7};
        TreeNode root1 = TreeNode.mkBT(input1);
        boolean expect1 = true;

        boolean res1 = balancedBinaryTree.isBalanced(root1);
        assert res1 == expect1 : res1;
    }
}
