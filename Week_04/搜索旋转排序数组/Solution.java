package 搜索旋转排序数组;

/**
 * <p>
 * 搜索旋转排序数组
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-07-19 22:51
 * @see Solution
 * @since JDK1.8
 */
public class Solution {
    // 二分查找
    public int search(int[] nums, int target) {
        int start = 0, end =  nums.length - 1, mid = 0;;
        while (start <= end) {
            mid = start + (end - start) / 2;
            // 匹配到目标值
            if(nums[mid] == target) return mid;

            // 先根据 nums[mid] 与 nums[start] 的关系判断 mid 是在左段还是右段
            // 左段：前半部分有序,注意此处用大于等于
            if(nums[mid] >= nums[start]) {
                // 再判断 target 是在 mid 的左边还是右边，从而调整左右边界 start 和 end
                // target 在前半部分 nums[start] <=target<nums[mid]
                if(target >= nums[start] && target < nums[mid])
                    end = mid - 1;
                else
                    start = mid + 1;
            }
            // 右段：后半部分有序
            else {
                // nums[mid] <target<=nums[end]
                if(target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }
}
