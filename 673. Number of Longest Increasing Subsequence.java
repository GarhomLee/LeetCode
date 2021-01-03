https://leetcode.com/problems/number-of-longest-increasing-subsequence/



class Solution {
    public int findNumberOfLIS(int[] nums) {
        int maxLen = 0, maxCount = 0;
        int n = nums.length;
        int[] dp = new int[n];  // dp[i] indicates the LIS that ends at i
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        int[] counts = new int[n];  // counts[i] indicates the total count of the LIS that ends at i
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i] && dp[j] == dp[i] - 1) {
                    // have to meet this condition
                    counts[i] += counts[j];
                }
            }
            
            counts[i] = Math.max(counts[i], 1); // make sure that counts[i] is at least 1
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxCount = counts[i];
            } else if (dp[i] == maxLen) {
                maxCount += counts[i];
            }
        }
        
        return maxCount;
    }
}