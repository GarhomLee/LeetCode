https://leetcode.com/problems/minimum-path-sum/

// 总体思路：可以算作62. Unique Paths的follow-up。类似最小路径问题的，一般都是DP解法。
//         状态函数：dp[i]表示到达当前行第i列（0-based）的minimum path sum
//         状态转移方程：在row!=0且col!=0的一般情况下，sum一定包含grid[row][col]。因为当前格子[row,col]可以由上面一格[row-1,col]或左边一格[row,col-1]走到，
//                 所以minimum sum要取这两种可能的较小值。
//         初始值：col==0时，dp[0] += grid[row][0]。row==0时，dp[col] = grid[row][col] + dp[col - 1];
// 时间复杂度：O(m*n), m = grid.length, n = grid[0].length
// 空间复杂度：O(m*n) -> O(n), m = grid.length, n = grid[0].length

class Solution {
    public int minPathSum(int[][] grid) {
        int rowLen = grid.length, colLen = rowLen == 0? 0 : grid[0].length;
        int[] dp = new int[colLen];
         
        for (int row = 0; row < rowLen; row++) {
            dp[0] += grid[row][0];
            for (int col = 1; col < colLen; col++) {
                if (row == 0) dp[col] = grid[row][col] + dp[col - 1];
                else dp[col] = grid[row][col] + Math.min(dp[col], dp[col - 1]);
            }
        }
        return dp[colLen - 1];
    }
}