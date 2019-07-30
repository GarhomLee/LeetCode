https://leetcode.com/problems/ones-and-zeroes/

// 思路：DP，属于0/1背包问题。
//         状态函数：dp[i][j][k]，都是1-based，表示给出前i个strs数组中的字符串元素，给定j个0，以及k个1，能取得的
//                 最大字符串元素个数。
//         状态转移方程：遍历每个strs数组元素，先统计其含有的0和1的个数。
//                     两层循环遍历m个0和n个1。对于当前strs[i - 1]“放还是不放”，分两种情况：
//                     如果不放，那么dp[i][j][k] = dp[i - 1][j][k]，当前strs[i - 1]没有影响；
//                     如果放，那么dp[i][j][k]取决于把当前strs[i - 1]刨除后的dp[i - 1][j - count[0]][k - count[1]]，
//                     然后还要再+1表示放进了当前strs[i - 1]。
//                     由于strs[i - 1]只能使用一次，所以dp[i][j][k]只能从以上两种情况取其一，因此取较大值。
//                     特殊情况下，给定的j个0比strs[i - 1]所需的0要少，或者给定的k个1比strs[i - 1]所需的1要少，那么
//                     dp[i][j][k]只能等于dp[i - 1][j][k] 。
//         初始值：无特定初始值，所有dp[i][j][k]=0
// 时间复杂度：O(l*m*n), l=strs.length, m=num of given 0s, n=num of given 1s
// 空间复杂度：O(l*m*n), l=strs.length, m=num of given 0s, n=num of given 1s

class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        for (int i = 1; i <= strs.length; i++) {
            /* count how many 0s and 1s in currrent String strs[i - 1] */
            int[] count = new int[2];
            for (char c : strs[i - 1].toCharArray()) {
                count[c - '0']++;
            }
            /* dp search */
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j < count[0] || k < count[1]) {  // 0s or 1s is not enough to make up a strs[i - 1]
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], 1 + dp[i - 1][j - count[0]][k - count[1]]);
                    }
                }
            }
        }
        
        return dp[strs.length][m][n];
    }
}

优化：空间降维，由于dp[i]只和dp[i-1]有关，所以实际上只需要用二维数组，将空间复杂度降为O(m*n)
时间复杂度：O(l*m*n), l=strs.length, m=num of given 0s, n=num of given 1s
空间复杂度：O(m*n), m=num of given 0s, n=num of given 1s

class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < strs.length; i++) {
            int[] count = new int[2];
            for (char c : strs[i].toCharArray()) {
                count[c - '0']++;
            }
            
            for (int j = m; j >= count[0]; j--) {
                for (int k = n; k >= count[1]; k--) {
                    dp[j][k] = Math.max(dp[j][k], 1 + dp[j - count[0]][k - count[1]]);
                }
            }
        }
        
        return dp[m][n];
    }
}