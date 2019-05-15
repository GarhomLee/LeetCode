https://leetcode.com/problems/coin-change/

// 总体思路：DP问题。
//         状态函数：dp[i]表示能组成i的最少硬币数
//         状态转移方程：对于所有的小于i的coins[j]，只需要利用已知的dp[i - coins[j]]，再加上1表示拿了一个当前的coins[j]，可以
//                 得到所有可能的硬币数。只需取其中最小的一个即可。
//         初值：dp[0] = 0，dp[i]初始化为比amount稍大的数。
// 时间复杂度：O(m * n)，m = coins.length，n = amount
// 空间复杂度：O(n)，n = amount
// 犯错点：1. dp[i]不能初始化为Integer.MAX_VALUE，否则Integer.MAX_VALUE + 1变为Integer.MIN_VALUE会出错。
//           改正：初始化为比amount稍大的数即可。
//         2. dp[0]不受题目条件限制，即amount == 0时不需要返回-1
//         3. 当没有任何硬币组合可以组成i时，dp[i]会维持为初始值，并以此为标记。【不需要赋值为0】。

class Solution {
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 10;  // {Mistake 1: dp[i] should not be initialized as Integer.MAX_VALUE, because Integer.MAX_VALUE + 1 would be Integer.MIN_VALUE} {Correction 1}
            for (int j = 0; j < coins.length && coins[j] <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
}