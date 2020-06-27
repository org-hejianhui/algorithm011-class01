package 旋转数组;

import java.util.Arrays;

/**
 * <p>
 * 189. 旋转数组
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-06-27 18:09
 * @see Rotate
 * @since JDK1.8
 */
public class Rotate {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        rotate2(nums , k);

        System.out.println(Arrays.toString(nums));

    }

    // 暴力：旋转k次，每次将数组旋转1个元素
    private static void rotate1(int[] nums, int k){
        if (nums == null || nums.length == 0) {
            return;
        }
        int temp, previous;
        // 旋转k次
        for (int i = 1; i <= k; i++) {
            // 旋转1次，需获取上次旋转后数组的最后元素
            previous = nums[nums.length - 1];

            // 旋转1次，数组元素相应移位操作
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }

    // 使用环状替换
    public static void rotate2(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }

}
