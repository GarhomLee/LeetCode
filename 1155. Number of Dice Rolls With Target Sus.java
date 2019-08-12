https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/

// 解法一：Recursion with Memozation
//         特殊情况下，如果target大于骰子数d和面数f的乘积，那么一定不能组成target，返回0.
//         递归函数定义：int dfs(int d, int f, int target, Integer[][] dp)表示利用所有d个有f个面的骰子，能组成
//                 和为target的方法数。
//                 辅助数组dp定义相同。
//         终止条件：1）d==0，表示用完了所有骰子，判断target是否也为0。如果此时target也为0，说明找到了一种方法，返回1，
//                     否则返回0.
//                 2）dp[d][target] != null，表示已经搜索过了，直接返回dp[d][target]
//         递归过程：拿一个骰子，可能有f种结果[1:f]，那么剩余的d-1个骰子只需要组成[target-f:target-1]。
//                 因此，对于不同的结果i，dp[d][target]为所有dfs(d - 1, f, target - i, dp)的结果之和。由于数字
//                 可能很大，每一次都要MOD取余。
//                 注意，每个骰子不需要取[1:f]，而只需要取[1:min(target,f)]，避免target - i小于0.
// 时间复杂度：O(d*target)
// 空间复杂度：O(d*target)
// 犯错点：1.细节错误：如果dp值为0，也有可能是搜索过的表示有0种方法的结果。因此，不能只取dp > 0的结果。
//             要么将dp数组定义为Integer数组，要么初始化为-1.

class Solution {
    private static final int MOD = 1000000007;
    
    public int numRollsToTarget(int d, int f, int target) {
        if (target > d * f) {
            return 0;
        }
        
        Integer[][] dp = new Integer[d + 1][target + 1];
        return dfs(d, f, target, dp);
    }
    
    private int dfs(int d, int f, int target, Integer[][] dp) {
        if (d == 0) {
            return target == 0 ? 1 : 0;
        }
        
        if (dp[d][target] != null) {
            return dp[d][target];
        }
        
        dp[d][target] = 0;
        for (int i = 1; i <= Math.min(f, target); i++) {
            dp[d][target] += dfs(d - 1, f, target - i, dp);
            dp[d][target] = dp[d][target] % MOD;
        }
        
        return dp[d][target];
    }
}


解法二：DP

优化：空间降维