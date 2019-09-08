https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/

思路：DP

时间复杂度：O(n)
空间复杂度：O(n)

class Solution {
    public int maximumSum(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }
        
        int maxSum = Integer.MIN_VALUE;
        int[][] dp = new int[arr.length][2];
        dp[0][0] = arr[0];
        dp[0][1] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp[i][1] = Math.max(arr[i], arr[i] + dp[i - 1][1]);
            dp[i][0] = Math.max(dp[i - 1][1], arr[i] + dp[i - 1][0]);
            maxSum = Math.max(maxSum, Math.max(dp[i][0], dp[i][1]));
        }
        
        return maxSum;
    }
}