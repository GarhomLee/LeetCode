https://leetcode.com/problems/integer-break/

// 根据数学性质可知，只有分成2和3的乘积才会最大。

// 解法一：iteration
// 1）n为2和3时需要作为特殊性情况单独讨论
// 2）在循环中，n == 4要和n == 2放在一起讨论，以免把n == 4的情况并入n == 3的情况

class Solution {
    public int integerBreak(int n) {
        if (n <= 2) return 1;
        if (n == 3) return 2;
        int product = 1;
        while (n >= 2) {
            if (n == 2 || n == 4) {
                product *= 2;
                n -= 2;
            } else {
                product *= 3;
                n -= 3;
            }
        }
        return product;
    }
}

// 解法二：DP，内层只需要循环1，2，3这三个数

class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i && j < 4; j++) {
                dp[i] = Math.max(dp[i], j * (Math.max(i - j, dp[i - j])));
            }
        }
        return dp[n];
    }
}