https://leetcode.com/problems/partition-array-for-maximum-sum/

idea: DP
    -dp[i] indicates the maximum sum in A[0:i), right exclusive.
    -For each A[i-1] (i in range [1:n]), try to replace the nearest continuous k elements
     with the max num in A[i-k:i-1], which will be max*k in total. Then, the sum will be
     dp[i-k] + max*k.
time complexity: O(NK)
space complexity: O(N)

class Solution {
    public int maxSumAfterPartitioning(int[] A, int K) {
        int n = A.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int max = 0;
            for (int k = 1; k <= Math.min(K, i); k++) {
                max = Math.max(max, A[i - k]);
                dp[i] = Math.max(dp[i], dp[i - k] + max * k);
            }
        }
        
        return dp[n];
    }
}