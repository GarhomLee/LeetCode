https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/

idea: DP. Referring to: https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/discuss/484235/JavaC%2B%2BPython-Similar-to-LC1024
    State function: dp[i] indicates the minimum taps for watering [0:i].
    State transfer: For all taps j in current range [i-ranges[i]:i+ranges[i]], update dp[j] 
        by selecting the smaller number between its previous state value dp[j] and 1 + dp[i-ranges[i]],
        which means maybe 1 more tap is needed from the left boundary.
    Initial value: dp[i]=some big number, for example n+10
time complexity: O(n*r), r=max(ranges[i])=100 according to constraints
space complexity: O(n)

class Solution {
    public int minTaps(int n, int[] ranges) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 10);
        for (int i = 0; i <= n; i++) {
            for (int j = Math.max(0, i - ranges[i]); j <= Math.min(n, i + ranges[i]); j++) {
                if (i - ranges[i] <= 0) {
                    dp[j] = 1;
                } else {
                    dp[j] = Math.min(dp[j], 1 + dp[i - ranges[i]]);
                }
            }
        }
        return dp[n] == n + 10 ? -1 : dp[n];
    }
}