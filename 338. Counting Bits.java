https://leetcode.com/problems/counting-bits/

// 总体思路：DP问题。
// 解法一：
//         状态函数：dp[i]表示数字i的二进制表示中包含的1的个数
//         状态转移方程：使用了一个bit manipulation的技巧，【i & (i - 1)表示将i的二进制表示中的最后一个1去掉后的数】，
//                 如i = 14时，其二进制为1110，使用i & (i - 1)后变为1100，即为12。所以已知dp[i & (i - 1)]的情况下，
//                 只需要把去掉的那个1加回来即可，所以dp[i] = 1 + dp[i & (i - 1)]
//         初始值：i从1开始
// 犯错点：1. num == 0的情况要特殊考虑，不能单独初始化dp[1] = 1。如果循环从i = 1开始，则可以避免错误。

class Solution {
    public int[] countBits(int num) {
        //if (num == 0) return new int[]{0};  // {Mistake 1: corner case num == 0}
        int[] dp = new int[num + 1];
        for (int i = 1; i <= num; i++) {  // {Correction: let i start from 1, and do not initialize dp[1]}
            dp[i] = 1 + dp[i & (i - 1)];
        }
        return dp;
    }
}


// 解法二：假设数字i二进制最高位为第k位，那么前k-1位1的个数和i>>1的所有1的个数相同，而最低位是1还是0就看i的奇偶性。

class Solution {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            res[i] = res[i >> 1] + ((i & 1) == 0 ? 0 : 1);
        }
        return res;
    }
}