https://leetcode.com/problems/arithmetic-slices/

// 思路：bottom-up DP
//         状态函数：dp[i]表示数组A[0:i]范围中包含A[i]的能形成的arithmetic slices数目。
//         状态转移方程：如果从当前A[i]向前连续三个数能形成arithmetic slices，那么dp[i]=dp[i-1]+1。否则，dp[i]=0.
//         初始值：dp[i]=0
// 时间复杂度：O(n)
// 空间复杂度：O(n)

// 优化：降维。因为dp[i]只和dp[i-1]有关，那么实际上只需要用到1个变量，就可以存储相关的临时结果。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) return 0;
        
        int res = 0, count = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                count++;
            } else {
                count = 0;
            }
            res += count;
        }
        
        return res;
    }
}