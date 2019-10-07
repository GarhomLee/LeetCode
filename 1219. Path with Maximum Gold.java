https://leetcode.com/problems/path-with-maximum-gold/

// 思路：Backtracking (DFS)。不能简单的用DFS是因为起始位置对于结果可能有影响（有后效性），所以需要搜索所有可能起始位置。
//         维护变量max，记录当前搜索到的某一路径可以获得的最大金子数；boolean数组visited，记录某一位置是否被所搜过。
//         对于每一个格子，都要调用递归函数dfs()来更新max。
//         递归函数定义：int dfs(int[][] grid, int row, int col, boolean[][] visited)，表示从当前位置{row, col}
//             开始的所有路径中可以获得的最大金子数。
//         终止条件：1）越界
//                 2）grid[row][col] == 0，根据题意不去搜索
//                 3）visited[row][col] == true，已经遍历过的位置不能重复遍历。
//         递归过程：Goal：没有特定终止目标，搜索到无法再搜索为止。
//                 Choices：四个方向。
//                 Constraints：没有特殊限制条件。
//                 在搜索四个方向前，将visited[row][col]设为true。搜索完四个方向后，重置visited[row][col]为false，
//                 以便backtracking。
//                 返回四个方向搜索结果的最大值与当前grid[row][col]的和。
// 时间复杂度：O((rowLen * colLen)^2)
// 空间复杂度：O(rowLen * colLen)

class Solution {
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    
    public int getMaximumGold(int[][] grid) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        boolean[][] visited = new boolean[rowLen][colLen];
        int max = 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                max = Math.max(max, dfs(grid, row, col, visited));
            }
        }
        
        return max;
    }
    
    private int dfs(int[][] grid, int row, int col, boolean[][] visited) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        if (row < 0 || row >= rowLen || col < 0 || col >= colLen 
            || grid[row][col] == 0 || visited[row][col]) {
            return 0;
        }
        
        visited[row][col] = true;
        int max = 0;
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dir[i], nextCol = col + dir[i + 1];
            max = Math.max(max, dfs(grid, nextRow, nextCol, visited));
        }
        
        max += grid[row][col];
        visited[row][col] = false;  // reset
        return max;
    }
}