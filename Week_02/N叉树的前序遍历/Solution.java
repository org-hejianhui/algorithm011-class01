package N叉树的前序遍历;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Node {
    public int val;
    public List<Node> children;
    public Node() {}
    public Node(int _val) {
        val = _val;
    }
    public Node(int _val , List<Node> _children) {
        val = _val;
        children = _children;
    }
}
/**
 * <p>
 * 589.N叉树的前序遍历
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-07-05 14:17
 * @see Solution
 * @since JDK1.8
 */
public class Solution {

    // 使用迭代-双端队列
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Deque<Node> deq = new ArrayDeque<>();
        deq.offerFirst(root);
        while (!deq.isEmpty()) {
            Node cur = deq.pollFirst();
            list.add(cur.val);
            for (int i = cur.children.size() - 1; i >= 0; i--) {
                deq.offerFirst(cur.children.get(i));
            }
        }
        return list;
    }

    // 使用递归的方式
    public List<Integer> preorder1(Node root) {
        // 根-左-右
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        helper(root, list);
        return list;
    }

    private void helper(Node root , List<Integer> list) {
        // 递归终止条件
        if(root == null) return;
        list.add(root.val);
        for (Node node : root.children) {
            helper(node , list);
        }
    }
}
