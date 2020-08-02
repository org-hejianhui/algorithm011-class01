/**
 * <p>
 * 647.回文子串
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-08-02 22:15
 * @see LeetCode647
 * @since JDK1.8
 */
public class LeetCode647 {
    /**
     * 1.定义子问题
     *   字符串s[i...j]计算有多少个回文子串，可以分解成每个
     * 2.定义状态
     *   定义数组dp[i][j]，表示字符串s[i...j]是否为回文字符串
     *   dp[i][j] = true，表示是回文子串
     *   dp[i][j] = false，表示不是回文子串
     * 3.DP方程
     *   (1)如果s[i] == s[j],那么说明只要dp[i+1][j-1]是回文子串，那么dp[i][j]也就是回问子串
     *   (2)如果s[i] != s[j],那么说明dp[i][j]必定不是回文子串
     */
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int result = n;
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // j 和 i相邻的时候
                    if (j - i == 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j]) {
                    result++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode647 leetCode647 = new LeetCode647();
        System.out.println(leetCode647.countSubstrings("aaa"));
    }
}
