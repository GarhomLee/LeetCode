https://leetcode.com/problems/4-keys-keyboard/

idea: DP. Refer to: https://leetcode.com/problems/4-keys-keyboard/discuss/105980/Java-4-lines-recursion-with-step-by-step-explanation-to-derive-DP
time complexity: O(n^2)
space complexity: O(n)

class Solution {
    public int maxA(int N) {
        int[] dp = new int[N+1];
        for (int i = 1; i <= N; i++) {
            dp[i] = i;
            for (int j = 1; j <= i - 3; j++) {
                dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));
            }
        }
        
        return dp[N];
    }
}