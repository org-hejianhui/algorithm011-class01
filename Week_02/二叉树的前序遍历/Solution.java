package 二叉树的前序遍历;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){
        val = x;
    }
}
/**
 * <p>
 * 二叉树的前序遍历
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-07-05 13:39
 * @see Solution
 * @since JDK1.8
 */
public class Solution {

    // 迭代，基于栈的遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                list.add(cur.val);
                cur = cur.left;
            }
            cur = stack.pop();
            cur = cur.right;
        }
        return list;
    }

    // 递归方式
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root , list);
        return list;
    }

    /**
     * 基于递归的方式实现
     * 复杂度分析：
     *    时间复杂度：O(n)。递归函数T(n) = 2.T(n/2) + 1。
     *    空间复杂度：O(n)。最坏的情况需要O(n),平均需要O(logn)
     * @param root
     * @param list
     */
    private void helper(TreeNode root, List<Integer> list) {
        // 递归终止条件
        if (root == null) return;
        // 根
        list.add(root.val);
        // 左
        helper(root.left , list);
        // 右
        helper(root.right, list);
    }
}
