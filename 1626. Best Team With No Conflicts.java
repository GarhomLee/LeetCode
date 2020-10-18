https://leetcode.com/problems/best-team-with-no-conflicts/

idea: Sort + DP
time complexity: O(n log n + n^2)
space complexity: O(n)

class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] pair = new int[n][2];
        for (int i = 0; i < n; i++) {
            pair[i][0] = scores[i];
            pair[i][1] = ages[i];
        }
        Arrays.sort(pair, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);   // sort by ages
        
        int[] dp = new int[n + 1];
        int[] sum = new int[n + 1];
        int max = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = pair[i-1][0];
            for (int j = i - 1; j >= 1; j--) {
                if (pair[j-1][1] == pair[i-1][1] || pair[j-1][0] <= pair[i-1][0]) {
                    dp[i] = Math.max(dp[i], pair[i-1][0] + dp[j]);
                }
            }
            
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
}