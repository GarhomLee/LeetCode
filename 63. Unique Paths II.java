https://leetcode.com/problems/unique-paths-ii/

// 总体思路：62. Unique Paths的follow-up。增加了obstacles的条件，依然可以用DP解题。
//         状态函数：dp[i]表示到达当前行第i列（0-based）的unique path数量
//         状态转移方程：由于obstacle的存在，所以当遭遇obstacle时，dp[i]只能为0。否则，没有obstacle，当前格子[row,col]可以从上面一格[row-1,col]或左边一格[row,col-1]走到，
//         所以unique path数量为上面一格和左边一格unique path数量的总和。
//         初始值：dp[0]=1
// 时间复杂度：O(m*n), m = obstacleGrid.length, n = obstacleGrid[0].length
// 空间复杂度：O(m*n) -> O(n), m = obstacleGrid.length, n = obstacleGrid[0].length

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rowLen = obstacleGrid.length, colLen = rowLen == 0? 0 : obstacleGrid[0].length;
        int[] dp = new int[colLen];
        dp[0] = 1;
        for (int row = 0; row < rowLen; row++) {
            dp[0] = obstacleGrid[row][0] == 1? 0 : dp[0];
            for (int col = 1; col < colLen; col++) {
                if (row == 0) dp[col] = obstacleGrid[0][col] == 1? 0 : dp[col - 1];
                else dp[col] = obstacleGrid[row][col] == 1? 0 : dp[col] + dp[col - 1];
            }
        }
        return dp[colLen - 1];
    }
}