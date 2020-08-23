/**
 * <p>
 * 91. 解码方法
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-08-23 21:42
 * @see LeetCode91
 * @since JDK1.8
 */
public class LeetCode91_2 {
    /**
     * 1. 定义子问题
     *    s[i] : i-1...i 每个元素可以译码方法总数
     * 2. 定义状态
     *    dp[i] 表示以s[i]结尾的前缀字符串有多种解码方法
     * 3. DP方程
     *    (1) 如果当前字符不为0，则当前字符解码方法总数为累加前一个前缀字符解码数 dp[i] += dp[i-1]
     *    (2) 前一个字符为1 或者 (2 且 当前字符 <= 6)
     */
    public int numDecodings(String s) {
        if(s == null) {
            return 0;
        }
        int n = s.length();
        if (n == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            // 如果当前字符不为0，则当前字符解码方法总数为累加前一个前缀字符解码数
            if (s.charAt(i) != '0') {
                dp[i] += dp[i-1];
            }
            // 前一个字符为1 或者 (2 且 当前字符 <= 6)
            if (s.charAt(i-1) == '1' || (s.charAt(i-1) == '2' && s.charAt(i) <= '6')) {
                if (i - 2 > 0) {
                    dp[i] += dp[i-2];
                } else {
                    dp[i]++;
                }
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        LeetCode91 leetCode91 = new LeetCode91();
        System.out.println(leetCode91.numDecodings("27"));
    }
}
