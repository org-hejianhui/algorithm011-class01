/**
 * <p>
 * 64.最小路径和
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-08-02 19:57
 * @see LeetCode64
 * @since JDK1.8
 */
public class LeetCode64 {
    /**
     1. 定义子问题
     找出从左上角到右下角的最小路径和，可以转化成走到每个格子的最小路径和

     2. 定义状态
     设dp为大小m*n矩阵，其中dp[i][j]的值代表直到走到(i, j)的最小路径和。

     3. DP方程
     题目要求，只能向右或向下走，也就是当前单元格(i, j) 只能从左方单元格（i - 1, j） 或 上方单元格(i, j - 1)走到。因此只需要考虑矩阵左边界和上边界。

     走到当前单元格(i ,j)的最小路径和 = “从左方单元格(i - 1, j) 与 从上方单元格(i, j - 1)走来的 两个最小路径和中较小的” + 当前单元格值 grid[i][j]。具体分为以下4种情况：
     (1) 当左边和上边都不是矩阵边界时： 即当 i!=0, j!=0 时，dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
     (2) 当只有左边是矩阵边界时：只能从上面来，即 i=0, j!=0 时，dp[i][j] = dp[i][j-1] + grid[i][j];
     (3) 当只有上边是矩阵边界时：只能从左面来，即当 i!=0, j=0 时，dp[i][j] = dp[i-1][j] + grid[i][j];
     (4) 当左边和上边都是矩阵边界时：即当 i=0, j=0 时，其实就是起点，dp[i][j] = grid[i][j];

     4. 优化空间
     */

    // 二维DP 时间O(m*n) 空间O(m*n)
    public int minPathSum1(int[][] grid) {
        if (grid == null || grid.length == 0
                || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        // 起点
        dp[0][0] = grid[0][0];
        // 当只有上边是矩阵边界时：只能从左面来，即当 i!=0, j=0 时，dp[i][j] = dp[i-1][j] + grid[i][j];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        // 当只有左边是矩阵边界时：只能从上面来，即 i=0, j!=0 时，dp[i][j] = dp[i][j-1] + grid[i][j];
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
    // 一维DP(滚动数组) 时间O(m*n) 空间O(n)
    public int minPathSum2(int[][] grid) {
        if (grid == null || grid.length == 0
                || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        // 定义数组只保存一行单元格值
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 第一行，上边界，前一个单元格值+当前单元格值
                if (i == 0 && j > 0) {
                    dp[j] = dp[j-1] + grid[i][j];
                } else if (i > 0 && j == 0) { // 换行，左边界，直接加当前单元格
                    dp[j] += grid[i][j];
                } else if (j > 0){ // 既不是上边界也不是左边界
                    dp[j] = Math.min(dp[j], dp[j-1]) + grid[i][j];
                }
            }
        }
        return dp[n-1];
    }

    // 利用grid数组进行优化
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0
                || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 起点不处理
                if (i == 0 && j == 0) {
                    continue;
                }
                // 第一行，上边界
                if (i == 0 && j > 0) {
                    grid[i][j] += grid[i][j-1];
                } else if (i > 0 && j == 0) { // 换行，左边界
                    grid[i][j] += grid[i-1][j];
                } else if (j > 0) { // 既不是上边界，也不是左边界
                    grid[i][j] = Math.min(grid[i-1][j], grid[i][j-1]) + grid[i][j];
                }
            }
        }
        return grid[m-1][n-1];
    }

    public static void main(String[] args) {
        LeetCode64 leetCode64 = new LeetCode64();
        System.out.println(leetCode64.minPathSum(new int[][]{
                {1,3,1},
                {1,5,1},
                {4,2,1}
        }));
    }
}
