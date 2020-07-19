package 岛屿数量;

/**
 * <p>
 * 200. 岛屿数量
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-07-19 22:53
 * @see Solution
 * @since JDK1.8
 */
public class Solution {
    // 深度优先搜索
    public int numIslands(char[][] grid) {
        int count = 0;
        if(grid == null || grid.length == 0) return count;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int r, int c) {
        // 判断 base case
        if(!inArea(grid, r, c)) return;
        // 如果这个格子不是岛屿，直接返回
        if(grid[r][c] != '1') return;
        // 将格子标记为「已遍历过」
        grid[r][c] = 2;
        // 访问上、下、左、右四个相邻的结点
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);

    }

    private boolean inArea(char[][] grid, int r, int c) {
        return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
    }
}
