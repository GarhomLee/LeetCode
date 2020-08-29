https://leetcode.com/problems/detect-cycles-in-2d-grid/

idea: DFS
    -Use a 2D array to keep track of the step num for each char. There will be a cycle if the
     next char is the same char AND the its step number is at least 3 smaller than the current step.
time complexity: O(rowLen*colLen)
space complexity: O(rowLen*colLen)

class Solution {
    int[] dir = {-1, 0, 1, 0, -1};
    
    // return true if a cycle is detected going from grid[row][col], given the previous visited step and 
    // previously visited char
    private boolean dfs(int[][] step, int preStep, char preChar, char[][] grid, int row, int col) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        if (row < 0 || row >= rowLen || col < 0 || col >= colLen || grid[row][col] != preChar) {
            return false;
        }
        if (step[row][col] != 0) {
            return preStep - step[row][col] >= 3;   // evaluate if it encounters a visited char with the previous step that can form a cycle
        }
        
        step[row][col] = preStep + 1;   // update step
        for (int i = 0; i + 1 < dir.length; i++) {
            int nextRow = row + dir[i], nextCol = col + dir[i + 1];
            if (dfs(step, step[row][col], grid[row][col], grid, nextRow, nextCol)) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean containsCycle(char[][] grid) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        int[][] step = new int[rowLen][colLen]; 
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (dfs(step, 0, grid[row][col], grid, row, col)) {
                    return true;
                }
            }
        }
        
        return false;
    }
}