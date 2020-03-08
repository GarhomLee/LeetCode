https://leetcode.com/problems/stone-game-ii/

// Idea：Recursion with Memoization
// time complexity: O(n^3)
// space complexity: O(2n^2)=O(n^2)
// code optimization: use 2D int[][] dp = new int[len][len + 1];

class Solution {
    /** for this player, given current M and start index, return the maximum total stones
    *   this player can get from piles[index:len)
    */
    private int dfs(int[] sum, int index, int player, int currM, int[][][] dp) {
        int len = sum.length - 1;   // length of piles
        // base case: out of range
        if (index >= len) {
            return 0;
        }
        // base case: result has been cached
        if (dp[player][index][currM] != 0) {
            return dp[player][index][currM];
        }
        // base case: this player can take all the remaining stones
        if (len - index <= 2 * currM) {
            dp[player][index][currM] = sum[len] - sum[index];
            return dp[player][index][currM];
        }
        
        // minimax
        int lostStones = Integer.MAX_VALUE;
        for (int i = 1; i <= 2 * currM; i++) {
            int nextM = Math.max(currM, i);
            lostStones = Math.min(lostStones, dfs(sum, index + i, 1 - player, nextM, dp));
        }
        
        dp[player][index][currM] = sum[len] - sum[index] - lostStones;
        return dp[player][index][currM];
    }
    
    public int stoneGameII(int[] piles) {
        int len = piles.length;
        int[] sum = new int[len + 1];   // sum[i] is the partial sum of piles[0:i) starting from i==1
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + piles[i - 1];
        }
        
        // dp[k][i][j]: the max total stones for player k to take from piles[i:len) given M == j
        int[][][] dp = new int[2][len][len + 1];
        
        return dfs(sum, 0, 0, 1, dp);
    }
}