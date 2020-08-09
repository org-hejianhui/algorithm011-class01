import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <p>
 * 547. 朋友圈
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-08-09 17:51
 * @see LeetCode547
 * @since JDK1.8
 */
public class LeetCode547 {

    // 1.DFS 时间O(n^2) 空间O(n)
    public int findCircleNum1(int[][] M) {
        /**
         * 使用一个visited,依次判断每个结点
         * 如果其未访问，朋友圈数加1并对该结点进行dfs搜索标记所有访问到的结点
         */
        boolean[] visited = new boolean[M.length];
        int res = 0;
        // 遍历所有学生
        for (int i = 0; i < M.length; i++) {
            // 当这个学生没有被访问过，那就res++
            if(!visited[i]) {
                // 同时dfs这个学生和他的所有朋友（类似于岛屿问题一样）
                dfs(M, visited, i);
                res++;
            }
        }
        return res;
    }

    private void dfs(int[][] M, boolean[] visited, int i) {
        /**
         * 这里的DFS一开始没有所谓的Terminator
         * 这也是一个特殊情况，因为他的Terminator就是所有东西都访问过了，不用再扩散
         * 对于所有当前访问的结点 i ，然后遍历其它的结点，也就是学生0把0到M.length重新遍历一遍
         */
        for (int j = 0; j < M.length; j++) {
            // 如果M[i][j] == 1，意味着i和j是朋友，且j没有被访问过
            if (M[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(M, visited, j);
            }
        }

    }
    // 2.BFS 时间O(n^2) 空间O(n)
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) return 0;
        int n = M.length;
        boolean[] visited = new boolean[n];
        int count = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    int curPeople = queue.poll();
                    visited[curPeople] = true;
                    for (int j = 0; j < n; j++) {
                        if (M[curPeople][j] == 1 && !visited[j]) {
                            queue.add(j);
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }

    // 3.并查集 时间O(n^3) 空间O(n)
    public int findCircleNum3(int[][] M) {
        if (M == null || M.length == 0) return 0;
        int n = M.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if(M[i][j] == 1) unionFind.union(i, j);
            }
        }
        return unionFind.count;
    }

    class UnionFind {
        private int count = 0;
        private int[] parent;

        public UnionFind (int n) {
            count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int p) {
            // 一直循环获取领头结点
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            // 存在交集直接返回
            if (rootP == rootQ) return;
            parent[rootP] = rootQ;
            count--;
        }
    }

    public static void main(String[] args) {
        LeetCode547 leetCode547 = new LeetCode547();
        int[][] M = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(leetCode547.findCircleNum(M));
    }
}
