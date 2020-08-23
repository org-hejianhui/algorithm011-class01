import java.util.Arrays;
import java.util.Collections;

/**
 * <p>
 * 151. 翻转字符串里的单词
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-08-23 21:35
 * @see LeetCode151
 * @since JDK1.8
 */
public class LeetCode151 {

    public String reverseWords(String s) {
        String[] words = s.trim().split(" +");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
    public String reverseWords1(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] chars = s.toCharArray();
        int n = s.length();
        // 翻转整个数组
        reverse(chars, 0, n - 1);
        // 翻转每个单词
        vord_reverse(chars, n);
        // 去除多余的空格
        return clean_space(chars, n);
    }

    private void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start++] = chars[end];
            chars[end--] = temp;
        }
    }

    private void vord_reverse(char[] chars, int n) {
        int i = 0, j = 0;
        while (j < n) {
            // 找到第一个首字母
            while (i < n && chars[i] == ' ') {
                i++;
            }
            j = i;
            // 末位置
            while (j < n && chars[j] != ' ') {
                j++;
            }
            reverse(chars, i, j - 1);
            i = j;
        }
    }

    private String clean_space(char[] chars, int n) {
        int i = 0, j = 0;
        while (j < n) {
            while (j < n && chars[j] == ' ') j++;
            while (j < n && chars[j] != ' ') chars[i++] = chars[j++];
            while (j < n && chars[j] == ' ') j++;
            if (j < n) chars[i++] = ' ';
        }
        return new String(chars).substring(0, i);
    }

    public static void main(String[] args) {
        LeetCode151 leetCode151 = new LeetCode151();
        System.out.println(leetCode151.reverseWords("  hello world!  "));
    }
}
