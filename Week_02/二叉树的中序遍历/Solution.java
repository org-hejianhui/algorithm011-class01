package 二叉树的中序遍历;


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
 * 二叉树中序遍历
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-07-05 15:42
 * @see Solution
 * @since JDK1.8
 */
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }
        return list;
    }

    // 使用递归方式
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        helper(root, list);
        return list;
    }

    private void helper(TreeNode root, List<Integer> list) {
        // 递归终止条件
        if (root == null) return;
        helper(root.left, list);
        list.add(root.val);
        helper(root.right, list);
    }
}
