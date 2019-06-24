https://leetcode.com/problems/longest-increasing-path-in-a-matrix/

// 思路：DFS + DP Top-down Memoization
//      利用helper method，返回的是以matrix[row][col]为起始的increasing path的长度。最终结果在所有的increasing path中取最大值。
//      在dfs函数（即helper method）中，利用二维dp数组存以matrix[row][col]为起始的increasing path的长度。
//      如果dp[row][col] != 0，说明matrix[row][col]为起始的path已经被遍历和求解过，所以直接返回dp[row][col]。
//      否则，扫描它的四个相邻元素。如果相邻元素在matrix界内且比matrix[row][col]大，说明可以从matrix[row][col]到相邻元素形成increasing path，
//      dp[row][col] = dfs(matrix, nextRow, nextCol, dp) + 1。最后dp[row][col]由可能的最大值确定。

class Solution {
    int[] dir = new int[]{0, -1, 0, 1, 0};
    
    public int longestIncreasingPath(int[][] matrix) {
        int rowLen = matrix.length, colLen = rowLen == 0 ? 0 : matrix[0].length;
        int res = 0;
        int[][] dp = new int[rowLen][colLen];
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                res = Math.max(res, dfs(matrix, row, col, dp));
            }
        }
        return res;
    }
    
    private int dfs(int[][] matrix, int row, int col, int[][] dp) {
        if (dp[row][col] != 0) return dp[row][col];  // use memoization
        
        dp[row][col] = 1;
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dir[i], nextCol = col + dir[i + 1];
            if (isValid(matrix, row, col, nextRow, nextCol)) dp[row][col] = Math.max(dp[row][col], dfs(matrix, nextRow, nextCol, dp) + 1); 
        }
        return dp[row][col];
    }
    
    private boolean isValid(int[][] matrix, int row, int col, int nextRow, int nextCol) {
        return (nextRow >= 0 && nextRow < matrix.length && nextCol >= 0 && nextCol < matrix[0].length && matrix[row][col] < matrix[nextRow][nextCol]);
    }
}
