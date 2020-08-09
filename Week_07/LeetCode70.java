/**
 * <p>
 * 70.爬楼梯
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-08-09 17:05
 * @see LeetCode70
 * @since JDK1.8
 */
public class LeetCode70 {

    // 1. 暴力递归 时间O(n) 空间O(n) - 会超时
    public int climbStairs1(int n) {
        if (n <= 2) return n;
        return climbStairs1(n-1) + climbStairs1(n-2);
    }

    // 2. 数组记忆化搜索 递归 时间O(n) 空间O(n)
    public long climbStairs2(int n) {
        if (n <= 2) return n;
        long[] memo = new long[n+1];
        return fib(n, memo);
    }
    private long fib(int n, long[] memo) {
        if (n <= 2) return n;
        if (memo[n] == 0) {
            memo[n] = fib(n-1, memo) + fib(n-2, memo);
        }
        return memo[n];
    }

    // 3. DP 时间O(n) 空间O(n)
    public int climbStairs3(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    // 4. DP、滚动数组 时间O(n) 空间O(1)
    public int climbStairs4(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 0; i < n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }


    public static void main(String[] args) {
        LeetCode70 leetCode70 = new LeetCode70();
        System.out.println(leetCode70.climbStairs4(50));
    }
}
