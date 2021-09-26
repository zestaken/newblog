package tree;

import org.junit.jupiter.api.Test;

public class MaximumDepthOfBinaryTreeTest {

    @Test
    public void test() {
        MaximumDepthOfBinaryTree104 maximumDepthOfBinaryTree = new MaximumDepthOfBinaryTree104();

        int[] input1 = {3, 9, 20, -1, -1, 15, 7};
        TreeNode root1 = TreeNode.mkBT(input1);
        int expect1 = 3;

        int res1 = maximumDepthOfBinaryTree.maxDepth(root1);
        assert res1 == expect1 : res1;
    }
}
