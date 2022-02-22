package tree;

import org.omg.CORBA.TRANSACTION_MODE;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SymmetricTree101 {

//    public boolean isSymmetric(TreeNode root) {
//        //如果根节点为空，直接返回true
//        if(root == null) {
//            return true;
//        }
//        //递归判断左右子树是否镜像对称
//        return helper(root.left, root.right);
//    }
//
//    //因为是要对比两颗树是否镜像对称，所以同时传入左右子树
//    boolean helper(TreeNode left, TreeNode right) {
//        //如果左右子树都为空，则对称
//        if(left == null && right == null) {
//            return true;
//        }
//        //如果左右子树只有一方为空，肯定不对称
//        if(left == null || right == null) {
//            return false;
//        }
//        //如果左右子树的根节点值不同，肯定不对称
//        if(left.val != right.val) {
//            return false;
//        }
//        //再子树根节点值相同的情况下，再对应相比对应位置的子树是否相同
//        return helper(left.left, right.right) && helper(left.right, right.left);
//    }

    /**
     * 法二：非递归。迭代的方法
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
            return check(root, root);
    }

    boolean check(TreeNode left, TreeNode right) {
        //还是利用递归的思想，但是利用栈迭代实现
        Stack<TreeNode> s = new Stack<>();
        //先将根节点压进去两次，保证第一次对比的成功
        s.push(left);
        s.push(right);
        while (!s.isEmpty()) {
            //弹出相邻的两个节点，即为左右子树的根结点（除了第一次）
            TreeNode r = s.pop();
            TreeNode l = s.pop();
            //如果都为空，则一定对称，进行下一次循环
            if(r == null && l == null) {
                continue;
            }
            //如果只有一边为空，或者值不相等，则直接返回不对称
            if(r == null || l == null) {
                return false;
            }
            if(r.val != l.val) {
                return false;
            }
            //如果两颗树的根结点满足条件，则将其镜像位置子树根结点成对压入栈中，后续比较
            s.push(l.left);
            s.push(r.right);
            s.push(l.right);
            s.push(r.left);
        }
        return true;
    }

}
