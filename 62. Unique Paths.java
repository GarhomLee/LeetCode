https://leetcode.com/problems/unique-paths/

// 总体思路：求total counting number，可以用DP解决。
//         状态函数：dp[i]表示到达当前行第i列（0-based）的unique path数量
//         状态转移方程：在row!=0且col!=0的一般情况下，当前格子[row,col]可以从上面一格[row-1,col]或左边一格[row,col-1]走到，所以unique path数量
//                 为上面一格和左边一格unique path数量的总和。
//         初始值：col==0时dp[0]=1，row==0时dp[col]=1
// 时间复杂度：O(m*n)
// 空间复杂度：O(m*n) -> O(n)

class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        for (int row = 0; row < m; row++) {
            dp[0] = 1;
            for (int col = 1; col < n; col++) {
                if (row == 0) dp[col] = 1;
                else dp[col] += dp[col - 1];
            }
        }
        return dp[n - 1];
    }
}