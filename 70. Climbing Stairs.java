https://leetcode.com/problems/climbing-stairs/

// 总体思路：经典的DP题，本质上是Fibonacci数列。
//         状态函数：dp[i]表示到第i级台阶（1-based）的所有可能方法数
//         状态转移方程：走到第i级台阶有两种方式：要么从第i-1级台阶走一步，要么从第i-2级台阶走一步。所以能走到第i级台阶的方法数就是这两种可能的总和，
//                 即dp[i] = dp[i - 1] + dp[i - 2];
//         初始值：dp[0]=dp[1]=1，其中dp[0]没有实际意义。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}