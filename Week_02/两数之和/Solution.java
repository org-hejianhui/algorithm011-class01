package 两数之和;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 两数之和
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-07-05 17:26
 * @see Solution
 * @since JDK1.8
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] res = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - i)) {
                res[0] = map.get(target - i);
                res[1] = i;
                break;
            }
            map.put(nums[i] ,i);
        }
        return res;

    }
}
