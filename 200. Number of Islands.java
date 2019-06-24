https://leetcode.com/problems/number-of-islands/

// 思路：DFS。
//      利用DFS将所有的'1'变成'0'（想象成把岛沉到水里）。在遍历grid的时候，如果遇到'1'，说明这个岛还没被处理，
//      所以count++，同时DFS把相连的'1'全部处理掉。这样即使原来的neighbor元素为'1'，也会被处理掉，当遍历到
//      这个neighbor元素时已经变成了'0'。

class Solution {
    int[] dir = new int[]{0, -1, 0, 1, 0};
    int count = 0;
    
    public int numIslands(char[][] grid) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (grid[row][col] == '1') {
                    count++;
                    dfs(grid, row, col);
                }
            }
        }
        
        return count;
    }
    
    private void dfs(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == '0') return ;
        
        grid[row][col] = '0';  // mark as already visited
        for (int i = 0; i < 4; i++) {
            dfs(grid, row + dir[i], col + dir[i + 1]);
        }
    }
}