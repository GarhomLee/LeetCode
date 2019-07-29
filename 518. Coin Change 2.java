https://leetcode.com/problems/coin-change-2/

// 对比：416. Partition Equal Subset Sum，不同点在于本题每个数可以使用无数次。
// 思路：DP，属于0/1背包问题。视频讲解：https://www.youtube.com/watch?v=ZKAILBWl08g
//         状态函数：dp[i][j]表示从coins数组前i个元素（即coins[0:i-1]，当前元素为coins[i-1]）组成和为j的方法个数。
//         状态转移方程：对于dp[i][j]，要考虑当前元素coins[i-1]“放还是不放进来”两种情况。
//                 如果当前元素不放进来，那么dp[i][j]取值和dp[i-1][j]相同，当前元素不产生影响。
//                 如果当前元素放进来，由于【当前元素可以使用无数次】，那么实际上dp[i][j]取值要考虑的是dp[i][j-coins[i-1]]，
//                 也就是【考虑了当前数（可以取无限个）放进来了，但特定的这一个数没放进来前】的取值。也就是说，j-coins[i-1]也可能
//                 包含了其他的当前元素coins[i-1]，但不包含【特定的这一个数】。
//                 综合两种情况，dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]]。
//                 注意，当j - coins[i - 1] < 0时，没有一个coins[i-1]可以放进来，所以dp[i][j - coins[i - 1]]不用考虑。
//         初始值：dp[0][0] = 1，表示利用0个coins数组元素组成和为0的方法有1种
// 时间复杂度：O(n*m), n=coins.length, m=amount
// 空间复杂度：O(n*m), n=coins.length, m=amount

class Solution {    
    public int change(int amount, int[] coins) {
        Arrays.sort(coins);
        int res = 0;
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j] + (j - coins[i - 1] >= 0 ? dp[i][j - coins[i - 1]] : 0);
            }
        }
        
        return dp[coins.length][amount];
    }
}

优化：因为dp[i]只和dp[i - 1]有关以及部分dp[i]有关，所以利用一维的滚动数组，可以将空间复杂度从降为O(m), m=amount