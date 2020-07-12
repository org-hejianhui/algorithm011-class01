package 从前序与中序遍历序列构造二叉树;

import java.util.HashMap;
import java.util.Map;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

/**
 * <p>
 * 105. 从前序与中序遍历序列构造二叉树
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-07-12 14:34
 * @see Solution
 * @since JDK1.8
 */
public class Solution {
    Map<Integer,Integer> idx_map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 前序遍历：根-左-右
        // 中序遍历：左-根-右
        int len = inorder.length;
        // 构造哈希映射表，帮助快速定位根节点
        for (int i = 0; i < preorder.length; i++) {
            idx_map.put(inorder[i], i);
        }
        return helper(preorder,inorder,0,len-1,0,len-1);
    }

    private TreeNode helper(int[] preorder, int[] inorder, int preL, int preR, int inL, int inR) {
        if (preL > preR || inL > inR) return null;
        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preL]);
        // 在中序遍历中定位根节点
        int index = idx_map.get(preorder[preL]);
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界 + 1  开始到 左子树中的节点数目」个元素就是对应了中序遍历中「从 左边界 开始到 根」
        root.left = helper(preorder, inorder, preL + 1, preL + (index - inL), inL, index - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界 + 1 + 左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」元素
        root.right = helper(preorder, inorder, preL + (index - inL) + 1, preR, index + 1, inR);
        return root;
    }
}
