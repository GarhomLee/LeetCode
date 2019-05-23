https://leetcode.com/problems/min-cost-climbing-stairs/

// 总体思路：可以看作70. Climbing Stairs的follow-up，加入了在每级阶梯的cost。
//         状态函数：dp[i]表示走到第i级阶梯（1-based）时的最小花费。
//         状态转移方程：踏上第i级阶梯，一定需要花费cost[i]，而最小花费是从可能到达第i级阶梯的第i-1级阶梯和第i-2级阶梯当中选取较小值
//         初始值：dp[0]=0, dp[1]=cost[0]。其中dp[0]没有实际意义。
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.最后的返回值应为min(dp[cost.length - 1], dp[cost.length])，因为到达顶端有两种可能，要从两种可能当中取较小值

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[1] = cost[0];
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = cost[i - 1] + Math.min(dp[i - 1], dp[i - 2]);
        }
        //return dp[cost.length];  // {Mistake 1}
        return Math.min(dp[cost.length - 1], dp[cost.length]);  // {Correction 1: since there are two ways to reach the top of the floor, 
                                                                // either from n-th or from (n-1)-th stair, we should choose the lower
                                                                // cost from them}
    }
}