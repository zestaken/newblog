package tree;

import org.junit.jupiter.api.Test;

public class DiameterOfBinaryTreeTest {

    @Test
    public void test() {

        DiameterOfBinaryTree543 diameterOfBinaryTree = new DiameterOfBinaryTree543();

        int[] input1 = {1, 2, 3, 4, 5, -1, -1};
        TreeNode root1 = TreeNode.mkBT(input1);
        int expect1 = 3;

        int[] input2 = {1, 2};
        TreeNode root2 = TreeNode.mkBT(input2);
        int expect2 = 1;

        int res1 = diameterOfBinaryTree.diameterOfBinaryTree(root1);
        assert res1 == expect1 : res1;

        int res2 = diameterOfBinaryTree.diameterOfBinaryTree(root2);
        assert res2 == expect2 : res2; 
    }
}
