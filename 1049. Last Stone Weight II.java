https://leetcode.com/problems/last-stone-weight-ii/

idea: Dynamic Programming (Knapsack)
time complexity: O(n*sum)
space complexity: O(n*sum) 

class Solution {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length, sum = 0;
        for (int num : stones) {
            sum += num;
        }
        
        boolean[][] dp = new boolean[n + 1][sum + 1];
        dp[0][0] = true;
        for (int i = 0; i < n; i++) {
            dp[i + 1][0] = true;
            for (int j = 1; j <= sum; j++) {
                if (j < stones[i]) {
                    dp[i + 1][j] = dp[i][j];
                } else {
                    dp[i + 1][j] = dp[i][j] | dp[i][j - stones[i]];
                }
            }
        }
        
        int res = sum;
        for (int i = 1; i <= sum; i++) {
            if (dp[n][i]) {
                res = Math.min(res, Math.abs(sum - i - i));
            }
        }
        
        return res;
    }
}