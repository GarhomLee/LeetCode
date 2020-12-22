https://leetcode.com/problems/maximum-length-of-pair-chain/

idea: DP. Similar to 300. Longest Increasing Subsequence
time complexity: O(n^2)
space complexity: O(n)

class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]); // first by [0] then by [1]
        int n = pairs.length;
        int[] dp = new int[n];
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        
        return maxLen;
    }
}