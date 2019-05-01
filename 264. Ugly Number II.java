https://leetcode.com/problems/ugly-number-ii/

// 算是263. Ugly Number的follow-up，解题思路类似merge k sorted list，优化后变为DP题。见discuss：https://leetcode.com/problems/ugly-number-ii/discuss/69362/O(n)-Java-solution
// 1）维护dp数组，dp[i]表示第i个ugly number，它必然由之前的ugly number和2，3，或5相乘得到
// 2）维护3个变量i2，i3，i5，分别表示最近和2，3，5相乘的ugly number的位置
// 3）因为第i个ugly number（即dp[i]）由之前的ugly number和2，3，或5相乘得到，所以乘积的较小值即为当前dp[i]。
//     同时，如果从i2，i3，或i5得到dp[i]，需要更新之（只要能得到都要更新），因为相当于已经使用过了。

class Solution {
    public int nthUglyNumber(int n) {
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int i2 = 1, i3 = 1, i5 = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(dp[i2] * 2, Math.min(dp[i3] * 3, dp[i5] * 5));
            if (dp[i] == dp[i2] * 2) i2++;
            if (dp[i] == dp[i3] * 3) i3++;
            if (dp[i] == dp[i5] * 5) i5++;
        }
        return dp[n];
    }
}