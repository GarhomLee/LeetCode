https://leetcode.com/problems/minimum-cost-for-tickets/

// 思路：Dynamic Programming
//         状态函数：dp[i]表示在第i天所需要花费的最小费用，i=[1:365]
//         状态转移方程：1）如果i不存在于days数组中，那么当天不会做新的行程安排，因此dp[i] = dp[i - 1]
//                 2）如果i存在于days数组中，那么可以选择3种情况，取dp[i-1] + costs[0]，dp[i-7] + costs[1]，
//                 和dp[i-30] + costs[2]的最小值.
//         初始值：除了dp[0] = 0外，其他dp[i] = Integer.MAX_VALUE
// 时间复杂度：O(d), d=days[length - 1], which is no more than 365
// 空间复杂度：O(d), d=days[length - 1], which is no more than 365

class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[days[n - 1] + 1];
        Set<Integer> set = new HashSet<>();
        for (int day : days) {
            set.add(day);
        }
        
        for (int i = 1; i < dp.length; i++) {
            if (i > days[n - 1]) break;  // some optimization
            
            if (!set.contains(i)) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = Integer.MAX_VALUE;

                int pre = dp[i - 1];
                dp[i] = Math.min(dp[i], pre + costs[0]);

                pre = i - 7 >= 0 ? dp[i - 7] : dp[0];
                dp[i] = Math.min(dp[i], pre + costs[1]);

                pre = i - 30 >= 0 ? dp[i - 30] : dp[0];
                dp[i] = Math.min(dp[i], pre + costs[2]);
            }
            
        }
        
        return dp[days[n - 1]];
    }
}