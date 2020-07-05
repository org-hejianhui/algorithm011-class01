package 二叉树的后序遍历;

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
 * 145.二叉树后序遍历
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-07-05 16:09
 * @see Solution
 * @since JDK1.8
 */
public class Solution {

    // 使用迭代方式
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            // 和传统先序遍历不一样，左节点先入栈
            if (cur.left != null) {
                stack.push(cur.left);
            }
            // 再将右节点入栈
            if (cur.right != null) {
                stack.push(cur.right);
            }
            // 逆序添加节点值
            list.add(0, cur.val);
        }
        return list;
    }


    // 使用递归方式
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        helper(root, list);
        return list;
    }

    private void helper(TreeNode root, List<Integer> list) {
        // 递归终止条件
        if (root == null) return;
        helper(root.left, list);
        helper(root.right, list);
        list.add(root.val);
    }
}
