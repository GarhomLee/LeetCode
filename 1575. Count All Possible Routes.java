https://leetcode.com/problems/count-all-possible-routes/

idea: Recursion with Memoization
time complexity: O(n^2 *fuel)
space complexity: O(n * fuel)

class Solution {
    final int MOD = 1_000_000_007;
    
    // return the total count of routes that starts at currIdx, ends at endIdx, and have fuel remaining
    private long dfs(int[] locations, int currIdx, int endIdx, int fuel, long[][] dp) {
        if (fuel < 0) {
            return 0;
        }
        if (dp[currIdx][fuel] != -1) {
            return dp[currIdx][fuel];
        }
        
        dp[currIdx][fuel] = currIdx == endIdx ? 1 : 0;
        for (int nextIdx = 0; nextIdx < locations.length; nextIdx++) {
            if (nextIdx != currIdx) {
                dp[currIdx][fuel] += dfs(locations, nextIdx, endIdx, fuel - Math.abs(locations[nextIdx] - locations[currIdx]), dp) % MOD;
            }
        }
        
        return dp[currIdx][fuel];
    }
    
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int n = locations.length;
        long[][] dp = new long[n][fuel + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        long res = dfs(locations, start, finish, fuel, dp);
        return (int) (res % MOD);
    }
}