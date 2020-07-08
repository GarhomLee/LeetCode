https://leetcode.com/problems/stone-game/

idea: Recursion with Memoization
time complexity: O(N^2)
space complexity: O(N^2)

class Solution {
    /** return the maximum num of stones in range piles[low:high] with pre-computed currSum in this range */
    private int dfs(int[] piles, int low, int high, int[][] dp, int currSum) {
        if (low == high - 1) {
            return Math.max(piles[low], piles[high]);
        }
        if (dp[low][high] > 0) {
            return dp[low][high];
        }
        
        int res1 = dfs(piles, low + 1, high, dp, currSum - piles[low]);
        int res2 = dfs(piles, low, high - 1, dp, currSum - piles[high]);
        dp[low][high] = Math.max(currSum - res1, currSum - res2);
        
        return dp[low][high];
    }
    
    public boolean stoneGame(int[] piles) {
        int n = piles.length, sum = 0;
        for (int num : piles) {
            sum += num;
        }
        return dfs(piles, 0, n - 1, new int[n][n], sum) > sum / 2;
    }
}