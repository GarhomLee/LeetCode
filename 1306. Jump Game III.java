https://leetcode.com/problems/jump-game-iii/

解法一：Recursion with Memoization

时间复杂度：O(n)
空间复杂度：O(n)

class Solution {
    private int dfs(int[] arr, int curr, int[] dp) {
        int len = arr.length;
        if (curr < 0 || curr >= len) {
            return -1;
        }
        if (dp[curr] != 0) {
            return dp[curr];
        }
        if (arr[curr] == 0) {
            return 1;
        }
        
        dp[curr] = -1;
        int res = dfs(arr, curr + arr[curr], dp) == 1 || dfs(arr, curr - arr[curr], dp) == 1 ? 1 : -1;
        dp[curr] = res;
        
        return dp[curr];
    }
    
    public boolean canReach(int[] arr, int start) {
        int len = arr.length;
        int[] dp = new int[len];
        
        return dfs(arr, start, dp) == 1;
    }
}


解法二：BFS