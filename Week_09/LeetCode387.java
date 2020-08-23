import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 387. 字符串中的第一个唯一字符
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-08-17 11:26
 * @see LeetCode387
 * @since JDK1.8
 */
public class LeetCode387 {

    public int firstUniqChar1(String s) {
        Map<Character, Integer> map = new HashMap<>(26);
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1) {
                return i;
            }
        }
        return -1;
    }

    public int firstUniqChar2(String s) {
        int[] freq = new int[26];
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            freq[ch - 'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            if (freq[chars[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public int firstUniqChar3(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 同一个位置
            if (s.indexOf(ch) == s.lastIndexOf(ch)) {
                return i;
            }
        }
        return -1;
    }

    public int firstUniqChar4(String s) {
        boolean[] notUniq = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!notUniq[ch - 'a']) {
                if (s.indexOf(ch) == s.lastIndexOf(ch)) {
                    return i;
                } else {
                    notUniq[ch - 'a'] = true;
                }
            }
        }
        return -1;
    }

    public int firstUniqChar(String s) {
        int res = -1;
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int index = s.indexOf(ch);
            if (index != -1 && index == s.lastIndexOf(ch)) {
                res = (res == -1 || res > index) ? index : res;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode387 leetCode387 = new LeetCode387();
        System.out.println(leetCode387.firstUniqChar("loveleetcode"));
    }
}
