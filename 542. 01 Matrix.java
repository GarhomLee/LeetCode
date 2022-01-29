https://leetcode.com/problems/01-matrix/

// 解法一：DFS (Recursion) + Memoization，两次扫描
//         维护辅助数组dist，dist[row][col]表示matrix[row][col] == 1的元素到最近的0元素的最小距离。
//         第一次扫描：对于所有matrix[row][col] == 1，利用递归函数dfs()【部分更新】dist[row][col]。
//             递归函数定义：int dfs(int[][] matrix, int[][] dist, int row, int col)表示求
//                     matrix[row][col]到最近的0元素的最小距离。
//             终止条件：1）超过matrix边界，返回初始值Integer.MAX_VALUE - 10
//                     2）matrix[row][col] == 0，返回0
//                     3）matrix[row][col] == 1且dist[row][col] != 0，说明这个格子已经遍历过，直接返回dist[row][col]
//             递归过程：首先，对于没有遍历过的1，初始化dist[row][col]为Integer.MAX_VALUE - 10（不直接初始化为
//                     Integer.MAX_VALUE是为了避免Integer.MAX_VALUE + 1后数据溢出）。
//                     搜索相邻四个元素，调用递归函数更新为最小距离，至少要+1。
//         第二次扫描：由于第一次扫描只利用了部分信息来更新dist数组，结果不完全正确，因此需要再一次扫描来
//             确保所有dist[row][col]是最优解。也就是说，dfs()有后效性。

class Solution {
    int[] dir = new int[]{0, 1, 0 ,-1, 0};
    
    public int[][] updateMatrix(int[][] matrix) {
        int rowLen = matrix.length, colLen = rowLen == 0 ? 0 : matrix[0].length;
        int[][] dist = new int[rowLen][colLen];
        
        /* first round search */
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                dist[row][col] = matrix[row][col] == 0 ? 0 : dfs(matrix, dist, row, col);
            }
        }
        /* second round search to update some values */
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (matrix[row][col] == 0) continue;
                for (int i = 0; i < 4; i++) {
                    int nextRow = row + dir[i], nextCol = col + dir[i + 1];
                    if (nextRow < 0 || nextRow >= matrix.length || nextCol < 0 || nextCol >= matrix[0].length) continue;
                    dist[row][col] = Math.min(dist[row][col], 1 + dist[nextRow][nextCol]);
                }
            }
        }
        
        return dist;
    }
    
    private int dfs(int[][] matrix, int[][] dist, int row, int col) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) return Integer.MAX_VALUE - 10;
        
        if (matrix[row][col] == 0) return 0;
        if (dist[row][col] != 0) return dist[row][col];
        
        dist[row][col] = Integer.MAX_VALUE - 10;
        
        for (int i = 0; i < 4; i++) {
            dist[row][col] = Math.min(dist[row][col], 1 + dfs(matrix, dist, row + dir[i], col + dir[i + 1]));
        }
        
        return dist[row][col];
    }
}


解法二：BFS，视频讲解https://www.youtube.com/watch?v=F9F-TKZlHRs
        扫描矩阵，将所有matrix[row][col] == 0放进Queue，同时将matrix[row][col] == 1的格子赋值-1表示还未遍历。
        只要matrix[row][col]的值不为-1，即可知其已经经过bfs得到了最小距离。

class Solution {
    int[] dir = {-1, 0, 1, 0, -1};
    
    public int[][] updateMatrix(int[][] mat) {
        int rowLen = mat.length, colLen = rowLen == 0 ? 0 : mat[0].length;
        int[][] dist = new int[rowLen][colLen];
        for (int row = 0; row < rowLen; row++) {
            Arrays.fill(dist[row], -1);
        }
        
        Queue<int[]> queue = new LinkedList<>();
        
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (mat[row][col] == 0) {
                    // bfs(mat, row, col, dist);
                    dist[row][col] = 0;
                    queue.add(new int[]{row, col});
                }
            }
        }
        
        int step = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                for (int i = 0; i < dir.length - 1; i++) {
                    int nextRow = curr[0] + dir[i], nextCol = curr[1] + dir[i + 1];
                    if (nextRow >= 0 && nextRow < rowLen && nextCol >= 0 && nextCol < colLen && mat[nextRow][nextCol] == 1 && dist[nextRow][nextCol] == -1) {
                        queue.add(new int[]{nextRow, nextCol});
                        dist[nextRow][nextCol] = step;
                    }
                }
            }
            
            step++;
        }
        
        return dist;        
    }
}       


解法三：DP，两次扫描
        第一次扫描：从左上到右下，得到每个格子从左边或上边格子的最短距离
        第二次扫描：从右下到左上，得到每个格子从右边或下边格子的最短距离
        最后每个格子的值是取两次扫描的最小值