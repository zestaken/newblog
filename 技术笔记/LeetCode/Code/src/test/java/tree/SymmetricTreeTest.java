package tree;


import org.junit.jupiter.api.Test;

public class SymmetricTreeTest {

    @Test
    public void test() {
        SymmetricTree101 symmetricTree101 = new SymmetricTree101();

        int[] input1 = {1,2,2,3,4,4,3};
        boolean exp1 = true;
        TreeNode root1 = TreeNode.mkBT(input1);

        int[] input2 = {1,2,2,-1,3,-1,3};
        boolean exp2 = false;
        TreeNode root2 = TreeNode.mkBT(input2);

        int[] input3 = {1,2,3};
        boolean exp3 = false;
        TreeNode root3 = TreeNode.mkBT(input3);

        int[] input4 = {2,3,3,4,5,-1,4};
        boolean exp4 = false;
        TreeNode root4 = TreeNode.mkBT(input4);

        boolean res1 = symmetricTree101.isSymmetric(root1);
        assert res1 == exp1;

        boolean res2 = symmetricTree101.isSymmetric(root2);
        assert res2 == exp2;

        boolean res3 = symmetricTree101.isSymmetric(root3);
        assert res3 == exp3;

        boolean res4 = symmetricTree101.isSymmetric(root4);
        assert res4 == exp4;

    }
}
