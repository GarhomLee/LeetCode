https://leetcode.com/problems/triangle/

// 总体思路：DP题，需要转变思维，从底层往上走，这样就可以在每一层遍历的时候进行选择。
//         状态函数：dp[i]表示从最底层行走到当前行时第i列（0-based）的minimum path sum。初始长度定为triangle.size()+1是为了简化代码。
//         状态转移方程：当前行的sum取决于下一行，当前列的sum在下一行同一列和相邻列（即i和i+1）取较小值来更新。
//         初始值：dp[i] = 0

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int dimension = triangle.size();
        int[] dp = new int[dimension + 1];
        for (int row = dimension - 1; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                dp[col] = triangle.get(row).get(col) + Math.min(dp[col], dp[col + 1]);
            }
        }
        return dp[0];
    }
}