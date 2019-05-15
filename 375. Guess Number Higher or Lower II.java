https://leetcode.com/problems/guess-number-higher-or-lower-ii/

总体思路：待续

解法一：Top-down DP

class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        
        return guess(dp, 1, n);
    }
    private int guess(int[][] dp, int start, int end) {
        if (start >= end) return 0;
        if (start == end - 1) return start;
        if (dp[start][end] > 0) return dp[start][end]; 
        
        dp[start][end] = Integer.MAX_VALUE;
        for (int i = start + 1; i < end; i++) {
            dp[start][end] = Math.min(dp[start][end], i + Math.max(guess(dp, start, i - 1), guess(dp, i + 1, end)));
        }
        return dp[start][end];
    }
}

解法二：Bottom-up DP

class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        
        for (int end = 1; end <= n; end++) {
            for (int start = end - 1; start >= 1; start--) {
                if (start == end - 1) {
                    dp[start][end] = start;
                    continue;
                }
                dp[start][end] = Integer.MAX_VALUE; 
                for (int i = start + 1; i < end; i++) {
                    dp[start][end] = Math.min(dp[start][end], i + Math.max(dp[start][i - 1], dp[i + 1][end]));
                }
            }
        }
        
        return dp[1][n];
    }
}