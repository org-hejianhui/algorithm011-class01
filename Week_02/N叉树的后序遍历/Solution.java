package N叉树的后序遍历;

import org.omg.PortableInterceptor.INACTIVE;

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
 * 590.N叉树的后序遍历
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-07-05 16:43
 * @see Solution
 * @since JDK1.8
 */
public class Solution {

    // 使用迭代方式
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Deque<Node> deq = new ArrayDeque<>();
        deq.offerFirst(root);
        while (!deq.isEmpty()) {
            Node cur = deq.pollFirst();
            // 与先序遍历的尾部插入不同，这里是头部插入
            list.add(0,cur.val);
            // 因为是头部插入，其顺序：根-右-左。所以这里从左往右入栈
            for (int i = 0; i < cur.children.size(); i++) {
                deq.offerFirst(cur.children.get(i));
            }
        }
        return list;
    }

    // 使用递归方式
    public List<Integer> postorder1(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        helper(root, list);
        return list;
    }

    private void helper(Node root, List<Integer> list) {
        // 递归终止条件
        if (root == null) return;
        for (Node node : root.children) {
            helper(node, list);
        }
        list.add(root.val);
    }

}
