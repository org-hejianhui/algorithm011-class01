# 63. 不同路径 II
1、状态定义：
dp[i][j] 表示走到格子 (i, j) 的方法数。

2、状态转移：
如果网格 (i, j) 上有障碍物，则 dp[i][j] 值为 0，表示走到该格子的方法数为 0；
否则网格 (i, j) 可以从网格 (i - 1, j) 或者 网格 (i, j - 1) 走过来，因此走到该格子的方法数为走到网格 (i - 1, j) 和网格 (i, j - 1) 的方法数之和，即 dp[i, j] = dp[i - 1, j] + dp[i, j - 1]。

3、初始条件
第 1 列的格子只有从其上边格子走过去这一种走法，因此初始化 dp[i][0] 值为 1，存在障碍物时为 0；
第 1 行的格子只有从其左边格子走过去这一种走法，因此初始化 dp[0][j] 值为 1，存在障碍物时为 0。

```
class Solution {
    // DP-一维 时间O(n*m) 空间O(n)
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1) {
                    dp[j] = 0;
                } else if (j > 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[width - 1];
    }
    // DP-二维 时间O(m*n) 空间O(m*n)
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length == 0 
            || obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
                return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 遇到障碍物
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    if (i == 0 && j == 0) {
                        dp[i][j] = 1;
                    } else if (i == 0) {
                        dp[i][j] = dp[i][j-1];
                    } else if (j == 0) {
                        dp[i][j] = dp[i-1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
        }
        return dp[m-1][n-1];
    }
}
```