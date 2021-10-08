package tree;

import org.junit.jupiter.api.Test;

public class PathSumIIITest {

    @Test
    public void test() {
        PathSumIII437 pathSumIII = new PathSumIII437();

        int[] input1 = {10,5,-3,3,2,-1,11,3,-2,-1,1};
        int targetSum1 = 8;
        TreeNode root1 = TreeNode.mkBT(input1);
        int expect1 = 3;

        int[] input2 = {5,4,8,11,-1,13,4,7,2,-1,-1,5,1};
        int targetSum2 = 22;
        TreeNode root2 = TreeNode.mkBT(input2);
        int expect2 = 3;

        int res1 = pathSumIII.pathSum(root1, targetSum1);
        assert res1 == expect1: res1;
        
        int res2 = pathSumIII.pathSum(root2, targetSum2);
        assert res2 == expect2: res2;
    }
}
