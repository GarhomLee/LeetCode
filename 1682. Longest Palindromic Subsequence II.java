https://leetcode.com/problems/longest-palindromic-subsequence-ii/

idea: Recursion with Memoization
time complexity: O(n^2)
space complexity: O(n^2)

class Solution {
    /* return the longest subsequence in s[low:high] with previous diff as the constraint */
    public int helper(String s, int low, int high, int diff, Integer[][][] dp) {
        if (low >= high) {
            return 0;
        }
        if (dp[low][high][diff] != null) {
            return dp[low][high][diff];
        }
        
        if (s.charAt(low) == s.charAt(high) && s.charAt(low) - 'a' != diff) {
            dp[low][high][diff] = 2 + helper(s, low + 1, high - 1, s.charAt(low) - 'a', dp);
        } else {
            dp[low][high][diff] = Math.max(helper(s, low + 1, high, diff, dp),
                                           helper(s, low, high - 1, diff, dp));
        }
        return dp[low][high][diff];
    }
    
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        Integer[][][] dp = new Integer[n][n][27];
        
        return helper(s, 0, n - 1, 26, dp);
    }
}