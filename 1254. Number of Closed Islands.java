https://leetcode.com/problems/number-of-closed-islands/

思路：DFS
    
时间复杂度：O(rowLen * colLen)
空间复杂度：O(rowLen * colLen)
犯错点：1.细节错误：如果找到了某个方向为false就立即返回，会导致剩下的方向没有被搜索，使得island没有完整地被
            赋值为2，会有剩下的0。

class Solution {
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    
    public int closedIsland(int[][] grid) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        int count = 0;
        for (int row = 1; row < rowLen - 1; row++) {
            for (int col = 1; col < colLen - 1; col++) {
                if (grid[row][col] == 0) {
                    count += dfs(grid, row, col) ? 1 : 0;
                }
            }
        }
        
        return count;
    }
    
    private boolean dfs(int[][] grid, int row, int col) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        if (row < 0 || row >= rowLen || col < 0 || col >= colLen) {
            return false;
        }
        
        if (grid[row][col] != 0) {
            return true;
        }
        
        grid[row][col] = 2;
        if (row == 0 || row == rowLen - 1 || col == 0 || col == colLen - 1) {
            return false;
        }
        
        // {Mistake 1}
        boolean isClosed = true;  // {Correction 1}
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dir[i], nextCol = col + dir[i + 1];
            //if (!dfs(grid, nextRow, nextCol)) return false;  // {Mistake 1}
            isClosed = isClosed & dfs(grid, nextRow, nextCol);  // {Correction 1}
        }
        
        return isClosed;
    }
}