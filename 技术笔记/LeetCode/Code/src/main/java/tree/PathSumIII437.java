package tree;

import java.util.HashMap;
import java.util.Map;

public class PathSumIII437 {

    //法一：深度优先直接搜索
//    public int pathSum(TreeNode root, int targetSum) {
//        if(root == null) {
//            return  0;
//        }
//        //在包含当前根节点的情况下满足条件的路径
//        int count = rootSum(root, targetSum);
//        //分别计算当前的左子树和右子树的路径数
//        count += pathSum(root.left, targetSum);
//        count += pathSum(root.right, targetSum);
//        return count;
//    }
//
//    int rootSum(TreeNode root, int targetSum) {
//        int count = 0;
//        if(root == null) {
//            return 0;
//        }
//        int val = root.val;
//        //判断当前节点是否满足和
//        if(val == targetSum) {
//            count++;
//        }
//
//        //目标和减去当前根节点的值，判断左右子树是否满足减去根节点值后的和
//        //计算左右子树是否满足目标和条件
//        count += rootSum(root.left, targetSum - val);
//        count += rootSum(root.right, targetSum - val);
//
//        return count;
//    }

    /**
     * 法二：前缀和加深度优先搜索
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        //存储的是<前缀和，前缀和次数>
        HashMap<Long, Integer> prefix= new HashMap<>();
        //给哈希表初始化一个，前缀和为0的值是考虑最开始的根节点的值即满足目标和的情况
        prefix.put(0L, 1);
        int res = dfs(root, targetSum, prefix, 0L);
        return res;
    }

    int dfs(TreeNode root, int targetSum, HashMap<Long, Integer> prefix, Long curr) {
        if(root == null) {
            return 0;
        }

        //加上当前节点的值，计算出当前节点的前缀和
        curr += root.val;

        //如何前面出现当前前缀值和目标和之差的前缀，说明从前面某一节点，到当前节点的和满足目标和
        int res = prefix.getOrDefault(curr - targetSum, 0);
        //将当前前缀和加入哈希表中，如果已经存在则次数加一，没有存在次数设为1
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        //递归计算当前结点的左右子树的路径情况
        res += dfs(root.left, targetSum, prefix, curr);
        res += dfs(root.right, targetSum, prefix, curr);
        //在递归中使用的“常存变量”需要在递归结束的时候回溯
        // 不然在计算当前结点的时候，由于实际上是从递归触底反弹开始计算的，哈希表中会包含后面结点的前缀和
        //只需将当前递归中的前缀和的次数减一即可实现回溯效果
        //因为本轮递归中该前缀和次数至少为1，所以减一不用担心为负一
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);
        //返回满足条件的路径数
        return res;
    }


}
