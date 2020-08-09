import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <p>
 * 200.岛屿数量
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-08-09 18:44
 * @see LeetCode200
 * @since JDK1.8
 */
public class LeetCode200 {
    // 1.DFS 深度优先遍历 时间O(M*N) 空间O(M*N)
    public int numIslands1(char[][] grid) {
        if(grid == null || grid.length == 0
            || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 如果当前格子是陆地
                if(grid[i][j] == '1') {
                    // 遍历当前格子相邻的陆地
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return  count;
    }
    private void dfs(char[][] grid, int r, int c) {
        // 递归终止条件-边界条件
        if(!(0 <= r && r < grid.length && 0 <= c && c < grid[0].length)) {
            return;
        }
        // 如果当前格子不是陆地直接返回
        if (grid[r][c] != '1') return;

        // 标记当前格子被访问过
        grid[r][c] = 2;

        // 遍历当前格子的四个方向:上下左右
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    // 2. BFS 时间O(N*M) 空间O(min(N,M))
    public int numIslands2(char[][] grid) {
        if(grid == null || grid.length == 0
                || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int nr = grid.length;
        int nc = grid[0].length;
        int count = 0;
        // BFS
        Queue<Integer> queue = new ArrayDeque<>();
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                // 如果当前格子为陆地，则有效
                if (grid[r][c] == '1') {
                    // 岛屿数+1
                    count++;
                    // 标记访问过
                    grid[r][c] = '0';
                    // 行与列转换成序号加入队列
                    queue.add(r * nc + c);
                    // BFS 遍历当前格子相邻的结点
                    while (!queue.isEmpty()) {
                        // 取出当前格子
                        int curr = queue.poll();
                        // 获取当前格子的（x,y）
                        int row = curr / nc;
                        int col = curr % nc;
                        // 上下左右四个方向进行扩散判断
                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            queue.add((row - 1) * nc + col);
                            grid[row - 1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row + 1][col] == '1') {
                            queue.add((row + 1) * nc + col);
                            grid[row + 1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            queue.add(row * nc + col - 1);
                            grid[row][col - 1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col + 1] == '1') {
                            queue.add(row * nc + col + 1);
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }
        return count;
    }

    // 3.并查集
    public int numIslands(char[][] grid) {
        int count = 0;
        if (grid == null || grid.length == 0
                || grid[0] == null || grid[0].length == 0) {
            return count;
        }
        int m = grid.length;
        int n = grid[0].length;
        UnionFind unionFind = new UnionFind(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == '1') {
                    // 二维矩阵m*n,z在一维数组的位置是:(第几行*矩阵宽度) + 在第几列
                    // 前面已经执行过，不用往回查
                    if (i + 1 < m && grid[i+1][j] == '1') {
                        unionFind.union(i * n + j, (i+1) * n + j);
                    }
                    if (j + 1 < n && grid[i][j+1] == '1') {
                        unionFind.union(i * n + j, i * n + j + 1);
                    }
                }
            }
        }
        return unionFind.count;
    }

    class UnionFind {
        int count = 0;
        int[] parent;
        public UnionFind(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if(grid[i][j] == '1') {
                        // 二维变一维
                        parent[i * n + j] = i * n + j;
                        count++;
                    }
                }
            }
        }

        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootQ == rootP) return;
            parent[rootP] = rootQ;
            count--;
        }
    }

    public static void main(String[] args) {
        LeetCode200 leetCode200 = new LeetCode200();
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        System.out.println(leetCode200.numIslands(grid));
    }
}
