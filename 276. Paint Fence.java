https://leetcode.com/problems/paint-fence/

// 思路：思维上比较难的一道DP。参考：https://leetcode.com/problems/paint-fence/discuss/178010/The-only-solution-you-need-to-read
//         状态函数：dp[i]表示以第i个post为最右的能得到符合题意的颜色组合个数。
//         状态转移方程：对于第i个post的颜色，有两种可能情况：
//             1）和第i-1个post颜色不同，那么对于每一种已有的[0:i-1]的颜色组合，都只有k-1种颜色可以选，因此这种情况下
//                 可以得到的组合数为dp[i-1]*(k-1)
//             2）和第i-1个post颜色相同，又因为题目要求不能连续3个post颜色相同，所以要求第i-1个post和第i-2个颜色不同。
//                 由于当前第i个post的颜色完全依据第i-1个post，所以不需要考虑，只需要考虑第i-1个post和第i-2个的颜色关系，
//                 也就是求i-1时的情况1），所以这种情况下可以得到的组合数为dp[i-2]*(k-1)。
//             综合上述情况，dp[i]=(dp[i-1] + dp[i-2]) * (k-1)。
//         初始值：dp[0] = 0; dp[1] = k; dp[2] = k * k
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int numWays(int n, int k) {
        if (n == 0 || k == 0) {
            return 0;
        }
        if (n == 1) {
            return k;
        }
        if (n == 2) {
            return k * k;
        }
        
        int[] dp = new int[n + 1];
        dp[1] = k;
        dp[2] = k * k;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1);
        }
        
        return dp[n];
    }
}