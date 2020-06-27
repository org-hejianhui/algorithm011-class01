package 删除排序数组中的重复项;

/**
 * <p>
 * 26. 删除排序数组中的重复项（Facebook、字节跳动、微软在半年内面试中考过）
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-06-27 16:25
 * @see RemoveDuplicates
 * @since JDK1.8
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1,1,2}));
    }

    public static int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int p = 0; // 前指针
        int q = 1;// 后指针

        while (q < nums.length){
            if (nums[p] != nums[q]) {
                if(q - p > 1){
                    nums[p + 1] = nums[q];
                }
                p++;
            }
            q++;
        }
        return p + 1;

    }

}
