https://leetcode.com/problems/dice-roll-simulation/

// 解法一：Recursion with Memoization (Top-down)
//         首先进行预处理，用变量max表示所有rollMax的最大值。
//         递归函数定义：long dfs(int curr, int dice, int count, int[] rollMax, int n, Long dp[][][])，
//             表示当前骰子的数字为curr，用到的骰子是第dice个，是连续相同数字的count个骰子，所能组成的所有sequence的个数。
//             辅助数组dp[dice][rollMax[curr]][count]表示当前用到第dice个骰子，骰子数字curr对应的rollMax为rollMax[curr]，
//             连续相同数字的骰子已经有count个，所能组成的所有sequence的个数。
//         终止条件：1）dice==n，说明已经用到了最后一个骰子，返回1。
//                 2）dp[dice][rollMax[curr]][count] != null，说明该条件下所能组成的sequence的个数已经被计算过，直接返回结果。
//         递归过程：遍历所有可能的数字[0:6)，可能有两种情况：
//             1）i == curr，可能会受到rollMax的限制。如果这时count == rollMax[curr]，那么下一个骰子的数字一定不能是i，跳过；
//                 否则，下一层递归的参数里可以放置i，同时dice + 1，count + 1
//             2）i != curr，那么下一层递归的参数里一定可以放i，同时dice + 1，而且i和curr不相同，因此传递的count为1
// 时间复杂度：O(n * 6)=O(n)
// 空间复杂度：O(n * max^2)

class Solution {
    final int MOD = 1_000_000_007;
    long res = 0;
    
    public int dieSimulator(int n, int[] rollMax) {
        int max = 0;
        for (int m: rollMax) {
            max = Math.max(max, m);
        }
        
        Long dp[][][] = new Long[n + 1][max + 1][max + 1];   // dp[i][j][k] means using the i-th dice with roll max j 
															 // and currently there are k same continuous dice, 
															 // how many sequences can form in total
        for (int i = 0; i < 6; i++) {
            res += dfs(i, 1, 1, rollMax, n, dp) % MOD;  // get the sum of using each of [0:5] as the first dice
            res %= MOD;
        }
		
        return (int) res;
    }
    
    private long dfs(int curr, int dice, int count, int[] rollMax, int n, Long dp[][][]) {  // curr is the current dice number from [0:5],
																							// dice is the i-th dice using in this step,
																							// count is the contiuous count of this dice
        if (dice == n) {
            return 1;
        }
        if (dp[dice][rollMax[curr]][count] != null) {  // the total number of sequences formed by th i-th dice with the same continuous count 
														// and roll max has already been calculated, regardless of which dice number it is
            return dp[dice][rollMax[curr]][count];
        }
        
        long ans = 0;
        for (int i = 0; i < 6; i++) {
            if (i == curr) {
                if (count >= rollMax[curr]) continue;
                    
                ans += (dfs(i, dice + 1, count + 1, rollMax, n, dp)) % MOD;
            } else {
                ans += (dfs(i, dice + 1, 1, rollMax, n, dp)) % MOD;
            }
            ans %= MOD;
        }
		
        dp[dice][rollMax[curr]][count] = ans;
        return ans;
    }
}

优化：只需要用二维dp数组


解法二：DP (bottom-up)