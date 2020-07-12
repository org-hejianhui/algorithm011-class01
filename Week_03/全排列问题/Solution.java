package 全排列问题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 46.全排列问题
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-07-12 14:08
 * @see Solution
 * @since JDK1.8
 */
public class Solution {
    // 定义全局返回值
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length <= 0) return res;
        // 定义路径存放集合
        LinkedList<Integer> track = new LinkedList<>();
        int n = nums.length;
        // 树遍历，维护好节点的属性
        backtrack(nums, track);
        return res;
    }

    // 路径：track用于记录路径
    // 选择列表：nums 中不存在于 track的那些元素
    // 结束条件：nums 中的元素全部在track中
    private void backtrack(int[] nums, LinkedList<Integer> track) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 将该选择从选择列表移除，这里稍微做了变通，没有显示记录选择列表
            if (track.contains(nums[i])) continue;

            // 做出选择
            track.add(nums[i]);

            // 进入决策树下一层
            backtrack(nums, track);

            // 取消选择
            track.removeLast();
        }

    }
}
