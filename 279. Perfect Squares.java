https://leetcode.com/problems/perfect-squares/

// 时间复杂度为O(n^(3/2))的DP题。
// 1）维护dp数组，dp[i]表示和为i的完全平方数的最小个数
// 2）两层循环：外层循环遍历n个数；内层循环遍历小于i的所有完全平方数，相当于比较减掉某个完全平方数的dp加上被减掉的这个完全平方数个数（即+1），取最小值

class Solution {
    public int numSquares(int n) {
        if (n <= 0) return 0;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}