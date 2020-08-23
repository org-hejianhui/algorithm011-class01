/**
 * <p>
 * 541. 反转字符串 II
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-08-23 21:33
 * @see LeetCode541
 * @since JDK1.8
 */
public class LeetCode541 {

    public String reverseStr1(String s, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        // 每2k个元素为一组进行反转
        for (int i = 0; i < n; i += 2 * k) {
            int left = i;
            //判断下标是否越界
            int right = i + k - 1 < n ? i + k - 1 : n -1;
            // 双指针交换
            while (left < right) {
                char temp = chars[left];
                chars[left++] = chars[right];
                chars[right--] = temp;
            }
        }
        return String.valueOf(chars);
    }

    public String reverseStr(String s, int k) {
        int start = 0, end = s.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            // 剩余字符的个数
            int size = end - start + 1;
            if (size < k) {
                sb.append(new StringBuilder(s.substring(start)).reverse());
            } else {
                // 前k个元素反转
                sb.append(new StringBuilder(s.substring(start, start + k)).reverse());
                // 后k个元素保持原样
                sb.append(new StringBuilder(s.substring(start + k, start+Math.min(size, 2 * k))));
            }
            start += 2 * k;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LeetCode541 leetCode541 = new LeetCode541();
        System.out.println(leetCode541.reverseStr("abcdefg", 2));
    }
}
