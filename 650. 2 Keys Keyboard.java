https://leetcode.com/problems/2-keys-keyboard/

idea: DP
time complexity: O(n*sqrt(n))
space complexity: O(n)

class Solution {
    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }
        int[] dp = new int[n+1];
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int step = 2; step * step <= n; step++) {
                int prev = i / step;
                if (step * prev == i) {
                    dp[i] = Math.min(dp[i], dp[prev] + step);
                }
            }
        }
        
        return dp[n];        
    }
}