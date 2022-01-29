https://leetcode.com/problems/max-area-of-island/

// 思路：DFS
//         递归函数定义：int dfs(int[][] grid, int row, int col)，表示从位置{row,col}开始的
//             所有相连土地的总面积。
//         终止条件：1）越界，返回0；
//                 2）遇到水，返回0；
//         递归过程：首先，将当前位置{row,col}从标记1变成标记0。
//                 维护count记录搜索到的土地面积。因为当前位置{row,col}一定是土地，所以count初始化为1。
//                 对相邻的4个格子进行DFS，将搜索到的面积累加到count。最后返回count。
// 时间复杂度：O(rowLen * colLen)
// 空间复杂度：O(rowLen * colLen)

class Solution {
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    
    public int maxAreaOfIsland(int[][] grid) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        int maxArea = 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (grid[row][col] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, row, col));
                }
            }
        }
        
        return maxArea;
    }
    
    private int dfs(int[][] grid, int row, int col) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        if (row < 0 || row >= rowLen || col < 0 || col >= colLen || grid[row][col] == 0) {
            return 0;
        }
        
        grid[row][col] = 0;
        int count = 1;
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dir[i], nextCol = col + dir[i + 1];
            count += dfs(grid, nextRow, nextCol);
        }
        
        return count;
    }
}


solution 2: BFS
time comp: O(rowLen * colLen)
space comp: O(rowLen * colLen)

class Solution {
    int[] dir = {-1, 0, 1, 0, -1};
    
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        boolean[][] visited = new boolean[rowLen][colLen];
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (grid[row][col] == 1 && !visited[row][col]) {
                    maxArea = Math.max(maxArea, bfs(grid, row, col, visited));
                }
            }
        }
        
        return maxArea;
    }
    
    private int bfs(int[][] grid, int row, int col, boolean[][] visited) {
        int count = 0;
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        visited[row][col] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            count++;
            for (int i = 0; i < dir.length - 1; i++) {
                int nextRow = curr[0] + dir[i], nextCol = curr[1] + dir[i + 1];
                if (nextRow >= 0 && nextRow < rowLen && nextCol >= 0 && nextCol < colLen && grid[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
                    queue.add(new int[]{nextRow, nextCol});
                    visited[nextRow][nextCol] = true;
                }
            }
        }
        
        return count;
    }
}