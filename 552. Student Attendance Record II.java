https://leetcode.com/problems/student-attendance-record-ii/

// 思路：DP。详见：https://www.jiuzhang.com/solution/student-attendance-record-ii/#tag-highlight
//         状态函数：dp[i]表示有i个位置可以放置时，如果完全不放'A'，能得到的合法结果的个数
//         状态转移方程：对于位置i，有3种可能：
//                 1）第i位放'P'，即以'P'结尾，结果个数取决于dp[i-1]
//                 2）第[i-1:i]位放"PL"，即以"PL"结尾，结果个数取决于dp[i-2]
//                 3）第[i-2:i]位放"PLL"，即以"PLL"结尾，结果个数取决于dp[i-3]
//                 综合三种情况，dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
//         初始值：1）dp[0]=1，无实际意义，只是为了方便乘法运算
//                 2）dp[1]=2，只有1个位置时可以取'P'和'L'任意一个，即C(2,1)
//                 3）dp[2]=4，有2个位置时，除了'A'都可以取，即C(2,1)*C(2,1)
//         计算完dp数组后，还没有得到最终结果，因为只考虑了没有出现'A'的情况，还需要把出现1个'A'的情况也加上。
//         'A'可以放置在[1:n]的任意位置，如果放在位置i，那么能组成的包含1个'A'的合法结果个数等于左半边i-1个元素
//         所能组成的结果dp[i-1]和右半边n-i个元素能组成的结果dp[n - i]的乘积，累加到res。
//         注意，这里只需要利用元素个数，而不区分是哪些具体元素。
//         最终结果是res+dp[n]，其中res表示包含1个'A'的合法结果个数，dp[n]表示完全不放'A'能得到的合法结果个数。
// 犯错点：1.数据范围错误：由于数据规模大，所以【每一步都需要求余数】

class Solution {
    private static final int MOD = 1000000007;
    
    public int checkRecord(int n) {
        
        long[] dp = new long[n + 1];
        dp[0] = 1;  // base case, no actual meaning
        if (n >= 1) dp[1] = 2;
        if (n >= 2) dp[2] = 4;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            dp[i] = dp[i] % MOD;  // this is necessary
        }
        
        long res = 0;
        for (int i = 1; i <= n; i++) {
            res += dp[i - 1] * dp[n - i];
            res = res % MOD;  // this is necessary
        }
        return (int) ((res + dp[n]) % MOD);
    }
}