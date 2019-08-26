https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/

// 思路：DP
//         状态函数：dp[i]中包含两个值，dp[i][0]表示不调换A[i]和B[i]时能使得A[0:i]和B[0:i]都为严格递增序列
//                 的最小调换次数，dp[i][1]表示调换A[i]和B[i]时能使得A[0:i]和B[0:i]都为严格递增序列的最小
//                 调换次数。
//                 结果返回dp[n-1][0]和dp[n - 1][1]的较小值。
//         状态转移方程：【从位置1开始】，比较A[i]和B[i]和它们的前一个数字A[i-1]和B[i-1]的大小关系。有3种可能：
//                 1）如果A[i]比A[i-1]和B[i-1]都大，同时B[i]也比A[i-1]和B[i-1]都大，那么dp[i][0]可以直接取
//                     dp[i-1][0]和dp[i-1][1]的较小值，而dp[i][0]取dp[i-1][0]和dp[i-1][1]的较小值再加1
//                     表示进行了调换。
//                 2）如果1）不符合，但A[i]比A[i-1]大，B[i]比B[i-1]大，那么意味着A[i]和B[i]要随着A[i-1]和B[i-1]
//                     的调换而调换，所以dp[i][0] = dp[i - 1][0]，dp[i][1] = 1 + dp[i - 1][1]。
//                 3）如果1）不符合，但A[i]比B[i-1]大，B[i]比A[i-1]大，那么意味着，如果A[i]和B[i]不调换，
//                     A[i-1]和B[i-1]必然要调换，即dp[i][0] = dp[i - 1][1]。如果A[i]和B[i]进行了调换，
//                     那么A[i-1]和B[i-1]就不用调换，即dp[i][1] = 1 + dp[i - 1][0]。
//         初始值：dp[0][0]=0, dp[0][1] = 1
// 时间复杂度：O(n)
// 空间复杂度：O(n)，可以优化降维到O(1)

class Solution {
    public int minSwap(int[] A, int[] B) {
        int n = A.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1] && B[i] > B[i - 1] && A[i] > B[i - 1] && B[i] > A[i - 1]) {
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = 1 + Math.min(dp[i - 1][0], dp[i - 1][1]);
            } else if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = 1 + dp[i - 1][1];
            } else if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                dp[i][0] = dp[i - 1][1];
                dp[i][1] = 1 + dp[i - 1][0];
            }
        }
        
        return Math.min(dp[n-1][0], dp[n - 1][1]);
    }
}


另一种DP写法，简化条件判断：https://www.youtube.com/watch?v=__yxFFRQAl8