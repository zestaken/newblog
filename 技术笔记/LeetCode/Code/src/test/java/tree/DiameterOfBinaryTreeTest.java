package tree;

import org.junit.jupiter.api.Test;

public class DiameterOfBinaryTreeTest {

    @Test
    public void test() {

        DiameterOfBinaryTree543 diameterOfBinaryTree = new DiameterOfBinaryTree543();

        int[] input1 = {1, 2, 3, 4, 5, -1, -1};
        TreeNode root1 = TreeNode.mkBT(input1);
        int expect1 = 3;

        int res1 = diameterOfBinaryTree.diameterOfBinaryTree(root1);
        assert res1 == expect1 : res1;
    }
}
