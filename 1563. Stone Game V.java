https://leetcode.com/problems/stone-game-v/

idea: Recursion with Memoization + Prefix Sum
time complexity: O(n^3)
space complexity: O(n^2)

class Solution {
    // find the max score in val[low:high]
    private int dfs(int[] val, int low, int high, int[] sum, int[][] dp) {
        if (low == high) {
            return 0;
        }
        if (low + 1 == high) {
            return Math.min(val[low], val[high]);
        }
        if (dp[low][high] > 0) {
            return dp[low][high];
        }
        
        for (int mid = low + 1; mid <= high; mid++) {
            int leftSum = sum[mid] - sum[low], rightSum = sum[high + 1] - sum[mid];
            if (leftSum > rightSum) {
                dp[low][high] = Math.max(dp[low][high], rightSum + dfs(val, mid, high, sum, dp));
            } else if (leftSum < rightSum) {
                dp[low][high] = Math.max(dp[low][high], leftSum + dfs(val, low, mid - 1, sum, dp));
            } else {
                dp[low][high] = Math.max(dp[low][high],
                                         Math.max(leftSum + dfs(val, low, mid - 1, sum, dp),
                                                  rightSum + dfs(val, mid, high, sum, dp)));
            }
        }
        
        return dp[low][high];
    }
    
    public int stoneGameV(int[] val) {
        int n = val.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + val[i - 1];
        }
        
        int[][] dp = new int[n][n];
        return dfs(val, 0, n - 1, sum, dp);
    }
}