https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/

思路：DP
Time complexity: O(len1*len2)
Space complexity: O(len1*len2)

class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 == 0 && len2 == 0) {
            return 0;
        }
        
        // dp[i][j]: the minimum delete to make s1[0:i) and s2[0:j) to be the same
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
            for (int j = 1; j <= len2; j++) {
                char c1 = s1.charAt(i - 1), c2 = s2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + (int) c1, dp[i][j - 1] + (int) c2);
                }
            }
        }
        
        return dp[len1][len2];
    }
}