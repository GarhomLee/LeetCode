https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/

思路：DP + Bit Manipulaiton，分为多个小的0/1背包问题。
        首先，进行预处理，得到num的二进制表示的最大数位。
        状态函数：dp[i]表示如果前i个数位可以放1，可组成的二进制中没有出现连续1的数的个数。
        状态转移方程：对于第i位，可以有放0或者放1两种情况：
                1）如果放0，那么对于第i-1位没有限制，所以取决于dp[i-1]
                2）如果放1，那么为了不出现连续的1，第i-1位只能放0，从第i-2位开始才没有限制，所以取决于dp[i-2]
                综合两种情况，dp[i]取并集，即dp[i]=dp[i-1]+dp[i-2]
        初始值：1）dp[0] = 1，如果不放任何1，可以组成1个数，即0
                2）dp[1] = 2，如果前1个数位可以放1，那么可以组成2个数，即0和1
        得到dp数组后，还没得到最终结果。假设要求的是"10101"的结果，那么dp[4]对应的是"00000"-"01111"的结果，
        不完整；而dp[5]对应的是"00000"-"11111"的结果，超出了范围。因此，不能简单的用dp[4]或而dp[5]，而是分成
        多个小的部分，求各个部分的加和。对于"10101"，需要求"00000"-"01111"的结果，"10000"-"10011"的结果，
        和"10100"的结果，以及整个"10101"本身。
        从digit-1位开始，不断更新变量pre表示前一位的bit。在每个1 bit位置i分段，累加dp[i]到变量res中，同时利用pre
        判断是否出现了连续的1。如果有连续1，后面的结果必然也会带有连续的1，不符合题意，所以不需要继续往下求，
        用hasConsecutiveOnes=true记录下来。
        最后返回的结果中，如果hasConsecutiveOnes==true，说明num本身不合题意，只需返回res。否则，num本身也要算进去，
        所以返回res+1.
时间复杂度：O(digit)
空间复杂度：O(digit)

class Solution {
    public int findIntegers(int num) {
        //if (num == 0) return 0;
        
        int temp = num, digit = 0;
        while (temp != 0) {
            digit++;
            temp = temp >> 1;
        }
        /* fill dp array */
        int[] dp = new int[digit];
        if (digit >= 1) dp[0] = 1;
        if (digit >= 2) dp[1] = 2;
        for (int i = 2; i < digit; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        /* get results from each part seperated by 1 in bit*/
        boolean hasConsecutiveOnes = false;
        int res = 0, pre = 0;
        for (int i = digit - 1; i >= 0 && !hasConsecutiveOnes; i--) {
            if ((num & (1 << i)) != 0) {
                res += dp[i];
                if (pre == 1) {
                    hasConsecutiveOnes = true;
                }
                
                pre = 1;  // update 
            } else {
                pre = 0;  // update
            }
        }
        
        return res + (hasConsecutiveOnes ? 0 : 1);
    }
}