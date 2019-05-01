https://leetcode.com/problems/super-ugly-number/

// 264. Ugly Number II的follow-up，考察一般性的情况。
// 1）维护dp数组，dp[i]表示第i个ugly number，它必然由之前的ugly number和primes中的某一个（或多个）prime number相乘得到
// 2）维护indices数组，表示最近和primes中的某一个（或多个）prime number相乘的ugly number的位置
// 3）两层循环：外层遍历n个数；内层第一次遍历indices找到最小的乘积，第二次根据dp[i]更新indices

class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] indices = new int[primes.length];
        Arrays.fill(indices, 1);
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < indices.length; j++) {
                dp[i] = Math.min(dp[i], dp[indices[j]] * primes[j]);
            }
            for (int j = 0; j < indices.length; j++) {
                if (dp[i] == dp[indices[j]] * primes[j]) indices[j]++;
            }
        }
        return dp[n];
    }
}